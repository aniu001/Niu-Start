package com.rjwl.api.security;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.rjwl.api.common.utils.MobileUtil;
import com.rjwl.api.common.utils.RedisUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.AbstractHash;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义凭证匹配器
 *
 * @author aniu
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
    /**
     * @since 1.1
     */
    private String hashAlgorithm;
    private int hashIterations;
    private boolean hashSalted;
    private boolean storedCredentialsHexEncoded;

    @Autowired
    private RedisUtil redisUtil;


    public MyHashedCredentialsMatcher() {
        this.hashAlgorithm = null;
        this.hashSalted = false;
        this.hashIterations = 1;
        //false means Base64-encoded
        this.storedCredentialsHexEncoded = true;
    }


    public MyHashedCredentialsMatcher(String hashAlgorithmName) {
        this();
        if (!StringUtils.hasText(hashAlgorithmName)) {
            throw new IllegalArgumentException("hashAlgorithmName cannot be null or empty.");
        }
        this.hashAlgorithm = hashAlgorithmName;
    }

    @Override
    public String getHashAlgorithmName() {
        return hashAlgorithm;
    }

    @Override
    public void setHashAlgorithmName(String hashAlgorithmName) {
        this.hashAlgorithm = hashAlgorithmName;
    }

    @Override
    public boolean isStoredCredentialsHexEncoded() {
        return storedCredentialsHexEncoded;
    }

    @Override
    public void setStoredCredentialsHexEncoded(boolean storedCredentialsHexEncoded) {
        this.storedCredentialsHexEncoded = storedCredentialsHexEncoded;
    }

    @Deprecated
    public boolean isHashSalted() {
        return hashSalted;
    }

    @Deprecated
    public void setHashSalted(boolean hashSalted) {
        this.hashSalted = hashSalted;
    }

    @Override
    public int getHashIterations() {
        return hashIterations;
    }

    @Override
    public void setHashIterations(int hashIterations) {
        if (hashIterations < 1) {
            this.hashIterations = 1;
        } else {
            this.hashIterations = hashIterations;
        }
    }

    @Deprecated
    protected Object getSalt(AuthenticationToken token) {
        return token.getPrincipal();
    }

    @Override
    protected Object getCredentials(AuthenticationInfo info) {
        Object credentials = info.getCredentials();

        byte[] storedBytes = toBytes(credentials);

        if (credentials instanceof String || credentials instanceof char[]) {
            //account.credentials were a char[] or String, so
            //we need to do text decoding first:
            if (isStoredCredentialsHexEncoded()) {
                storedBytes = Hex.decode(storedBytes);
            } else {
                storedBytes = Base64.decode(storedBytes);
            }
        }
        AbstractHash hash = newHashInstance();
        hash.setBytes(storedBytes);
        return hash;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //不存在密码
        if (StrUtil.isNotBlank((String) info.getCredentials())) {
            Object tokenHashedCredentials = hashProvidedCredentials(token, info);
            Object accountCredentials = getCredentials(info);
            return equals(tokenHashedCredentials, accountCredentials);
        } else {
            //通过验证码方式登录
            //查看登录账号是否符合 手机号格式
            String phone = (String) token.getPrincipal();
            String tVerifyCode = String.valueOf((char[]) token.getCredentials());
            if (!MobileUtil.isPhoneBase(phone)) {
                return false;
            }
            //获取redis中验证码
            String rVerifyCode = redisUtil.get(phone);
            if (StrUtil.isBlank(rVerifyCode)) {
                return false;
            }
            //进行验证码比对
            if (tVerifyCode.equals(rVerifyCode)) {
                return true;
            }
            return false;
        }
    }

    @Override
    protected Object hashProvidedCredentials(AuthenticationToken token, AuthenticationInfo info) {
        Object salt = null;
        if (info instanceof SaltedAuthenticationInfo) {
            salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
        } else {
            //retain 1.0 backwards compatibility:
            if (isHashSalted()) {
                salt = getSalt(token);
            }
        }
        return hashProvidedCredentials(token.getCredentials(), salt, getHashIterations());
    }

    private String assertHashAlgorithmName() throws IllegalStateException {
        String hashAlgorithmName = getHashAlgorithmName();
        if (hashAlgorithmName == null) {
            String msg = "Required 'hashAlgorithmName' property has not been set.  This is required to execute " +
                    "the hashing algorithm.";
            throw new IllegalStateException(msg);
        }
        return hashAlgorithmName;
    }

    @Override
    protected Hash hashProvidedCredentials(Object credentials, Object salt, int hashIterations) {
        String hashAlgorithmName = assertHashAlgorithmName();
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
    }

    @Override
    protected AbstractHash newHashInstance() {
        String hashAlgorithmName = assertHashAlgorithmName();
        return new SimpleHash(hashAlgorithmName);
    }
}

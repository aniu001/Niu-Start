package com.rjwl.api.security;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rjwl.api.entity.Admin;
import com.rjwl.api.entity.Menu;
import com.rjwl.api.service.AdminService;
import com.rjwl.api.service.MenuService;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * shiro 自定义realm
 * @author aniu
 */
public class MyRealm extends AuthorizingRealm {

    @Setter
    private AdminService adminService;
    @Setter
    private MenuService menuService;
    private static final String SALT = "RUIJING_QIANG";

    private static final String AUTHORIZATION_CACHE_NAME = "authorization";

    public MyRealm() {
        super.setAuthorizationCacheName(AUTHORIZATION_CACHE_NAME);
    }

    @Override
    public String getName() {
        return "MyRealm";
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Admin admin = adminService.selectAdminByName(username);
        if (admin == null) {
            return null;
        }
//        clearAuthorizationInfoCache(admin);//用户登录后,清除用户缓存,以便重新加载用户权限
        return new SimpleAuthenticationInfo(admin, admin.getPassword(), ByteSource.Util.bytes(SALT), getName());
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前操作用户信息
        Admin admin = (Admin) principals.getPrimaryPrincipal();
        //权限编码 user:edit
        List<String> permissions = new ArrayList<>();
        //角色简称 sysMgr
        List<String> roles = new ArrayList<>();
        //admin用户
        if ("admin".equals(admin.getAdminName())) {
            //拥有所有权限
            permissions.add("*:*");
            //查询所有角色
            //  roles 缺少编码字段 sysMgr
            roles.add(admin.getRoleId().toString());
        } else {
            roles.add(admin.getRoleId().toString());
            List<Menu> menus = menuService.selectMenusByRoleId(admin.getRoleId());
            for (Menu menu : menus) {
                //menu  中权限表达式menuCode  **：**格式 与 congtroller 中方法 @RequestPermission注解的value 对应
                permissions.add(menu.getMenuCode());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;

    }

    /**
     * 清除所有用户的缓存
     */
    public void clearAuthorizationInfoCache() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            cache.clear();
        }
    }


/**
     * 清除指定用户的缓存
     *
     * @param
     */
    private void clearAuthorizationInfoCache(Admin admin) {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//        cache.remove(admin.get());
    }

    /**
     * 清除缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
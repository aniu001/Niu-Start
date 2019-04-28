package com.rjwl.api.security;

import com.rjwl.api.entity.Admin;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author aniu
 */


public class RedisCacheManager implements CacheManager {
    private String cacheKeyPrefix = "shiro:";
    private long timeout;

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroRedisCache<K, V>(cacheKeyPrefix + name, timeout);
    }

    /**
     * 为shiro量身定做的一个redis cache,为Authorization cache做了特别优化
     */

    public class ShiroRedisCache<K, V> implements Cache<K, V> {

        private String cacheKey;
        private long authorizationCacheTimeout;

        public ShiroRedisCache(String cacheKey, long authorizationCacheTimeout) {
            this.cacheKey = cacheKey;
            this.authorizationCacheTimeout = authorizationCacheTimeout;
        }

        @Override
        public V get(K key) throws CacheException {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            Object k = hashKey(key);
            //获取Authorization cache 刷新过期时间
            hash.expire(authorizationCacheTimeout, TimeUnit.SECONDS);
            return hash.get(k);
        }

        @Override
        public V put(K key, V value) throws CacheException {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            Object k = hashKey(key);
            hash.put((K) k, value);
            //为Authorization cache添加过期时间
            hash.expire(authorizationCacheTimeout, TimeUnit.SECONDS);
            return value;
        }

        @Override
        public V remove(K key) throws CacheException {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);

            Object k = hashKey(key);
            V value = hash.get(k);
            hash.delete(k);
            return value;
        }

        @Override
        public void clear() throws CacheException {
            redisTemplate.delete(cacheKey);
        }

        @Override
        public int size() {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            return hash.size().intValue();
        }

        @Override
        public Set<K> keys() {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            return hash.keys();
        }

        @Override
        public Collection<V> values() {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            return hash.values();
        }

        protected Object hashKey(K key) {
//此处很重要,如果key是登录凭证,那么这是访问用户的授权缓存;将登录凭证转为admin对象,返回admin的id属性做为hash key,否则会以admin对象做为hash key,这样就不好清除指定用户的缓存了
            if (key instanceof PrincipalCollection) {
                PrincipalCollection pc = (PrincipalCollection) key;
                Admin admin = (Admin) pc.getPrimaryPrincipal();
                return admin.getAdminName();
            }
            return key;
        }
    }

}

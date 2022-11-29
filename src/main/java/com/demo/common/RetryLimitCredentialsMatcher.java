package com.demo.common;


import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    //声明缓存对象
    private Ehcache userLockEhcache;
    // 声明构造方法获取缓存对象
    public RetryLimitCredentialsMatcher(EhCacheManager ehCacheManager){
        userLockEhcache = ehCacheManager.getCacheManager().getEhcache("userLockEhcache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //声明变量记录错误次数
        int i=0;
        //1.获取缓存中的错误次数，获取用户的身份信息（身份信息为缓存数据的键名）
        String username = token.getPrincipal().toString();
        //获取缓存对象
        Element element = userLockEhcache.get(username);
        //判断是否有缓存数据  没有缓存登录的错误次数，创建新的缓存
        if (element==null)
        {
            Element ele = new Element(username, new AtomicInteger(0));
            userLockEhcache.put(ele);
        }else
        {
            //有缓存就递增一下
            AtomicInteger atomicInteger = (AtomicInteger)element.getObjectValue();
            i = atomicInteger.incrementAndGet();
        }

        //2.判断缓存中的错误次数
        if (i>=3)
        {
            throw new ExcessiveAttemptsException();
        }
        //3.如果不是3次进行登录认证，认证失败次数+1，认证成功次数清空
        boolean matchs = super.doCredentialsMatch(token, info);
        //登录成功，次数清空
        if (matchs)
        {
            userLockEhcache.remove(username);
        }
        //4.返回结果
        return matchs;
    }
}

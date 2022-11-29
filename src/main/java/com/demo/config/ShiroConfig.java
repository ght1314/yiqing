package com.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.demo.common.RetryLimitCredentialsMatcher;
import com.demo.realm.MyShiroRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 自定义凭证匹配器
    @Autowired
    private RetryLimitCredentialsMatcher retryLimitCredentialsMatcher;


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        设置defaultWebSecurityManager
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        设置登录url
        shiroFilterFactoryBean.setLoginUrl("/User/login");
//        设置主页url
        shiroFilterFactoryBean.setSuccessUrl("/User/main");
        //没有权限页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/User/main");

//        开放swagger,403错误接口,开放静态资源文件
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/User/doLogin", "anon");
        //找回密码相关
        filterChainDefinitionMap.put("/User/lookForPwd", "anon");
        filterChainDefinitionMap.put("/User/lookForPwdSubmit", "anon");
        filterChainDefinitionMap.put("/User/sendSms", "anon");
        filterChainDefinitionMap.put("/User/retrievePwdSubmit", "anon");
        filterChainDefinitionMap.put("/User/register", "anon");
        filterChainDefinitionMap.put("/User/registerUser", "anon");
//        filterChainDefinitionMap.put("/menus/*","anon");
        //swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        //静态资源
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/font/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        //        需要拦截的其余url
        filterChainDefinitionMap.put("/**", "user");
//        装配拦截策略
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置登录认证开启MD5加密
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        retryLimitCredentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        retryLimitCredentialsMatcher.setHashIterations(1024);
        myShiroRealm.setCredentialsMatcher(retryLimitCredentialsMatcher);
        defaultWebSecurityManager.setRealm(myShiroRealm);
//        开启记住我 remember me
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //开启缓存ehcache
        defaultWebSecurityManager.setCacheManager(ehCacheManager());

        return defaultWebSecurityManager;
    }

    @Bean(name = "myShiroRealm")  //使用该注解是的Realm对象由spring容器管理
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm shiroRealm = new MyShiroRealm();
        return shiroRealm;
    }


    /**
     * 设置RememberMe对象
     */

    public CookieRememberMeManager rememberMeManager() {
        //创建CookieRememberMeManager对象
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        //设置cookie的参数（有效期..)
        rememberMeManager.setCookie(rememberMeCookie());
        //cookie信息加密 两种方式
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    /**
     * 设置cookie信息
     */

    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        //有效路径
        cookie.setPath("/");
        //有效期
        cookie.setMaxAge(7 * 24 * 60 * 60);
        return cookie;
    }


    //开启Aop注解支持,用來扫描shiro相关的注解
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }


    /**
     * 显式声明 CacheManager ,防止 EhCacheCacheConfiguration 调用 ehCacheCacheManager()
     * 继续生成 CacheManager
     *
     * @return
     */
    @Bean
    public CacheManager ehCacheCacheManager() {
        CacheManager cacheManager = CacheManager.getCacheManager("ehcache");// 2.10.6

        if (cacheManager == null) {
            try {
                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache/ehcache-shiro.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cacheManager;
    }


    /**
     * 将生成的 CacheManager 对象转为Shiro 管理的 EhCacheManager 对象
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        CacheManager cacheManager = ehCacheCacheManager();
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
//        ehCacheManager.setCacheManager(cacheCacheManager.getCacheManager());
        return ehCacheManager;
    }


    //整合thymeleaf shiro标签
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}

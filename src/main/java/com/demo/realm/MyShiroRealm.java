package com.demo.realm;

import com.demo.dto.UserDto;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * shiro验证用户名密码是否匹配，该用户是否具有权限
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private RoleService roleService;


    // 系统授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户的身份信息（用户名）
        String logAccount = (String) principalCollection.getPrimaryPrincipal();
        //获取当前用户的角色、权限信息
        String roleName = roleService.findRoleByAccount(logAccount);
        //log.info("我是"+logAccount+"用户角色为："+roleName);

        //将用户的权限信息给shiro
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(roleName);
        return info;
    }

    //    用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String) token.getPrincipal();
//        根据用户信息查询数据库获取后端用户身份，转交给securityManager判定
        UserDto loginUser = userService.queryUserDtoByAccount(account);
        if (loginUser != null) {
            //当前realm对象的name
            String realmName = getName();
            //盐值
            ByteSource credentialsSalt = ByteSource.Util.bytes(account);
            //封装用户信息MD5Salt，构建AuthenticationInfo对象并返回
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account, loginUser.getPassword(), credentialsSalt, realmName);
            return authenticationInfo;
        }
        return null;

    }


    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }
}

package com.inHere.shiro.realm;

import com.inHere.dao.UserMapper;
import com.inHere.entity.Permissions;
import com.inHere.entity.Roles;
import com.inHere.entity.User;
import com.inHere.redis.TokenManage;
import com.inHere.service.SecurityService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * shiro 认证 + 授权
 *
 * @author lwh
 * @date 2016-10-03
 */
public class RootRealm extends AuthorizingRealm {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TokenManage tokenManage;

    /**
     * 认证, 错误抛异常处理（RuntimeException） TODO token绑定redis里
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String user_id = (String) token.getPrincipal();  //得到用户名
        String acceptPwdTmp = new String((char[]) token.getCredentials()); //得到密码

        User user = userMapper.selectByPrimaryKey(user_id);
        // 帐号不存在
        if (user == null) {
            throw new UnknownAccountException();
        } else {

            List<Roles> roles = userMapper.selectUserRoles(user.getUserId());
            if (roles.size() < 1) { // 用户非管理员，无权登录
                throw new UnauthorizedException();
            }

            String salt = user.getSaltKey();
            String dbPwd = user.getPasswd();
            String acceptPwd = securityService.encrypt(salt, acceptPwdTmp);

            // 身份认证验证成功
            if (acceptPwd.equals(dbPwd)) {
                log.info("------>身份认证验证成功");
                DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
                DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
                //获取当前已登录的用户session列表
                Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
                for (Session session : sessions) {
                    //清除该用户以前登录时保存的session, 实现单用户登录，一个用户同一时刻只能在一个地方登录
                    PrincipalCollection principal = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                    User userTmp = (User) principal.getPrimaryPrincipal();
                    if (userTmp.getUserId().equals(user_id)) {
                        log.info("------>清除该用户以前登录时保存的session");
                        tokenManage.checkUserDel(userTmp.getUserId());
                        sessionManager.getSessionDAO().delete(session);
                    }
                }

                //身份认证验证成功，返回一个AuthenticationInfo实现
                return new SimpleAuthenticationInfo(user, acceptPwdTmp, getName());
            } else { // 凭证异常
                log.info("------>身份认证验证失败");
                throw new CredentialsException();
            }
        }
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("---->用户授权");
        // 获取通过认证的用户信息
        User user = (User) principalCollection.getPrimaryPrincipal();

        List<Roles> roles = userMapper.selectUserRoles(user.getUserId());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 授予角色和权限
        for (Roles role : roles) {
            authorizationInfo.addRole(role.getRole());
            for (Permissions perm : role.getPermissions()) {
                authorizationInfo.addStringPermission(perm.getPermission());
            }
        }
        return authorizationInfo;
    }

}

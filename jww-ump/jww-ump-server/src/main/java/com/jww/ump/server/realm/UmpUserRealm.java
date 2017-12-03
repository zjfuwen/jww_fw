package com.jww.ump.server.realm;

import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpAuthorizeService;
import com.jww.ump.rpc.api.UmpUserService;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author wanyong
 * @date 2017/11/29 15:00
 */
@Slf4j
@Component
public class UmpUserRealm extends AuthorizingRealm {

    @Autowired
    private UmpUserService umpUserService;

    @Autowired
    private UmpAuthorizeService umpAuthorizeService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String account = (String) principals.getPrimaryPrincipal();
        UmpUserModel umpUserModel = umpUserService.findByAccount(account);
        if (umpUserModel != null) {
            List<String> permissionList = umpAuthorizeService.findPermissionByUserId(umpUserModel.getId());
            for (String permission : permissionList) {
                if (StrUtil.isNotBlank(permission)) {
                    simpleAuthorizationInfo.addStringPermission(permission);
                }
            }
        }
        // 添加用户权限
        simpleAuthorizationInfo.addStringPermission("user");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        UmpUserModel umpUserModel = umpUserService.findByAccount(usernamePasswordToken.getUsername());
        if (null == umpUserModel) {
            throw new UnknownAccountException();
        }
        if (!umpUserModel.getPassword().equals(new String(usernamePasswordToken.getPassword()))) {
            throw new IncorrectCredentialsException();
        }
        // return new SimpleAuthenticationInfo(umpUserModel.getAccount(), umpUserModel.getPassword(), umpUserModel.getUserName());
        return new SimpleAuthenticationInfo(umpUserModel, umpUserModel.getPassword(), umpUserModel.getUserName());
    }
}
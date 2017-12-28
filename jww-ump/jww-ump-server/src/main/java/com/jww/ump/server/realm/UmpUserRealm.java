package com.jww.ump.server.realm;

import com.alibaba.fastjson.JSON;
import com.jww.common.web.util.WebUtil;
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

import java.util.Arrays;
import java.util.List;

/**
 * shiro权限获取
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

    /**
     * 权限验证
     *
     * @param principals
     * @return AuthorizationInfo
     * @author wanyong
     * @date 2017-12-25 19:52
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UmpUserModel umpUserModel = (UmpUserModel) principals.getPrimaryPrincipal();
        if (umpUserModel != null) {
            List<String> permissionList = umpAuthorizeService.queryPermissionsByUserId(umpUserModel.getId());
            for (String permission : permissionList) {
                if (StrUtil.isNotBlank(permission)) {
                    //一个菜单有多个权限标识，逗号分隔，需要拆分
                    String[] perms = StrUtil.split(permission, ",");
                    Arrays.stream(perms).forEach(perm -> {
                                if (StrUtil.isNotBlank(perm)) {
                                    simpleAuthorizationInfo.addStringPermission(perm);
                                }
                            }
                    );
                }
            }
            log.info("userId:{},permissionList:{}", umpUserModel.getId(), JSON.toJSONString(permissionList));
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录验证
     *
     * @param token
     * @return AuthenticationInfo
     * @author wanyong
     * @date 2017-12-25 19:51
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        UmpUserModel umpUserModel = umpUserService.queryByAccount(usernamePasswordToken.getUsername());
        if (null == umpUserModel) {
            throw new UnknownAccountException();
        }
        if (!umpUserModel.getPassword().equals(new String(usernamePasswordToken.getPassword()))) {
            throw new IncorrectCredentialsException();
        }
        WebUtil.saveCurrentUser(umpUserModel.getId());
        return new SimpleAuthenticationInfo(umpUserModel, umpUserModel.getPassword(), umpUserModel.getUserName());
    }
}
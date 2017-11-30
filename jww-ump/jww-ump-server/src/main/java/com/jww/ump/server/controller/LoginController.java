package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.LoginException;
import com.jww.common.core.model.LoginModel;
import com.jww.common.core.util.SecurityUtil;
import com.jww.common.web.BaseController;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * 登陆控制器
 *
 * @author waner
 * @create 2017-11-30
 **/
@Slf4j
@RestController
public class LoginController extends BaseController {

    /**
     * 登陆
     *
     * @param loginModel
     * @return ResultModel
     * @author wanyong
     * @date 2017-11-30 16:14
     */
    @PostMapping("/login")
    public ResultModel login(@RequestBody LoginModel loginModel) {
        log.info(loginModel.toString());
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginModel.getAccount(), SecurityUtil.encryptPassword(loginModel.getPassword()));
        usernamePasswordToken.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (LockedAccountException e) {
            throw new LoginException(Constants.ResultCodeEnum.LOGIN_FAIL_ACCOUNT_LOCKED.getMessage(), e);
        } catch (DisabledAccountException e) {
            throw new LoginException(Constants.ResultCodeEnum.LOGIN_FAIL_ACCOUNT_DISABLED.getMessage(), e);
        } catch (ExpiredCredentialsException e) {
            throw new LoginException(Constants.ResultCodeEnum.LOGIN_FAIL_ACCOUNT_EXPIRED.getMessage(), e);
        } catch (UnknownAccountException e) {
            throw new LoginException(Constants.ResultCodeEnum.LOGIN_FAIL_ACCOUNT_UNKNOWN.getMessage(), e);
        } catch (IncorrectCredentialsException e) {
            throw new LoginException(Constants.ResultCodeEnum.LOGIN_FAIL_INCORRECT_CREDENTIALS.getMessage(), e);
        } catch (Exception e) {
            throw new LoginException(e);
        }
        return ResultUtil.ok();
    }

    /**
     * 未登陆
     *
     * @return ResultModel
     * @author wanyong
     * @date 2017-11-30 16:03
     */
    @RequestMapping(value = "/unlogin", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    public ResultModel unlogin() {
        return ResultUtil.fail(Constants.ResultCodeEnum.UNLOGIN);
    }

    /**
     * 未授权
     *
     * @return ResultModel
     * @author wanyong
     * @date 2017-11-30 16:03
     */
    @RequestMapping(value = "/unauthorized", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    public ResultModel unauthorized() {
        return ResultUtil.fail(Constants.ResultCodeEnum.UNAUTHORIZED);
    }
}

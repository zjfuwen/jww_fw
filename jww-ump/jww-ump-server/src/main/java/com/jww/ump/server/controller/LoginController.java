package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.LoginException;
import com.jww.common.core.model.LoginModel;
import com.jww.common.core.util.SecurityUtil;
import com.jww.common.redis.util.CacheUtil;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import com.xiaoleilu.hutool.captcha.CaptchaUtil;
import com.xiaoleilu.hutool.captcha.CircleCaptcha;
import com.xiaoleilu.hutool.lang.Base64;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆控制器
 *
 * @author waner
 * @create 2017-11-30
 **/
@Slf4j
@RestController
public class LoginController extends BaseController {

    @Autowired
    private UmpUserService umpUserService;

    /**
     * 获取验证码
     *
     * @param captchaId 验证码ID
     * @return ResultModel
     * @author wanyong
     * @date 2017-12-27 21:10
     */
    @GetMapping("/captcha/{captchaId}")
    public ResultModel queryCaptcha(@PathVariable(value = "captchaId", required = false) String captchaId) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 20);
        captcha.createCode();
        if (StrUtil.isBlank(captchaId) || !CacheUtil.getCache().exists(Constants.CAPTCHA_CACHE_NAMESPACE + captchaId)) {
            captchaId = RandomUtil.randomUUID();
        }
        CacheUtil.getCache().set(Constants.CAPTCHA_CACHE_NAMESPACE + captchaId, captcha.getCode(), 120);
        captcha.write(outputStream);
        Map<String, String> map = new HashMap(2);
        map.put("captchaId", captchaId);
        map.put("captcha", Base64.encode(outputStream.toByteArray()));
        return ResultUtil.ok(map);
    }

    /**
     * 登陆
     *
     * @param loginModel
     * @return ResultModel
     * @author wanyong
     * @date 2017-11-30 16:14
     */
    @PostMapping("/login")
    public ResultModel login(@Valid @RequestBody LoginModel loginModel) {
        // 校验验证码
        String redisCaptchaValue = (String) CacheUtil.getCache().get(Constants.CAPTCHA_CACHE_NAMESPACE + loginModel.getCaptchaId());
        if (StrUtil.isBlank(redisCaptchaValue)) {
            throw new LoginException(Constants.ResultCodeEnum.LOGIN_FAIL_CAPTCHA_ERROR.getMessage());
        }
        if (!redisCaptchaValue.equals(loginModel.getCaptchaValue())) {
            throw new LoginException(Constants.ResultCodeEnum.LOGIN_FAIL_CAPTCHA_ERROR.getMessage());
        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginModel.getAccount(), SecurityUtil.encryptPassword(loginModel.getPassword()));
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
        UmpUserModel crrentUser = (UmpUserModel) subject.getPrincipal();
        // 验证通过，返回前端所需的用户信息
        UmpUserModel umpUserModel = new UmpUserModel();
        umpUserModel.setId(crrentUser.getId());
        umpUserModel.setAccount(crrentUser.getAccount());
        umpUserModel.setUserName(crrentUser.getUserName());
        umpUserModel.setAvatar(crrentUser.getAvatar());
        return ResultUtil.ok(umpUserModel);
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

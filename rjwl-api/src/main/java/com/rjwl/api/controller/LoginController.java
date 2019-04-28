package com.rjwl.api.controller;

import com.rjwl.api.common.enums.JsonCodeEnum;
import com.rjwl.api.common.json.JsonResp;
import com.rjwl.api.common.utils.MobileUtil;
import com.rjwl.api.entity.dto.LoginDto;
import com.rjwl.api.service.AdminService;
import com.rjwl.api.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author aniu
 */
@RestController
@RequestMapping("/api/login")
@Api(value = "login", description = "登录相关服务")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService longinService;
    @Autowired
    private AdminService adminService;

    /**
     * 后台登录
     *
     * @param loginParam 可能是手机号或用户名
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/managelogin")
    @ApiOperation(value = "用户登录", notes = "用户登录传adminName,password")
    public JsonResp<?> manageLogin(@ApiParam(name = "loginParam", value = "登录", required = true) @RequestBody LoginDto loginParam) {
        LOGGER.info("用户登录:用户名{} 密码{}",loginParam.getAdminName(),loginParam.getPassword());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getAdminName(), loginParam.getPassword());
        subject.login(token);
        return JsonResp.success("登陆成功");
    }

    /**
     * 渠道登录
     *
     * @param loginParam
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/channellogin")
    @ApiOperation(value = "渠道登录", notes = "渠道登录传adminName,password")
    public JsonResp<?> channelLogin(@ApiParam(name = "loginParam", value = "登录", required = true) @RequestBody LoginDto loginParam) {
        LOGGER.info("渠道用户登录:用户名{} 密码{}",loginParam.getAdminName(),loginParam.getPassword());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getAdminName(), loginParam.getPassword());
        subject.login(token);
        return JsonResp.success("登陆成功");
    }

    /**
     * 请先登录
     *
     * @return
     */
    @GetMapping(value = "/loginAgain")
    public JsonResp<?> loginAgain() {
        return new JsonResp(JsonCodeEnum.LOGIN_FIRST);
    }

    /**
     * 接口没有权限响应
     *
     * @return
     */
    @GetMapping(value = "/unauthorized_err")
    public JsonResp<?> unauthorizedErr() {
        return new JsonResp(JsonCodeEnum.NO_AUTHORIZATION);
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping(value = "/logout_success")
    public JsonResp<?> logout() {
        return new JsonResp(JsonCodeEnum.LOGOUT_SUCCESS, null, true);
    }

    /**
     * 验证码获取
     *
     * @return
     */
    @GetMapping(value = "/verifycode")
    @ApiOperation(value = "登录获取验证码", notes = "登录获取验证码")
    public JsonResp<?> verifyCode(@ApiParam(name = "phone", value = "电话", required = true) @RequestParam("phone") String phone) {
        if (!MobileUtil.isPhoneBase(phone)) {
            return JsonResp.fail("手机号码不合法");
        }
        if (null == adminService.selectAdminByName(phone)) {
            //用户名不存在
            return new JsonResp(JsonCodeEnum.UNKNOWN_ACCOUNT);
        }
        if (longinService.verifyCode(phone)) {
            return JsonResp.success("验证码发送成功");
        }
        return JsonResp.fail("验证码发送失败");
    }
}

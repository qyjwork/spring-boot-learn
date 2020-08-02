package cn.imacoder.userauth.controller;

import cn.imacoder.userauth.annotation.IgnoreAuth;
import cn.imacoder.userauth.entities.LoginReq;
import cn.imacoder.userauth.entities.LoginResp;
import cn.imacoder.userauth.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"登录接口"})
@RestController
@RequestMapping("/login")
public class LoginController {


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @IgnoreAuth
    public LoginResp Login(@RequestBody LoginReq req) {
        String token = JWTUtils.createJWT(req.getUserId(), req.getPassword());
        return new LoginResp(token, JWTUtils.TOKEN_EXPIRETOME);
    }
}

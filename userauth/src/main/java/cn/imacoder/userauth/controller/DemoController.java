package cn.imacoder.userauth.controller;


import cn.imacoder.userauth.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"示例接口"})
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation(value = "示例GET方法")
    @GetMapping("")
    public String Index(){
        return "this is userauth api !!!";
    }

}

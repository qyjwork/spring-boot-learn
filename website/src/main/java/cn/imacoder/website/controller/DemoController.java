package cn.imacoder.website.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "示例接口", tags = {"示例接口"})
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation(value = "GET方法")
    @GetMapping("")
    public String Index(){
        return "this is website";
    }
}

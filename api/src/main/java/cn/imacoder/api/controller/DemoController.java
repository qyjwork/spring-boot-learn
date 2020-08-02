package cn.imacoder.api.controller;


import cn.imacoder.api.models.DemoReq;
import cn.imacoder.api.models.DemoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(value = "示例接口", tags = {"示例接口"})
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation(value = "GET方法")
    @GetMapping("")
    public String Index() {
        return "this is api !!!";
    }

    @ApiOperation(value = "Post方法示例")
    @PostMapping("/postMethod")
    public DemoResp PostMethod(@RequestBody DemoReq req) {
        return new DemoResp();
    }

}

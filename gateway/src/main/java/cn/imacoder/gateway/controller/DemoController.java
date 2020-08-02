package cn.imacoder.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final static Logger logger = LoggerFactory.getLogger(FallbackController.class);

    @RequestMapping("")
    public String Index() {
        return "this is gateway !!! ";
    }
}

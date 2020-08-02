package cn.imacoder.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    private final static Logger logger = LoggerFactory.getLogger(FallbackController.class);


    @RequestMapping("")
    public ResponseEntity fallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("服务暂不可用！！！！");
    }
}

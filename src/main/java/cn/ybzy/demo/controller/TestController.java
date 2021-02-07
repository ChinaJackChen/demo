package cn.ybzy.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CJ
 * @description Test
 * @create 2021-02-05
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Hello World !";
    }
}

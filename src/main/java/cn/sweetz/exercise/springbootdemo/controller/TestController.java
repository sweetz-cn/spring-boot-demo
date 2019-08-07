package cn.sweetz.exercise.springbootdemo.controller;

import cn.sweetz.exercise.springbootdemo.property.TestProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${test.maven.profile}")
    private String profile;

    @Resource
    private TestProperties testProperties;

    @GetMapping("/profile")
    public String getProfile(){
        return profile;
    }

    @GetMapping("/properties")
    public String getTestProperties(){
        return testProperties.toString();
    }
}

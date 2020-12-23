package com.guns21.example.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "a-service", url = "http://localhost:8080")
public interface Client {

    @RequestMapping(method= RequestMethod.GET, value = "/a")
    String get(@RequestParam("id") String id);
}
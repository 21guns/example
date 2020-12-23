package com.guns21.example.feign.controller;

import com.guns21.example.feign.service.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jliu
 * @date 2020/6/10
 */
@Slf4j
@RestController
@RequestMapping("/b")
public class BController {
    @Autowired
    private Client client;
    @GetMapping
    public String get(@RequestParam("id") String id) {
        log.info("get id {}", id);
        client.get(id);
        return "B ok " + id;
    }
}

package com.guns21.example.sharding.spring.boot;

import com.guns21.example.sharding.spring.boot.entity.OrderDO;
import com.guns21.example.sharding.spring.boot.repository.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Collections;
import java.util.Locale;

@ShellComponent
@Slf4j
public class TranslationCommands {

    @Autowired
    private OrderMapper orderMapper;

    // translate "hello world!" --from en_US --to fr_FR
    @ShellMethod("Translate text from one language to another.")
    public String translate(
      @ShellOption(defaultValue = "en_US")  String text,
      @ShellOption(defaultValue = "en_US") Locale from,
      @ShellOption(defaultValue = "en_US")  Locale to) {
      // invoke service
      return "";
    }

    @ShellMethod("Translate text from one language to another.")
    public String add() {
        // invoke service
        OrderDO orderDO = OrderDO.builder().no("sdfsd").build();
        orderDO.preCreate();
        orderMapper.addOrder(orderDO);
        return "ok";
    }
    @ShellMethod("Translate text from one language to another.")
    public String get() {
        // invoke service
        System.err.println(orderMapper.getOrders());
        return "ddok";
    }
}
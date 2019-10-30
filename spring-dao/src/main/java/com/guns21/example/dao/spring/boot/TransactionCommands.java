package com.guns21.example.dao.spring.boot;

import com.guns21.example.dao.spring.boot.entity.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;

@ShellComponent
@Slf4j
public class TransactionCommands {

    @Autowired
    private TransactionalService transactionalService;

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
        OrderDO orderDO = new OrderDO();
        orderDO.setNo("dddd");
        orderDO.preCreate();
        try {
            transactionalService.save(orderDO);
//            transactionalService.exceptionSave(orderDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
    @ShellMethod("Translate text from one language to another.")
    public String get() {
        // invoke service
        System.err.println(transactionalService.getOrders());
        return "ddok";
    }
}
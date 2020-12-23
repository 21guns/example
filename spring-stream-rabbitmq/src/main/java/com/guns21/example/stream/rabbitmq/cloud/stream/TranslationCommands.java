package com.guns21.example.stream.rabbitmq.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;

@ShellComponent
@Slf4j
public class TranslationCommands {

    private  TranslationService service;

    @Autowired
    public TranslationCommands(TranslationService service) {
      this.service = service;
    }

    // translate "hello world!" --from en_US --to fr_FR
    @ShellMethod("Translate text from one language to another.")
    public String translate(
      @ShellOption(defaultValue = "en_US")  String text,
      @ShellOption(defaultValue = "en_US") Locale from,
      @ShellOption(defaultValue = "en_US")  Locale to) {
      // invoke service
        log.info("translate");
      return service.translate(text, from, to);
    }

    @ShellMethod("Translate text from one language to another.")
    public String start() {
        // invoke service
        return service.start();
    }
    @ShellMethod("Translate text from one language to another.")
    public String stop() {
        // invoke service
        return service.stop();
    }
}
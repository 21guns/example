package com.guns21.example.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;

@ShellComponent
public class TranslationCommands {

    private  TranslationService service;

    @Autowired
    public TranslationCommands(TranslationService service) {
      this.service = service;
    }

    // translate "hello world!" --from en_US --to fr_FR
    @ShellMethod("Translate text from one language to another.")
    public String translate(
      @ShellOption String text,
      @ShellOption(defaultValue = "en_US") Locale from,
      @ShellOption Locale to) {
      // invoke service
      return service.translate(text, from, to);
    }


}
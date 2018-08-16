package com.guns21.example.spring.cloud;

import com.guns21.cloud.event.EventProcessor;
import com.guns21.event.EventBus;
import com.guns21.example.spring.cloud.event.AddEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class TranslationService {
    private static final Logger logger = LoggerFactory.getLogger(EventProcessor.class);
    @Autowired
    private EventBus eventBus;

    public String translate(String text, Locale from, Locale to) {

        eventBus.publish(new AddEvent(text));
        return text + ":" + from + "-->" + to;
    }

}
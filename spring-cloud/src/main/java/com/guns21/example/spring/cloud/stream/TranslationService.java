package com.guns21.example.spring.cloud.stream;

import com.guns21.cloud.event.EventProcessor;
import com.guns21.event.EventBus;
import com.guns21.example.spring.cloud.stream.event.UpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

public class TranslationService {
    private static final Logger logger = LoggerFactory.getLogger(EventProcessor.class);
    @Autowired
    private EventBus eventBus;
    @Autowired
    private Validator validator;
    public String translate(String text, Locale from, Locale to) {

//        eventBus.publish(new AddEvent(text));
        /*
         * https://github.com/kolorobot/spring-mvc-beanvalidation11-demo
         */
        UpdateEvent updateEvent = new UpdateEvent(EventDTO.builder().build());
        BindException bindException = new BindException(updateEvent, "updateEvent");
        ValidationUtils.invokeValidator(validator, updateEvent.getSource(),bindException );

        System.err.println(bindException);
        eventBus.publish(updateEvent);


//        eventBus.publish(new AddEvent(text),"aa-event");
        return text + ":" + from + "-->" + to;
    }

}
package com.guns21.example.spring.cloud.stream;

import com.guns21.example.spring.cloud.stream.config.EventClient;
import com.guns21.example.spring.cloud.stream.event.UpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.Locale;
import java.util.Set;

public class TranslationService {
    private static final Logger logger = LoggerFactory.getLogger(TranslationService.class);
    @Autowired
    private Validator validator;

    @Autowired
    private EventClient busClient;

    @Autowired
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    public String translate(String text, Locale from, Locale to) {

//        eventBus.publish(new AddEvent(text));
        /*
         * https://github.com/kolorobot/spring-mvc-beanvalidation11-demo
         */
        UpdateEvent updateEvent = new UpdateEvent(EventDTO.builder().name(text).build());
        BindException bindException = new BindException(updateEvent, "updateEvent");
        ValidationUtils.invokeValidator(validator, updateEvent.getSource(),bindException );

//        System.err.println(bindException);
//        eventBus.publish(updateEvent);
        for (int i = 0; i < 10000; i++) {
            busClient.output().send(updateEvent.toMessage());
//            busClient.output().send(new UpdateEvent(EventDTO.builder().name("1").build()).toMessage());
//            busClient.output().send(updateEvent.toMessage());
//            busClient.output().send(updateEvent.toMessage());
//            busClient.output().send(updateEvent.toMessage());
//            busClient.output().send(updateEvent.toMessage());

        }
//        throw new RuntimeException("");
//        eventBus.publish(new AddEvent(text),"aa-event");
        return text + ":" + from + "-->" + to;
    }

    public String start() {
        Set<String> listenerContainerIds = rabbitListenerEndpointRegistry.getListenerContainerIds();
        Collection<MessageListenerContainer> listenerContainers = rabbitListenerEndpointRegistry.getListenerContainers();
        return "";
    }
}
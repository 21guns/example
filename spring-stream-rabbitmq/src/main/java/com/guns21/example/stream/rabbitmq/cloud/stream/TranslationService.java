package com.guns21.example.stream.rabbitmq.cloud.stream;

import com.guns21.cloud.event.stream.EventWraper;
import com.guns21.event.domain.BaseEvent;
import com.guns21.example.stream.rabbitmq.cloud.stream.config.EventClient;
import com.guns21.example.stream.rabbitmq.cloud.stream.event.AddEvent;
import com.guns21.example.stream.rabbitmq.cloud.stream.event.UpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

import static com.guns21.cloud.event.EventConstant.EVENT_TYPE;

public class TranslationService {
    private static final Logger logger = LoggerFactory.getLogger(TranslationService.class);
    @Autowired
    private Validator validator;

    @Autowired
    private EventClient busClient;

    @Autowired
    private BindingService bindingService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        for (int i = 0; i < 1; i++) {
//            busClient.output().send(updateEvent.toMessage());
            busClient.output().send(toMessage(new UpdateEvent(EventDTO.builder().name("a").build())));
            busClient.output().send(toMessage(new UpdateEvent(EventDTO.builder().name("b").build())));
            busClient.output().send(toMessage(new AddEvent(EventDTO.builder().name("b").build())));
            busClient.output().send(toMessage(new AddEvent(EventDTO.builder().name("b").build())));
//            rabbitTemplate.send("service-event","a",new UpdateEvent(EventDTO.builder().name("b").build()).toMessage());

        }
//        throw new RuntimeException("");
//        eventBus.publish(new AddEvent(text),"aa-event");
        return text + ":" + from + "-->" + to;
    }

    public String start() {
//        Set<String> listenerContainerIds = rabbitListenerEndpointRegistry.getListenerContainerIds();
//        Collection<MessageListenerContainer> listenerContainers = rabbitListenerEndpointRegistry.getListenerContainers();
        bindingService.bindConsumer(busClient.input(), EventClient.INPUT);
        return "";
    }

    public String stop() {
        bindingService.unbindConsumers(EventClient.INPUT);
        return "";
    }


    public Message toMessage(BaseEvent<EventDTO> baseEvent) {
        return EventWraper.messageBuilder(baseEvent)
                .setHeader("type", baseEvent.getSource().getName())
                .build();
    }
}
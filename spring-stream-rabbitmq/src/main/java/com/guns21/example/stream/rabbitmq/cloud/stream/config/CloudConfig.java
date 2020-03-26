package com.guns21.example.stream.rabbitmq.cloud.stream.config;

import com.guns21.example.stream.rabbitmq.cloud.stream.TranslationService;
import lombok.Getter;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.classify.util.MethodInvokerUtils;
import org.springframework.cloud.stream.binder.*;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitExtendedBindingProperties;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.cloud.stream.config.ChannelBindingAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.util.TypeUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@Import(ChannelBindingAutoConfiguration.class)

public class CloudConfig {

    @Autowired
    private AmqpAdmin amqp;
    @Autowired
    private BindingServiceProperties bindingServiceProperties;
    @Bean
    public TranslationService translationService() {
        return new TranslationService();
    }

//    @Bean
//    public Binding extraBinding1(AmqpAdmin amqp /*this amqp params is required*/
//        , @Value("${spring.cloud.stream.bindings.a-event-output.destination}") String exchange
//        , @Value("${spring.cloud.stream.bindings.a-event-input.group}") String destination) {
//        return new Binding(destination, Binding.DestinationType.QUEUE,
//                exchange,"#",null);
//    }
    @Autowired
    private List<ReplaceBinding> replaceBindings;

    @Bean
    public ReplaceBinding extraBinding1() {
        return new ReplaceBinding("a-event-input", "a-input", Binding.DestinationType.QUEUE,
                "a-output", "#", null);
    }

    /**
     * 替换exchange 和 queue的绑定关系
     * 原因：当使用配置进行多个exchange和一个queue进行绑定值，如果指定binding-routing-key=a,
     *       那么这些个exchange和queue绑定的routing-key都是a
     *
     * 使用 ReplaceBinding 声明需要替换的binding关系
     *  ReplaceBinding.bindingName = 配置文件中spring.cloud.stream.<input>
     *  ReplaceBinding.destination = queue name
     *  ReplaceBinding.exchange = exchange name
     */
    @EventListener
    public void onApplicationEvent(BindingCreatedEvent event) {
        Object source = event.getSource();

        if (source instanceof DefaultBinding) {
            DefaultBinding binding = (DefaultBinding) source;

            replaceBindings.stream()
                    .filter(replaceBinding -> replaceBinding.getBindingName().equals(binding.getBindingName()))
                    .forEach(replaceBinding -> {
                        try {
                            Object destination = FieldUtils.readField(binding, "val$destination", true);
                            Binding  existingBinding = (Binding) FieldUtils.readField(destination, "binding", true);
                            if (existingBinding.getDestination().equals(replaceBinding.getDestination())
                                && existingBinding.getExchange().equals(replaceBinding.getExchange())) {
                                amqp.removeBinding(existingBinding);
                                amqp.declareBinding(replaceBinding.toBinding());
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    });

            if (binding.getBindingName().equals("a-event-input")) {
                ConsumerProperties consumerProperties = bindingServiceProperties.getConsumerProperties("a-event-input");
                Map extendedInfo = binding.getExtendedInfo();
                System.err.println(extendedInfo);
                System.err.println( bindingServiceProperties.getBindingProperties("a-event-input"));
                System.err.println(consumerProperties);
                System.err.println( bindingServiceProperties.getBinder("a-event-input"));
                System.err.println( binding.getName());
            }
        } else {
            System.err.println("error type " + source.getClass());
        }
    }

    @Getter
    public static class ReplaceBinding {
        private String bindingName;
        private Binding binding;
        public ReplaceBinding(String bindingName, String destination, Binding.DestinationType destinationType, String exchange, String routingKey, Map<String, Object> arguments) {
            binding = new Binding(destination, destinationType, exchange, routingKey, arguments);
            this.bindingName = bindingName;
        }

        public String getDestination() {
            return binding == null ? null : binding.getDestination();
        }

        public String getExchange() {
            return binding == null ? null : binding.getExchange();
        }

        public Binding toBinding() {
            return binding;
        }
    }
//     Queue 和Exchange 并不在Spring main context 而是在RabbitExchangeQueueProvisioner.autoDeclareContext
//    @Bean
//    public Binding binding(Queue queue) {
//        return BindingBuilder
//                .bind(boundExchange())
//                .to(destinatioExchange())
//                .with("myRoutingKey");
//    }
}

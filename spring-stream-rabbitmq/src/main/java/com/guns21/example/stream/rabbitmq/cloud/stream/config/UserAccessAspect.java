package com.guns21.example.stream.rabbitmq.cloud.stream.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//@Aspect
//@Configuration
public class UserAccessAspect {

	private Logger logger = LoggerFactory.getLogger("Event Log Consuming");
	
	//What kind of method calls I would intercept
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
//	@Before("execution(* com.guns21.example.spring.cloud.stream.event.*.toMessage())")
//	@Before("target(org.springframework.messaging.MessageChannel)")
//	@Around("@annotation(org.springframework.cloud.stream.annotation.Output)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		//Advice
		Object retVal = joinPoint.proceed();
		logger.info(" around execution for {}", retVal);
		return retVal;
	}

//	@After("execution(* org.springframework.integration.channel.AbstractMessageChannel+.send(*))")
	public void send(JoinPoint joinPoint) {
		//Advice
		logger.info(" send execution for {}", joinPoint.getArgs());
	}
//	@Before("execution(* com.guns21.example.spring.cloud.stream.consumers.*Consumer.*(..))")
	@Before("@annotation(org.springframework.cloud.stream.annotation.StreamListener)")
	public void consumer(JoinPoint joinPoint){
		//Advice
		logger.info("on consumer {} receive message: {} ", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getArgs());
	}
//	@Bean
//	@GlobalChannelInterceptor(patterns = "*-input")
	public ChannelInterceptor messageInputChannelInterceptor() {
		ChannelInterceptor channelInterceptor = new ChannelInterceptor() {
			private Logger logger = LoggerFactory.getLogger("Event Log Consuming");
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				/**
				 * Message payload is byte can't to Json
				 */
				logger.info("on channel {} receive message: {} ", channel.toString(), JSON.toJSONString((Object)message.getPayload()));
				return message;
			}

		};
		return channelInterceptor;
	}

	//stream message converter
//	@Bean
//	@StreamMessageConverter
//	public MessageConverter customMessageConverter() {
//		return new EventInputLogging();
//	}
//
//	class EventInputLogging extends ApplicationJsonMessageMarshallingConverter {
//
//	}

//	@Bean
//	@GlobalChannelInterceptor(patterns = "*-output", order = -1)
	public ChannelInterceptor messageOutputChannelInterceptor() {

		ChannelInterceptor channelInterceptor = new ChannelInterceptor() {
			@Autowired
			private Validator validator;

			private Logger logger = LoggerFactory.getLogger("Event Log Producing");
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				logger.info("on channel {} send message: {} ",channel.toString(), message.getPayload());
				BindException bindException = new BindException(message.getPayload(), message.getPayload().getClass().getSimpleName());
				ValidationUtils.invokeValidator(validator, message.getPayload(),bindException );

				System.err.println(bindException);
				return message;
			}
		};
		return channelInterceptor;
	}
}


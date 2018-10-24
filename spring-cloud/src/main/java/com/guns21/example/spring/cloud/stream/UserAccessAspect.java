package com.guns21.example.spring.cloud.stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

@Aspect
@Configuration
public class UserAccessAspect {
	
	private Logger logger = LoggerFactory.getLogger("EventLog");
	
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
//	@Before("@annotation(org.springframework.cloud.stream.annotation.StreamListener)")
	public void consumer(JoinPoint joinPoint){
		//Advice
		logger.info(" consumer {} execution for {}", joinPoint.getTarget().getClass(), joinPoint.getArgs());
	}

	@Bean
	@GlobalChannelInterceptor(patterns = "*-input")
	public ChannelInterceptor messageInputChannelInterceptor() {
		ChannelInterceptor channelInterceptor = new ChannelInterceptor() {

			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				logger.info("Event {} for channel {} ",message, channel.toString());
				return message;
			}

		};
		return channelInterceptor;
	}
	@Bean
	@GlobalChannelInterceptor(patterns = "*-output")
	public ChannelInterceptor messageOutputChannelInterceptor() {
		ChannelInterceptor channelInterceptor = new ChannelInterceptor() {

			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				logger.info("Event {} for channel {} ",message, channel.toString());
				return message;
			}

		};
		return channelInterceptor;
	}
}
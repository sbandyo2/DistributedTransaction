package com.ibm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.config.ApplicationConfigReader;
import com.ibm.dto.SupplierInfo;
import com.ibm.rabbitmq.MessageSender;
import com.ibm.utils.ApplicationConstant;

@RestController
@RequestMapping(path = "/suppliervalidation")
public class ValidationController {
	
	private static final Logger log = LoggerFactory.getLogger(ValidationController.class);

	private final RabbitTemplate rabbitTemplate;
	private ApplicationConfigReader applicationConfig;
	private MessageSender messageSender;

	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	@Autowired
	public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@Autowired
	public ValidationController(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	@Autowired
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}


	@RequestMapping(path = "/validate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendMessage(@RequestBody SupplierInfo user) {

		log.info("<<<<<<<<<<<<<<<<<< in order service");
		String exchange = getApplicationConfig().getApp2Exchange();
		String routingKey = getApplicationConfig().getApp2RoutingKey();

		/* Sending to Message Queue */
		try {
			messageSender.sendMessage(rabbitTemplate, exchange, routingKey, user);
			return ApplicationConstant.IN_QUEUE + " " + user.getEventId();
			
		} catch (Exception ex) {
			log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return ApplicationConstant.MESSAGE_QUEUE_SEND_ERROR+ " " + user.getEventId();
		}

	}

}

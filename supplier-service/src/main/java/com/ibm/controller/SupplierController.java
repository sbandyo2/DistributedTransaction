package com.ibm.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.config.ApplicationConfigReader;
import com.ibm.dto.SupplierInfo;
import com.ibm.dto.TransactionDto;
import com.ibm.rabbitmq.MessageSender;
import com.ibm.utils.ApplicationConstant;
import com.ibm.utils.Utils;

@RestController
@RequestMapping(path = "/supplierservice")
public class SupplierController {
	
	private static final Logger log = LoggerFactory.getLogger(SupplierController.class);

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
	public SupplierController(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	@Autowired
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}


	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMessage(@RequestBody SupplierInfo user) {

		String exchange = getApplicationConfig().getApp1Exchange();
		String routingKey = getApplicationConfig().getApp1RoutingKey();
		TransactionDto transactionDto = new TransactionDto();
		/* Sending to Message Queue */
		try {
			user.setEventId(ApplicationConstant.SAVE);
			user.setTransactionId(String.valueOf(Utils.randomAlphaNumeric(10)));
			
			transactionDto.setEventId(user.getEventId());
			transactionDto.setTransactionId(user.getTransactionId());
			transactionDto.setData(user);
			messageSender.sendMessage(rabbitTemplate, exchange, routingKey, transactionDto);
			
			String uri = "http://localhost:9103/suppliervalidation/validate";
			RestTemplate restTemplate = new RestTemplate();
		    String result = restTemplate.postForObject(uri, user,String.class);
			return new ResponseEntity<String>(ApplicationConstant.IN_QUEUE, HttpStatus.OK);
			
		} catch (Exception ex) {
			log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return new ResponseEntity(ApplicationConstant.MESSAGE_QUEUE_SEND_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

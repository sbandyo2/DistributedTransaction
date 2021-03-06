package com.ibm.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.ibm.config.ApplicationConfigReader;
import com.ibm.dto.TransactionDto;
import com.ibm.transaction.TransactionManager;
import com.ibm.utils.ApplicationConstant;

/**
 * Message Listener for RabbitMQ
 * @author deepak.af.kumar
 *
 */

@Service
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    
    @Autowired
    ApplicationConfigReader applicationConfigReader;

    @Autowired
    private TransactionManager transactionManager = null;
    
    /**
     * Message listener for app1
     * @param CustomerDetails a user defined object used for deserialization of message
     */
    @RabbitListener(queues = "${app1.queue.name}")
    public void receiveMessageForApp1(TransactionDto reqObj) {
    	//log.info("Received message: {} from app1 queue.", reqObj);
    	log.info("Received message from app1 queue "+reqObj.getData().toString());    	
    	try {
    		log.info("Making REST call to the API");
    		//insert the record if the data is valid
    		String state = String.valueOf( reqObj.getEventId());
    		String transactionID = String.valueOf(reqObj.getTransactionId());
    		
    		if(ApplicationConstant.COMMIT.equalsIgnoreCase(state)){
    			//dbOperations.insertSupplierInfo(reqObj.getData());
    			
    			log.info("<< commiting for transaction.. "+ transactionID);
    		}else if(ApplicationConstant.SAVE.equalsIgnoreCase(state)) {
    			
    			log.info("<< saving but not commiting for transaction.. "+transactionID);
    		}else if(ApplicationConstant.ROLLBACK.equalsIgnoreCase(state)){
    			log.info("<< rolliback for transaction.. "+transactionID);
    		}
    		
    		
        	log.info("<< Exiting receiveMessageCrawlCI() after API call.");
    	} catch(HttpClientErrorException  ex) {
    		
    		if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        		log.info("Delay...");
        		try {
    				Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
    			} catch (InterruptedException e) { }
    			
    			log.info("Throwing exception so that message will be requed in the queue.");
    			// Note: Typically Application specific exception should be thrown below
    			throw new RuntimeException();
    		} else {
    			throw new AmqpRejectAndDontRequeueException(ex); 
    		}
    		
    	} catch(Exception e) {
    		log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
    		throw new AmqpRejectAndDontRequeueException(e); 
    	}

    }
    
    
    /**
     * Message listener for app2
     * 
     */
    
    
//    @RabbitListener(queues = "${app2.queue.name}")
//    public void receiveMessageForApp2(Object reqObj) {
//    	log.info("Received message: {} from app2 queue.", reqObj);
//    	
//    	try {
//    		log.info("Making REST call to the API");
//    		
//    		//insert the record if the data is valid
//    		if(ApplicationConstant.COMMIT.equalsIgnoreCase(String.valueOf(Utils.getValue("eventId", new JSONObject(reqObj))))){
//    			dbOperations.insertSupplierInfo(reqObj);	
//    		}else {
//    			log.info("The supplier data is not valid for entry...");
//    		}
//    		
//    		
//        	log.info("<< Exiting receiveMessageCrawlCI() after API call.");
//    	} catch(HttpClientErrorException  ex) {
//    		
//    		if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
//        		log.info("Delay...");
//        		try {
//    				Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
//    			} catch (InterruptedException e) { }
//    			
//    			log.info("Throwing exception so that message will be requed in the queue.");
//    			// Note: Typically Application specific exception can be thrown below
//    			throw new RuntimeException();
//    		} else {
//    			throw new AmqpRejectAndDontRequeueException(ex); 
//    		}
//    		
//    	} catch(Exception e) {
//    		log.error("Internal server error occurred in python server. Bypassing message requeue {}", e);
//    		throw new AmqpRejectAndDontRequeueException(e); 
//    	}
//
//    }


}

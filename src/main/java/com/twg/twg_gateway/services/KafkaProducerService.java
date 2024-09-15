package com.twg.twg_gateway.services;


import com.twg.twg_gateway.models.TransactionMessage;
//import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
//import java.util.logging.Logge

@Service
public class KafkaProducerService {

//    private final Logger LOGGER= LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    KafkaTemplate<UUID, TransactionMessage> kafkaTemplate;


    public void send(String topicName, UUID key, TransactionMessage transactionMessage){
        var future = kafkaTemplate.send(topicName,key,transactionMessage);
        //will hold result of async operation
        //like promise
        future.whenComplete((sendResult,exception)->{
            if(exception!=null)
            {
                System.out.println(exception.getMessage());
//                LOGGER.log(exception.getMessage());
                future.completeExceptionally(exception);
            }
            else {
                future.complete(sendResult);
            }
            //Lombok plugin has to be installed on intelliJin order to invoke getters and setters
//            LOGGER.info("The id is:  " + transactionMessage.getTransactionId()+
//                    "TRANSACTION STATUS SENT TO KAFKA TOPIC" + transactionMessage.getStatus());

            System.out.println(transactionMessage.getTransactionId());
        });
    }
}

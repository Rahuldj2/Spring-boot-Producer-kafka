package com.twg.twg_gateway.controller;


import com.twg.twg_gateway.models.TransactionMessage;
import com.twg.twg_gateway.services.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//@RestCon
@RestController
@Slf4j
public class EventController {

    @Autowired
    KafkaProducerService kafkaProducerService;


    @PostMapping("/event")
    ResponseEntity<String> event(@RequestBody TransactionMessage transactionMessage){

        UUID uuid=UUID.randomUUID();
        log.info("We received transaction with key {}", uuid);
        kafkaProducerService.send("transaction-topic", uuid,transactionMessage);

        return ResponseEntity.ok("Sent");
    }
}

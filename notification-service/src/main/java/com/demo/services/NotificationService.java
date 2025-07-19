package com.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface NotificationService {

    public void handleNewJobPost(ConsumerRecord<String, String> record) throws JsonProcessingException;

}

package com.ebay.assignment.example.service;

import com.ebay.assignment.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeEventService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public void announceEmployeeEvent(Employee employee, String eventType) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, topicName);
        headers.put(KafkaHeaders.MESSAGE_KEY, eventType);
        kafkaTemplate.send(new GenericMessage<>(employee, headers));
    }
}
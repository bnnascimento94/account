package com.pingr.accounts.Account;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountConsumerService {

    @KafkaListener(
            topics = "${topic.random-messages}",
            groupId = "consumer_id"
    )
    public void consume(String message) throws IOException {
        System.out.println("Recebi a mensagem:");
        System.out.println(message);
    }

}

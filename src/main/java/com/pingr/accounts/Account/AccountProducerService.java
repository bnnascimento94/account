package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountProducerService {

    @Value(value = "${topic.random-messages}")
    private String topic;

    @Value(value = "${topic.account-updated}")
    private String topicAccountUpdated;

    @Value(value = "${topic.account-deleted}")
    private String topicAccountDeleted;

    @Autowired
    private KafkaTemplate<String, Object> template;


    public void sendCreatedAccount(Account account){
        this.template.send(this.topic, account);
    }


    public void sendUpdatedAccount(Account account){
        this.template.send(this.topicAccountUpdated, account);
    }

    public void sendDeletedAccount(Account account){
        this.template.send(this.topicAccountDeleted, account);
    }


}

package com.techprimers.kafka.springbootkafkaproducerexample.resource;

import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC1 = "acp_catalog";
    private static final String TOPIC2 = "Kafka_Example_json";

    @GetMapping("/{name}")
    public String post(){

        kafkaTemplate.send(TOPIC1, "1234", new User("ajay", "Technology", 12000L));

        return "Published successfully";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public String create(@RequestBody User user){

        kafkaTemplate.send(TOPIC2, user);

        return "Published JSON successfully";
    }
}

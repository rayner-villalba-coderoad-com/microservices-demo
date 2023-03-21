package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;


@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
    public static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);
   //When we use @Autowired it is wired injection
    //@Autowired
    //private TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

   private final StreamRunner streamRunner;
    //Constructor injection we need set final in order to set immutable variable
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData configData,
                                            StreamRunner runner) {
        this.twitterToKafkaServiceConfigData = configData;
        this.streamRunner = runner;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    //Executes after dependency injection to perform initialization
    @PostConstruct
    public void init() {

    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("App starts....");
        LOG.info(Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[] {})));
        LOG.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
        streamRunner.start();
    }
}

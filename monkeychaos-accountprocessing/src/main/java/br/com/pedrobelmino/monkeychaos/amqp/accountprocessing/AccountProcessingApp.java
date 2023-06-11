package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccountProcessingApp {

    public static void main(String[] args) {
        SpringApplication.run(AccountProcessingApp.class, args);
    }

}

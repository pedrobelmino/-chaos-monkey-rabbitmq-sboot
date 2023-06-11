package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.schedule;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.producer.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    public static final int FIXED_DELAY = 5000;
    @Autowired
    private MessageProducer messageProducer;

    @Scheduled(fixedDelay = FIXED_DELAY)
    public void doSomethingAfterStartup() throws JsonProcessingException {
        messageProducer.sendMessage();
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() {
//        messageProducer.sendMessage();
//    }
}

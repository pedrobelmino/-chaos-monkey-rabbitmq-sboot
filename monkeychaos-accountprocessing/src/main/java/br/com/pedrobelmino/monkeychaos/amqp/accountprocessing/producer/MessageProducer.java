package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.producer;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.domain.AccountMessageInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.configuration.AmqpConfiguration.EXCHANGE_MESSAGES;
import static br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.configuration.AmqpConfiguration.QUEUE_MESSAGES;

@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);
    public static final int MAX_RANDOM_NUMBER = 99999;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage() throws JsonProcessingException {
        log.info("Sending message...");
        SecureRandom rand = new SecureRandom();
        rabbitTemplate.convertAndSend(EXCHANGE_MESSAGES, QUEUE_MESSAGES,
                objectMapper.writeValueAsString(
                    AccountMessageInput.builder().document(""+rand.nextInt(MAX_RANDOM_NUMBER)).build()
                )
        );
    }
}
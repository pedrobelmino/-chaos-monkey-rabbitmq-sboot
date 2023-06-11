package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.consumer;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.domain.AccountMessageInput;
import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.exception.BusinessException;
import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.configuration.AmqpConfiguration.QUEUE_MESSAGES;

@Component
@Slf4j
public class AccountMessageConsumer {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = QUEUE_MESSAGES)
    public void receiveMessage(Message message)  {
        log.info("Received message: {}", message.toString());

        try {
            accountService.processAccount(objectMapper.readValue(new String(message.getBody()), AccountMessageInput.class));
        } catch (IOException e) {
            log.error("Error {}", e.getMessage());
        }
    }

}
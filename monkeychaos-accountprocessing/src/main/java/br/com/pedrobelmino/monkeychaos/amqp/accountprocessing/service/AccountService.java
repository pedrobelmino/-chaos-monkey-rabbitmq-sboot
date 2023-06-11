package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.service;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.configuration.AmqpConfiguration;
import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.domain.AccountMessageInput;
import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void processAccount(AccountMessageInput accountMessageInput) throws JsonProcessingException {
        log.info("Account Data Input: {}", accountMessageInput);
        var account = accountRepository.getAccount(accountMessageInput);
        log.info("Account Data Response: {}", account);
        rabbitTemplate.convertAndSend(AmqpConfiguration.EXCHANGE_SUCESS_MESSAGES,null , objectMapper.writeValueAsString(account));
        log.info("Sending to sucess queue: {}", account);
    }
}

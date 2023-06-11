package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.consumer;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.configuration.AmqpConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountDLQConsumer {
    public static final String X_DELAY = "x-delay";
    public static final int MAX_RETRIES_COUNT = 2;
    public static final String HEADER_X_RETRIES_COUNT = "x-retries-count";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = AmqpConfiguration.QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRetryWithParkingLot(Message failedMessage) {
        Integer retriesCnt = (Integer) failedMessage.getMessageProperties().getHeaders().get(HEADER_X_RETRIES_COUNT);
        if (retriesCnt == null)
            retriesCnt = 1;
        if (retriesCnt > MAX_RETRIES_COUNT) {
            log.info("Sending message to the parking lot queue");
            rabbitTemplate.send(AmqpConfiguration.EXCHANGE_PARKING_LOT, failedMessage.getMessageProperties().getReceivedRoutingKey(), failedMessage);
            return;
        }
        log.info("Retrying message for the {} time", retriesCnt);
        failedMessage.getMessageProperties().getHeaders().put(HEADER_X_RETRIES_COUNT, ++retriesCnt);
        rabbitTemplate.send(AmqpConfiguration.EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(), failedMessage);
    }

}

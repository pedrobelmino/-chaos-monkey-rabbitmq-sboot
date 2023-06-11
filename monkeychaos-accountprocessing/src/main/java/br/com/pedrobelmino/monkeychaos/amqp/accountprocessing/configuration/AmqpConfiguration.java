package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqpConfiguration {

    public static final String QUEUE_MESSAGES = "account-messages-queue";
    public static final String QUEUE_MESSAGES_DLQ = QUEUE_MESSAGES + ".dlq";
    public static final String EXCHANGE_MESSAGES = "account-messages-exchange";

    public static final String DLX_EXCHANGE_MESSAGES = QUEUE_MESSAGES + ".dlx";
    public static final String QUEUE_PARKING_LOT = QUEUE_MESSAGES + ".parking-lot";
    public static final String EXCHANGE_PARKING_LOT = QUEUE_MESSAGES + "exchange.parking-lot";

    public static final String EXCHANGE_SUCESS_MESSAGES = "account-sucess-messages-exchange";
    public static final String QUEUE_SUCESS_MESSAGES = "account-sucess-messages-queue";

    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper mapper) {
        final var jsonRabbitTemplate = new RabbitTemplate(connectionFactory);
        jsonRabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
        return jsonRabbitTemplate;
    }

    @Bean
    FanoutExchange parkingLotExchange() {
        return new FanoutExchange(EXCHANGE_PARKING_LOT);
    }

    @Bean
    Queue parkingLotQueue() {
        return QueueBuilder.durable(QUEUE_PARKING_LOT).build();
    }

    @Bean
    Binding parkingLotBinding() {
        return BindingBuilder.bind(parkingLotQueue()).to(parkingLotExchange());
    }

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
          .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_MESSAGES)
          .build();
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange(DLX_EXCHANGE_MESSAGES);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(EXCHANGE_MESSAGES);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_MESSAGES);
    }

    @Bean
    FanoutExchange fanoutSucessExchange() {
        return new FanoutExchange(EXCHANGE_SUCESS_MESSAGES);
    }

    @Bean
    Queue sucessMessagesQueue() {
        return QueueBuilder.durable(QUEUE_SUCESS_MESSAGES)
                .build();
    }

    @Bean
    Binding bindingSucessMessages() {
        return BindingBuilder.bind(sucessMessagesQueue()).to(fanoutSucessExchange());
    }
}

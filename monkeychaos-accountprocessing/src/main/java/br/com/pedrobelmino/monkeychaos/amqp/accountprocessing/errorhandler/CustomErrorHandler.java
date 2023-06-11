package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.errorhandler;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.exception.BusinessException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.util.ErrorHandler;

public class CustomErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        if (!(t.getCause() instanceof BusinessException)) {
            throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", t);
        }
    }
}

package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.errorhandler;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.exception.BusinessException;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;

public class CustomFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
    @Override
    public boolean isFatal(Throwable t) {
        return !(t.getCause() instanceof BusinessException);
    }
}
package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {
    private String document;
    private String agency;
    private String accountNumber;
}

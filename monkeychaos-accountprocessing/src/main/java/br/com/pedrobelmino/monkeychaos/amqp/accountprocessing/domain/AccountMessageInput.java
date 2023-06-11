package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountMessageInput {
    private String document;
}

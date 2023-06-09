package br.com.pedrobelmino.monkeychaos.serviceaccount.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private String document;
    private String agency;
    private String accountNumber;
}

package br.com.pedrobelmino.monkeychaos.serviceaccount.service;

import br.com.pedrobelmino.monkeychaos.serviceaccount.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public static final String DEFAULT_AGENCY_NUMBER = "9999";
    public static final String DEFAULT_ACCOUNT_PREFIX = "1111";

    public Account getAccountByDocument(String document) {

        return Account.builder()
                .document(document)
                .agency(DEFAULT_AGENCY_NUMBER)
                .accountNumber(DEFAULT_ACCOUNT_PREFIX.concat(document))
                .build();
    }
}

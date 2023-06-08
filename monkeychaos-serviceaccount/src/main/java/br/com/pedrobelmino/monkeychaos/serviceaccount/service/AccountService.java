package br.com.pedrobelmino.monkeychaos.serviceaccount.service;

import br.com.pedrobelmino.monkeychaos.serviceaccount.domain.Account;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {

    public Account getAccountByCnpj(String cnpj) {
        return new Account(cnpj, "9999", "9999".concat(cnpj));
    }
}

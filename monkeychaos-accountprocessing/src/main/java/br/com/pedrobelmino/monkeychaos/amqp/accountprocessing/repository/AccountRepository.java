package br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.repository;

import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.domain.Account;
import br.com.pedrobelmino.monkeychaos.amqp.accountprocessing.domain.AccountMessageInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AccountRepository {

    @Autowired
    private RestTemplate restTemplate;

    private final String ACCOUNT_URI = "http://localhost:8081/account/";

    public Account getAccount(AccountMessageInput accountMessageInput){
        ResponseEntity<Account> response = restTemplate.getForEntity(ACCOUNT_URI + "/"+accountMessageInput.getDocument(), Account.class);
        return response.getBody();
    }


}

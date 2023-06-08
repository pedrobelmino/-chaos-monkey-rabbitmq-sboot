package br.com.pedrobelmino.monkeychaos.serviceaccount.controller;

import br.com.pedrobelmino.monkeychaos.serviceaccount.domain.Account;
import br.com.pedrobelmino.monkeychaos.serviceaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired private AccountService accountService;

    @GetMapping("/{cnpj}")
    public ResponseEntity<Account> getAccount(@PathVariable String cnpj) {
        long initTime = System.currentTimeMillis();
        var account = accountService.getAccountByCnpj(cnpj);
        long endTime = System.currentTimeMillis();
        log.info("Service response time - {} seconds ", TimeUnit.MILLISECONDS.toSeconds( endTime-initTime));
        return ResponseEntity.ok(account);
    }

}

package com.ktds.msa.client.controller;

import com.ktds.msa.client.common.exception.ClientNotFoundException;
import com.ktds.msa.client.domain.Client;
import com.ktds.msa.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> query(@PathVariable String clientId) throws InterruptedException {

        return service.queryById(clientId)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ClientNotFoundException(clientId));
    }
}

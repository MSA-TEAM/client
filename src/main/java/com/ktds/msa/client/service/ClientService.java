package com.ktds.msa.client.service;

import com.ktds.msa.client.domain.Client;
import com.ktds.msa.client.repository.ClientRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @HystrixCommand(commandKey = "클라이언트조회",  fallbackMethod = "queryByIdDefault",
            threadPoolKey = "Query Client", threadPoolProperties = {
            @HystrixProperty(name="coreSize", value="30"),
            @HystrixProperty(name="maxQueueSize", value="30")
    })
    public Optional<Client> queryById(String clientId) {
        return repository.findByClientId(clientId);
    }

    public Optional<Client> queryByIdDefault(String userId) {
        return null;
    }
}

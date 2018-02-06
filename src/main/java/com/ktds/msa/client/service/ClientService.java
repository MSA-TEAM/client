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

    @HystrixCommand(commandKey = "클라이언트조회",  fallbackMethod = "queryByDefault",
            threadPoolKey = "Query Client", threadPoolProperties = {
            @HystrixProperty(name="coreSize", value="30"),
            @HystrixProperty(name="maxQueueSize", value="30")
    })

    public Optional<Client> queryByDefault(String userId) {
        return null;
    }
}

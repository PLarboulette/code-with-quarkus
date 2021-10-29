package plarboulette.services.impl;

import plarboulette.repository.impl.ConsumerRepository;
import plarboulette.repository.IConsumerRepository;
import plarboulette.services.IConsumerService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ConsumerService implements IConsumerService {

    @Inject
    IConsumerRepository repository;

    public ConsumerService(ConsumerRepository repo) {
        this.repository = repo;
    }

    public String consume () {
       return this.repository.getValue();
    }

    @PostConstruct
    public void init () {
        System.out.println("ConsumerService INIT");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ConsumerService DESTROY");
    }




}

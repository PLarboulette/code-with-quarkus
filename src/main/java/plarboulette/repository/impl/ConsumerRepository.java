package plarboulette.repository.impl;

import plarboulette.models.Item;
import plarboulette.repository.IConsumerRepository;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConsumerRepository implements IConsumerRepository {

    public String getValue () {
        Item monItem = new Item(15);
        return "" + monItem.getAge();
    }

    @Produces
    public List<String> names() {
        List<String> names = new ArrayList<>();
        names.add("Andy");
        names.add("Adalbert");
        names.add("Joachim");
        return names;
    }

    @PostConstruct
    public void init () {
        System.out.println("ConsumerRepository INIT");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ConsumerRepository DESTROY");
    }
}

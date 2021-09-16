package com.backbase.training.service;

import com.backbase.training.domain.Greeting;

import java.util.List;

public interface GreetingsService {

    List<Greeting> getGreetings();

    Greeting getGreetingById(String id);

    void addNewGreeting(Greeting greeting);
}

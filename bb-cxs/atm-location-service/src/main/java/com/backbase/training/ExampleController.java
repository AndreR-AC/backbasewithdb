package com.backbase.training;

import com.backbase.training.domain.Greeting;
import com.backbase.training.mappers.GreetingsMapper;
import com.backbase.training.service.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ExampleController  {

    @Autowired
    private GreetingsService greetingsService;

    @RequestMapping(method = RequestMethod.GET, value = "/message/{id}", produces = {
            "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public Message getMessage(@PathVariable(name = "id") String id) {
        Greeting greeting = greetingsService.getGreetingById(id);
        return GreetingsMapper.INSTANCE.greetingToMessage(greeting);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages", produces = {
            "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessages() {
        List<Greeting> greetings = greetingsService.getGreetings();
        return GreetingsMapper.INSTANCE.greetingsToMessages(greetings);
    }
    // tag::addMessage[]
    @RequestMapping(method = RequestMethod.POST, value = "/message")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMessage(@RequestBody Message message) {
        Greeting greeting = GreetingsMapper.INSTANCE.messageToGreeting(message);
        String id = UUID.randomUUID().toString();
        greeting.setId(id);
        greetingsService.addNewGreeting(greeting);
        return id;
    }
// end::addMessage[]
}
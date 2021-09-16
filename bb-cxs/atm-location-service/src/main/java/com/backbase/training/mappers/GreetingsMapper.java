package com.backbase.training.mappers;

import com.backbase.training.Message;
import com.backbase.training.domain.Greeting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GreetingsMapper {

    GreetingsMapper INSTANCE = Mappers.getMapper( GreetingsMapper.class);

    Message greetingToMessage(Greeting greeting);
    List<Message> greetingsToMessages(List<Greeting> greetings);
    Greeting messageToGreeting(Message message);
}

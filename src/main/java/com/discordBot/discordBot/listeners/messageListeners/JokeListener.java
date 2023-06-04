package com.discordBot.discordBot.listeners.messageListeners;

import com.discordBot.discordBot.models.JokeRepo;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public abstract class JokeListener{
    private final JokeRepo jokeRepo;

    public JokeListener(JokeRepo jokeRepo){
        this.jokeRepo = jokeRepo;
    }

    public Mono<Void> processCommand(Message msg){
        return Mono.just(msg)
                //checks if author of msg is another bot
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                //checks if msg is a command
                .filter(message -> message.getContent().equalsIgnoreCase("!joke"))
                //finds channel it has to respond to
                .flatMap(Message::getChannel)
                .flatMap(messageChannel -> messageChannel.createMessage(
                        jokeRepo.randomJoke().getJoke()))
                .then();
    }
}

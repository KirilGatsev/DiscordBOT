package com.discordBot.discordBot.services.messages;

import com.discordBot.discordBot.listeners.EventListener;
import com.discordBot.discordBot.listeners.messageListeners.MessageListener;
import com.discordBot.discordBot.models.JokeRepo;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// test, but will turn it into smth useful later
@Service
public class MessageCreateService extends MessageListener implements EventListener<MessageCreateEvent> {
    public MessageCreateService(JokeRepo jokeRepo) {
        super(jokeRepo);
    }

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processCommand(event.getMessage());
    }
}

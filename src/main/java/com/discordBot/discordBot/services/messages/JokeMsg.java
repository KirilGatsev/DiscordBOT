package com.discordBot.discordBot.services.messages;

import com.discordBot.discordBot.listeners.EventListener;
import com.discordBot.discordBot.listeners.messageListeners.JokeListener;
import com.discordBot.discordBot.models.JokeRepo;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JokeMsg extends JokeListener implements EventListener<MessageCreateEvent> {
    public JokeMsg(JokeRepo jokeRepo) {
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

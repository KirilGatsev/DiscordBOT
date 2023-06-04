package com.discordBot.discordBot.services.commands;

import com.discordBot.discordBot.listeners.EventListener;
import com.discordBot.discordBot.listeners.botCommandsListeners.DeleteJoke;
import com.discordBot.discordBot.models.JokeRepo;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteJokeService extends DeleteJoke implements EventListener<ChatInputInteractionEvent> {
    public DeleteJokeService(JokeRepo jokeRepo) {
        super(jokeRepo);
    }

    @Override
    public Class<ChatInputInteractionEvent> getEventType() {
        return ChatInputInteractionEvent.class;
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        return processCommand(event);
    }
}

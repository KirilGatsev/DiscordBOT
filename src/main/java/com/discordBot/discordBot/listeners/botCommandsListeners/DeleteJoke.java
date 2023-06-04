package com.discordBot.discordBot.listeners.botCommandsListeners;

import com.discordBot.discordBot.models.JokeRepo;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import reactor.core.publisher.Mono;

public abstract class DeleteJoke {
    private final JokeRepo jokeRepo;

    public DeleteJoke(JokeRepo jokeRepo){this.jokeRepo = jokeRepo;}

    public Mono<Void> processCommand(ChatInputInteractionEvent event){
        return Mono.just(event)
                .filter(event1 -> event1.getCommandName().equals("deletejoke"))
                .flatMap(event1 -> {
                    Long id = event1.getOption("id")
                            .flatMap(ApplicationCommandInteractionOption::getValue)
                            .map(ApplicationCommandInteractionOptionValue::asLong)
                            .get();
                    jokeRepo.deleteById(id);
                    return event1.reply(event1.getInteraction().getUser().getMention()
                    + "Joke with id: " + id + " has been successfully deleted.").withEphemeral(true);
                })
                .then();
    }
}

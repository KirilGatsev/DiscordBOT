package com.discordBot.discordBot.listeners.botCommandsListeners;

import com.discordBot.discordBot.models.Joke;
import com.discordBot.discordBot.models.JokeRepo;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public abstract class AddJoke {
    private final JokeRepo jokeRepo;

    public AddJoke(JokeRepo jokeRepo){this.jokeRepo = jokeRepo;}

    public Mono<Void> processCommand(ChatInputInteractionEvent event){
        return Mono.just(event)
                .filter(event1 -> event1.getCommandName().equals("addjoke"))
                .flatMap(event1 -> {
                    jokeRepo.save(new Joke(event1.getOption("joke")
                            .flatMap(ApplicationCommandInteractionOption::getValue)
                            .map(ApplicationCommandInteractionOptionValue::asString)
                            .get()));
                    return event1.reply(event.getInteraction().getUser().getMention()
                    + " Joke has been successfully added.").withEphemeral(true);
                }).then();
    }
}

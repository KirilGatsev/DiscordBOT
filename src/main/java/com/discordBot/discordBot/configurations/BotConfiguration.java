package com.discordBot.discordBot.configurations;

import com.discordBot.discordBot.listeners.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//veche i slash komandite rabotat kato horata sus eventlistener i t.n
//ostava da go vurja s db, i da da puska laf4eta ot tam, i da moje da se addva i deletvat shegi

@Configuration
public class BotConfiguration {
    @Value("${token}")
    private String token;

    //da napravim commandbuilder factory(mai samo da smenim imeto?)
    //da napravim list s komandi i da addvame s foreach v loop tuka

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(final List<EventListener<T>>
                                                                       eventListeners){
        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .gateway()
                .login()
                .block();

        for(EventListener<T> listener: eventListeners){
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }
        return client;
    }

    @Bean
    public RestClient discordRestClient(GatewayDiscordClient client) {
        return client.getRestClient();
    }

}

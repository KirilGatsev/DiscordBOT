package com.discordBot.discordBot.listeners.botCommandsListeners;

import discord4j.common.JacksonResources;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//black magic from docs, it makes it easy to add commands from json files in resources/commands dest
@Component
public class GlobalCommandRegister implements ApplicationRunner {
    private final Logger log = LoggerFactory.getLogger(com.discordBot.discordBot.listeners.botCommandsListeners.GlobalCommandRegister.class);
    private final RestClient restClient;
    @Value("#{new Long(${guildId})}")
    private Long guildId;

    public GlobalCommandRegister(RestClient restClient){
        this.restClient = restClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final JacksonResources d4jmapper = JacksonResources.create();

        PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();
        final ApplicationService applicationService = restClient.getApplicationService();
        final Long applicationId = restClient.getApplicationId().block();

        List<ApplicationCommandRequest> commands = new ArrayList<>();
        for(Resource resource: matcher.getResources("commands/*.json")){
            ApplicationCommandRequest request = d4jmapper.getObjectMapper()
                    .readValue(resource.getInputStream(), ApplicationCommandRequest.class);

            commands.add(request);
        }

        applicationService.bulkOverwriteGuildApplicationCommand(applicationId, guildId, commands)
                .doOnNext(ignore -> log.debug("Successfully registered commands"))
                .doOnError(e -> log.debug("Failed to register command " + e))
                .subscribe();
    }
}

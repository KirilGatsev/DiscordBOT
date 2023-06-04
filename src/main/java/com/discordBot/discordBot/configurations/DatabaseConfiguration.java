package com.discordBot.discordBot.configurations;

import com.discordBot.discordBot.models.Joke;
import com.discordBot.discordBot.models.JokeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// for convenience's sake, if sb wants to test if it works they can do so without setting up a database
// on their own
@Configuration
public class DatabaseConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
    @Bean
    CommandLineRunner cmdRunner(JokeRepo jokeRepo){
        return args -> {
            jokeRepo.save(new Joke("Изберете карта на късмета… – Средно. – Мое средно или ваше средно???"));
            jokeRepo.save(new Joke("Гащи с “б”?Цялата публика мълчи.- Боксерки!"));
            jokeRepo.save(new Joke("Една жена скочила от 10-тия етаж.Защо косата й останала във въздуха?Защото се къпела с шампоан против косопад."));
            jokeRepo.save(new Joke("По какво си падат бабичките? – По стълбите."));

            jokeRepo.findAll().forEach(joke -> log.info("Preloading: " + joke));
        };
    }
}

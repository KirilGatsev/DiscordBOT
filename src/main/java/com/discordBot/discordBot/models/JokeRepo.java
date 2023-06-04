package com.discordBot.discordBot.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepo extends JpaRepository<Joke, Long>{
    @Query(nativeQuery=true, value="SELECT *  FROM joke ORDER BY random() LIMIT 1")
    Joke randomJoke();
}

package com.discordBot.discordBot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String joke;

    public Joke(){}

    public Joke(String joke){
        this.joke = joke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joke joke1)) return false;
        return Objects.equals(id, joke1.id) && Objects.equals(joke, joke1.joke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, joke);
    }

    @Override
    public String toString() {
        return "MockedTable{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                '}';
    }
}

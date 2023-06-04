package com.discordBot.discordBot.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//runs in background so i don't have to deploy bot anywhere
@Service
public class KeepAliveService {
    @Scheduled(fixedRate = 1000 * 60)//minute
    public void reportCurrentTime(){
        System.out.println(System.currentTimeMillis());
    }
}

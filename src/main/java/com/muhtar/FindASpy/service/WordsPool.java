package com.muhtar.FindASpy.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class WordsPool {

    private static List<String> wordList = Arrays.asList(
            "Airport", "Police Office", "Hospital", "Football field", "Cinema");
    private static Random random = new Random();

    public String getRandomWord() {
        return wordList.get(random.nextInt(wordList.size()));
    }



}

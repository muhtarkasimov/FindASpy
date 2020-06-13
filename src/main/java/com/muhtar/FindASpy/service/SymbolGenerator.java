package com.muhtar.FindASpy.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SymbolGenerator {

    private static char[] permittedSymbols;
    private static final Random random = new Random();

    public SymbolGenerator() {
        List<Character> list = new ArrayList<>();

        for (int i = 48; i < 123; i++) {
            if ((i > 57 && i < 65) ||
                    (i > 90 && i < 97)) {
                continue;
            }
            list.add((char)i);
        }
        String str = list.toString().replaceAll(",", "");
        permittedSymbols = str.substring(1, str.length()-1).replaceAll(" ", "").toCharArray();
    }

    public String generateString(int length) {
        String s = "";
        int max = permittedSymbols.length;
        int min = 0;
        for (int i = 0; i < length; i++) {
            s += permittedSymbols[random.nextInt(permittedSymbols.length)];
        }
        return s;
    }
}

package com.urfu.Tamada.command.crocodile;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CrocodileTest {
    @Test
    void fillWords() {
        var crocodileTest = new Crocodile();
        crocodileTest.pathToWords = "src/test/java/com/urfu/Tamada/command/crocodile/testCrocodileWords.txt";
        crocodileTest.fillWords();
        var a = new ArrayList<String>();
        a.add("Матанализ");
        assertArrayEquals(crocodileTest.words.toArray(), a.toArray());
    }
}

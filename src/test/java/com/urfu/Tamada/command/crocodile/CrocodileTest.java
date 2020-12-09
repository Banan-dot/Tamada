package com.urfu.Tamada.command.crocodile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CrocodileTest {
    private Crocodile crocodile;
    @BeforeEach
    void setUp(){
        crocodile = new Crocodile();
    }

    @Test
    void fillWords() {
        crocodile.pathToWords = "src/test/java/com/urfu/Tamada/command/crocodile/testCrocodileWords.txt";
        crocodile.fillWords();
        var testList = new ArrayList<String>();
        testList.add("Матанализ");
        assertArrayEquals(crocodile.words.toArray(), testList.toArray());
    }


}

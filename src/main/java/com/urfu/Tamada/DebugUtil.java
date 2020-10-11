package com.urfu.Tamada;

import java.io.FileWriter;
import java.io.IOException;

public class DebugUtil {
    public void simpleLogger(String joke, Integer id) {
        try (FileWriter writer = new FileWriter("SimpleJokesLogger.txt", true)) {
            var loggString = id + " " + joke + "\n";
            writer.write(loggString);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

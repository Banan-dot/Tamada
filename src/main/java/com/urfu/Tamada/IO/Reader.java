package com.urfu.Tamada.IO;

import com.urfu.Tamada.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> readWords(String pathToWords) {
        var words = new ArrayList<String>();
        try {
            var reader = new BufferedReader(new FileReader(pathToWords));
            var line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static int readLastId() {
        var result = 0;
        try {
            var reader = new BufferedReader(new FileReader(Config.getPathToLastWord()));
            result = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

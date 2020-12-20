package com.urfu.Tamada.IO;

import com.urfu.Tamada.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void writeLastIdToFile(int i){
        try {
            var writer = new BufferedWriter(new FileWriter(Config.getPathToLastWord()));
            writer.write(String.valueOf(i));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.ppfx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class PlainTextProcess {

    private final Vector<String> data  = new Vector<>();

    public Vector<String> readFromPlainTextFile(String fileName)  {
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(line);
                }
            }
            catch (IOException e)
            {
                System.err.println("Error read from txt " + e.getMessage());
                System.exit(1);
            }

        }
        return data;
    }

    public void writeInPlainText(Vector<String> result,String fileName){

        try( FileWriter output = new FileWriter(fileName)) {
            for (String string : result) {
                output.write(string+ "\n");
            }


        }
        catch (IOException e)
        {
            System.out.println("Error");
        }

    }



}


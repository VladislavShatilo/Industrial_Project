package com.example.ppv2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class JSONWorkTest {
    private boolean areFilesEqual(Path file1, Path file2) throws IOException {

        byte[] file1Content = Files.readAllBytes(file1);
        byte[] file2Content = Files.readAllBytes(file2);

        return MessageDigest.isEqual(file1Content, file2Content);
    }
    JSONWork jsonWork = new JSONWork();
    @Test
    void test1() {
        Vector<String> expressions = new Vector<>();
        expressions = jsonWork.readFromJSON("i.json");
        Vector<String> expected = new Vector<>();
        expected.add("4 + 3 * 4");
        expected.add("3 + 2 - 7");
        expected.add("4 * (5 - 6) / 3");
        Assertions.assertEquals(expressions,expected);
    }

    @Test
    void writeInJSON() {
        Vector<String> data = new Vector<>();
        data.add("4 + 3 * 4");
        data.add("3 + 2 - 7");
        data.add("4 * (5 - 6) / 3");
        LibraryCalculatorBuilder libraryCalculatorBuilder = LibraryCalculatorBuilder.getInstance();


        jsonWork.writeInJSON(libraryCalculatorBuilder.calculate(data).build(),"temp.json");

        writeInJson();
        Path file1 = Path.of("temp.json");
        Path file2 = Path.of("temp1.json");
        assertTrue(Files.exists(file1), "File 1 does not exist");
        assertTrue(Files.exists(file2), "File 2 does not exist");


        try {
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");
        }catch (Exception e) {
            Assertions.fail();
        }
        File fileToDelete1 = new File("temp.json");
        File fileToDelete2 = new File("temp1.json");
        fileToDelete1.delete();
        fileToDelete2.delete();


    }
    private void writeInJson()
    {
        JSONObject elem = new JSONObject();

        try (PrintWriter out = new PrintWriter(new FileWriter("temp1.json"))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            elem.put(("Answer 0" ),Double.valueOf((double) 16).toString());
            elem.put(("Answer 1" ),Double.valueOf((double) -2).toString());
            elem.put(("Answer 2" ), Double.valueOf((double) -4 /3).toString());

            String jsonString = gson.toJson(elem);
            out.write(jsonString);

        } catch (Exception e) {
            System.out.println("Error open Json writer");
        }
    }
}
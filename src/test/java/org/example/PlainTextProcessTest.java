package org.example;

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

class PlainTextProcessTest {
    private boolean areFilesEqual(Path file1, Path file2) throws IOException {

        byte[] file1Content = Files.readAllBytes(file1);
        byte[] file2Content = Files.readAllBytes(file2);

        return MessageDigest.isEqual(file1Content, file2Content);
    }
    PlainTextProcess plainTextProcess = new PlainTextProcess();
    @Test
    void test1() {
        Vector<String> expressions = new Vector<>();
        expressions = plainTextProcess.readFromPlainTextFile("i.txt");
        Vector<String> expected = new Vector<>();
        expected.add("4 + 3 * 4;");
        expected.add("3 + 2 - 7;");
        expected.add("4 * (5 - 6) / 3;");
        expected.add("0.56 + 0.2 * 4;");
        Assertions.assertEquals(expressions,expected);
    }

    @Test
    void test2() {
        Vector<String> data = new Vector<>();
        data.add("4 + 3 * 4");
        data.add("3 + 2 - 7");
        data.add("4 * (5 - 6) / 3");
        LibraryCalculatorBuilder libraryCalculatorBuilder = LibraryCalculatorBuilder.getInstance();


        plainTextProcess.writeInPlainText(libraryCalculatorBuilder.calculate(data).build(),"temp.txt");

        writeInTxtTest();
        Path file1 = Path.of("temp.txt");
        Path file2 = Path.of("temp1.txt");
        assertTrue(Files.exists(file1), "File 1 does not exist");
        assertTrue(Files.exists(file2), "File 2 does not exist");


        try {
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");
        }catch (Exception e) {
            Assertions.fail();
        }
        File fileToDelete1 = new File("temp.txt");
        File fileToDelete2 = new File("temp1.txt");
        fileToDelete1.delete();
        fileToDelete2.delete();


    }
    private void writeInTxtTest()
    {
        try( FileWriter output = new FileWriter("temp1.txt")) {

                output.write("""
                        16.0
                        -2.0
                        -1.3333333333333333
                        """);


        }
        catch (IOException e)
        {
            System.out.println("Error");
        }

    }
}
package com.example.ppfx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {
    private boolean areFilesEqual(Path file1, Path file2) throws IOException {

        byte[] file1Content = Files.readAllBytes(file1);
        byte[] file2Content = Files.readAllBytes(file2);

        return MessageDigest.isEqual(file1Content, file2Content);
    }
    Encryption encryption = new Encryption();
    @Test
    void test1(){
        File fileToEncrypt = new File("i.txt");
        File outputFile = new File("out.enc");
        try {
            encryption.encryptFile(fileToEncrypt,outputFile);
        }
        catch (Exception e)
        {
            Assertions.fail();
        }
        Assertions.assertTrue(isFileEncrypted(outputFile.getPath()));

        File DecryptFile = new File("out.txt");
        try {
            encryption.decryptFile(outputFile, DecryptFile);
        }
        catch (Exception e)
        {
            Assertions.fail();
        }
        Path file1 = Path.of("i.txt");
        Path file2 = Path.of("out.txt");
        assertTrue(Files.exists(file1), "File 1 does not exist");
        assertTrue(Files.exists(file2), "File 2 does not exist");


        try {
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");
        }catch (Exception e) {
            Assertions.fail();
        }
        File fileToDelete1 = new File("out.enc");
        File fileToDelete2 = new File("out.txt");
        fileToDelete1.delete();
        fileToDelete2.delete();


    }


    public boolean isFileEncrypted(String filePath) {
        return filePath.endsWith(".enc");
    }

}
package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.*;

class ArchiveWorkTest {
    private boolean areFilesEqual(Path file1, Path file2) throws IOException {

        byte[] file1Content = Files.readAllBytes(file1);
        byte[] file2Content = Files.readAllBytes(file2);

        return MessageDigest.isEqual(file1Content, file2Content);
    }
    @Test
    void test1()
    {
        ArchiveWork archiveWork = new ArchiveWork();

        Path file1 = Path.of(archiveWork.read("zip_txt.zip"));
        Path file2 = Path.of("zip_txt.txt");
        assertTrue(Files.exists(file1), "File 1 does not exist");
        assertTrue(Files.exists(file2), "File 2 does not exist");



        try {
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");
        }catch (Exception e) {
            Assertions.fail();
        }

        File fileToDelete = new File(archiveWork.read("zip_txt.zip"));
        fileToDelete.delete();

    }

    @Test
    void test2()
    {
        ArchiveWork archiveWork = new ArchiveWork();

        Path file1 = Path.of( archiveWork.read("rar_txt.rar"));
        Path file2 = Path.of("zip_txt.txt");
        assertTrue(Files.exists(file1), "File 1 does not exist");
        assertTrue(Files.exists(file2), "File 2 does not exist");


        try {
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");
        }catch (Exception e) {
            Assertions.fail();
        }
        File fileToDelete = new File(archiveWork.read("zip_txt.zip"));
        fileToDelete.delete();

    }

    @Test
    void test3()
    {
        ArchiveWork archiveWork = new ArchiveWork();
        archiveWork.write("i","txt","zip");
        Path file1 = Path.of("i.txt");
        Path file2 = Path.of(archiveWork.read("i.zip"));
        assertTrue(Files.exists(file1), "File 1 does not exist");
        assertTrue(Files.exists(file2), "File 2 does not exist");

        try {
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");
        }catch (Exception e) {
            Assertions.fail();
        }
        File fileToDelete = new File("i.zip");
        fileToDelete.delete();
    }

    @Test
    void test4()
    {
        ArchiveWork archiveWork = new ArchiveWork();
        archiveWork.write("i","txt","rar");
        Path file1 = Path.of("i.txt");
        Path file2 = Path.of(archiveWork.read("i.rar"));
        assertTrue(Files.exists(file1), "File 1 does not exist");
        assertTrue(Files.exists(file2), "File 2 does not exist");

        try {
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");
        }catch (Exception e) {
            Assertions.fail();
        }
        File fileToDelete = new File("i.rar");
        fileToDelete.delete();
    }

}
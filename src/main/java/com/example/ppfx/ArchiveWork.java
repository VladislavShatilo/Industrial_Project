package com.example.ppfx;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveWork
{
    private void showErrorAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error read from archive");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    public String read(String fileName)
    {
        String unpackedFile = "";
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName)))
        {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null)
            {

                name = entry.getName();
                unpackedFile = name;

                FileOutputStream fileOutputStream = new FileOutputStream(unpackedFile);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fileOutputStream.write(c);
                }
                fileOutputStream.flush();
                zin.closeEntry();
                fileOutputStream.close();
            }

        }
        catch (Exception ex) {

            System.err.println("Error read from archive" + ex.getMessage());
            showErrorAlert(ex.getMessage());
            //System.exit(1);
        }
        return unpackedFile;
    }


    public void write(String fileName, String type, String typeArchive) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fileName + "." +typeArchive));
             FileInputStream fis = new FileInputStream(fileName + "." + type)) {
            ZipEntry entry1 = new ZipEntry(fileName + "." + type);
            zipOutputStream.putNextEntry(entry1);

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            zipOutputStream.write(buffer);

            zipOutputStream.closeEntry();


        } catch (Exception e) {
            System.out.println("error");
        }


    }


}

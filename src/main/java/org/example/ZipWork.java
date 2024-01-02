package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipWork
{

    public String read(String fileName)
    {
        String unpackedFile = new String();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName)))
        {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null)
            {

                name = entry.getName();

                unpackedFile = name;

                FileOutputStream fout = new FileOutputStream(unpackedFile);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

        }
        catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        return unpackedFile;
    }


    public void write(String fileName, String type) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fileName + ".zip"));
             FileInputStream fis = new FileInputStream(fileName + "." + type);) {
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

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

public class ZipWork {
        public void readZipTXT(String fileName)
        {

            try( ZipInputStream zip = new ZipInputStream(new FileInputStream(fileName))){

                ZipEntry entry;

                while ((entry = zip.getNextEntry()) != null) {
                    if(entry.getName().toLowerCase().endsWith(".txt"))
                    {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zip.read(buffer)) != -1) {
                            System.out.write(buffer, 0, bytesRead);
                        }
                    } else if (entry.getName().toLowerCase().endsWith(".json")) {

                     String str = entry.getComment();
                        System.out.println(str);
                        JsonObject jsonObject = new JsonObject();

                        }
                    zip.closeEntry();



                }
            }
            catch (IOException e) {
                 System.out.println(e.getMessage());;
            }
        }
    public void write(Vector<String> result, String fileName, String type) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fileName + ".zip"));
             FileInputStream fis= new FileInputStream(fileName + "." + type);){
            ZipEntry entry1=new ZipEntry(fileName + "." + type);
            zipOutputStream.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zipOutputStream.write(buffer);
            // закрываем текущую запись для новой записи
            zipOutputStream.closeEntry();


        }
        catch (Exception e)
        {
            System.out.println("error");
        }


    }
        public void readZipJson(String fileName)
        {
            try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileName))) {
                ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    System.out.println("File: " + entry.getName() + ", Size: " + entry.getSize());


                    if (entry.getName().endsWith(".json")) {

                        StringBuilder jsonContent = new StringBuilder();
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                            jsonContent.append(new String(buffer, 0, bytesRead));
                        }

                        System.out.println("JSON Content: " + jsonContent.toString());


                    }

                    System.out.println(); // Переход на новую строку после каждого файла
                }

            }
            catch (IOException e) {
                System.out.println(e.getMessage());;
            }
        }

    }


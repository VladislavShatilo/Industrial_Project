package com.example.ppfx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

public  class JSONWork {
    private void showErrorAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error read from json");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
        public Vector<String> readFromJSON(String fileName) {
            JSONParser parser = new JSONParser();
            Vector<String> data = new Vector<>();
            try (FileReader reader = new FileReader(fileName)) {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                JSONArray expressionsJson = (JSONArray)jsonObject.get("expressions");
                for(Object item:expressionsJson)
                {
                    JSONObject expJson =(JSONObject)item;
                    String exp =(String) expJson.get("exp");
                    data.add(exp);
                }


            } catch (Exception e) {
                System.err.println("Error read from json" + e.getMessage());
                showErrorAlert( e.getMessage());
                System.exit(1);
            }
            return data;

        }

        public void writeInJSON(Vector<String> result, String filename){

            JSONObject elem = new JSONObject();

            try (PrintWriter out = new PrintWriter(new FileWriter(filename))){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                for(int i = 0; i<result.size();i++)
                {
                     elem.put(("Answer " + i), result.get(i));
                }
                String jsonString = gson.toJson(elem);
                out.write(jsonString);

            } catch (Exception e) {
                System.out.println("Error open Json writer");
            }


        }

    }


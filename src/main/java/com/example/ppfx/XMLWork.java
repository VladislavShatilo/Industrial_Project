package com.example.ppfx;

import javafx.scene.control.Alert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Vector;

public class XMLWork {
    private void showErrorAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error read from xml");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public Vector<String> readFromXML(String fileName) {
        Vector<String> data = new Vector<>();
        String expString;
        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.err.println("Error xml read" + e.getMessage());
            showErrorAlert(e.getMessage());
           // System.exit(1);
            return null;

        }


        NodeList rootList = doc.getDocumentElement().getElementsByTagName("exp");

        for (int i = 0; i < rootList.getLength(); i++)
        {
                expString = rootList.item(i).getTextContent();
                data.add(expString);
        }
        return data;

    }

    public void writeInXml(Vector<String> result, String fileName){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            Element answers = doc.createElement("Answers");
            rootElement.appendChild(answers);
            for(int i = 0; i<result.size();i++){
                Element answer  =doc.createElement(("Answer"+i));
                answer.appendChild(doc.createTextNode(result.get(i)));
                answers.appendChild(answer);
            }




            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);


            File file = new File(fileName);
            StreamResult res = new StreamResult(file);

            transformer.transform(source, res);


        }
        catch(Exception e)
        {
            System.out.println("Error write in XML");
        }
    }
}

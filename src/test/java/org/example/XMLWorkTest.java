package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class XMLWorkTest {
    private boolean areFilesEqual(Path file1, Path file2) throws IOException {

        byte[] file1Content = Files.readAllBytes(file1);
        byte[] file2Content = Files.readAllBytes(file2);

        return MessageDigest.isEqual(file1Content, file2Content);
    }
    XMLWork xmlWork = new XMLWork();
    @Test
    void test1() {
        Vector<String> expressions = new Vector<>();
        expressions = xmlWork.readFromXML("i.xml");
        Vector<String> expected = new Vector<>();
        expected.add("4 + 3 * 4");
        expected.add("3 + 2 - 7");
        expected.add("4 * 5 - 6 / 3");
        Assertions.assertEquals(expressions,expected);
    }

    @Test
    void test2() {
        Vector<String> data = new Vector<>();
        data.add("4 + 3 * 4");
        data.add("3 + 2 - 7");
        data.add("4 * 5 - 6 / 3");
        LibraryCalculatorBuilder libraryCalculatorBuilder = LibraryCalculatorBuilder.getInstance();


        xmlWork.writeInXml(libraryCalculatorBuilder.calculate(data).build(),"temp.xml");


        try {
            Path file1 = Path.of("temp.xml");
            Path file2 = Path.of("temp1.xml");
            writeInXml();
            assertTrue(Files.exists(file1), "File 1 does not exist");
            assertTrue(Files.exists(file2), "File 2 does not exist");
            assertTrue(areFilesEqual(file1, file2), "Files are not equal");

        }catch (Exception e) {
            Assertions.fail();
        }
       File fileToDelete1 = new File("temp.xml");
       File fileToDelete2 = new File("temp1.xml");
       fileToDelete1.delete();
       fileToDelete2.delete();


    }
    private void writeInXml() throws Exception {
        JSONObject elem = new JSONObject();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("root");
        doc.appendChild(rootElement);

        Element answers = doc.createElement("Answers");
        rootElement.appendChild(answers);

        Element answer  =doc.createElement(("Answer"+0));
        answer.appendChild(doc.createTextNode("16.0"));
        answers.appendChild(answer);
         answer  =doc.createElement(("Answer"+1));
        answer.appendChild(doc.createTextNode("-2.0"));
        answers.appendChild(answer);
         answer  =doc.createElement(("Answer"+2));
        answer.appendChild(doc.createTextNode("18.0"));
        answers.appendChild(answer);







        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);


        File file = new File("temp1.xml");
        StreamResult res = new StreamResult(file);

        transformer.transform(source, res);

    }
}
package org.example;

import java.io.File;
import java.util.Vector;

public class Manager {
    private final FileInformationStructure file;
    private  Vector<String> result;
    private final Calculator calculator = new Calculator();

    Manager(FileInformationStructure basicFile)

    {
        file = basicFile;
    }

    public  void callFunctionInput()
    {

        if(!file.isArchiveInputFile && file.isEncryptInputFile)
        {
            Encryption encryption = new Encryption();
            File inputFile = new File(file.nameInputFile + ".enc");

            switch (file.typeInputFile)
            {
                case "txt"->{

                    File outputFile = new File("enc_out.txt");
                    PlainTextProcess txtWork = new PlainTextProcess();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        Vector<String> data = txtWork.readFromPlainTextFile("enc_out.txt");
                        result = calculator.calculateSimple(data);
                        boolean delete = outputFile.delete();

                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e.toString());
                    }

                }
                case "json"->{
                    File outputFile = new File("enc_out.json");
                    JSONWork jsonWork = new JSONWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        Vector<String> data = jsonWork.readFromJSON("enc_out.json");
                        result = calculator.calculateSimple(data);
                        outputFile.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e.toString());
                    }

                }
                case "xml"->{

                    File outputFile = new File("enc_out.xml");
                    XMLWork xmlWork = new XMLWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        Vector<String> data = xmlWork.readFromXML("enc_out.xml");
                        result = calculator.calculateSimple(data);
                        boolean delete = outputFile.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e.toString());
                    }

                }
                default -> {

                }
            }
        }
        else if(file.isArchiveInputFile && !file.isEncryptInputFile)
        {
            ZipWork zipWork = new ZipWork();
            switch (file.typeInputFile)
            {
                case "txt"->{
                    PlainTextProcess txtWork = new PlainTextProcess();
                    try {
                        Vector<String> data = txtWork.readFromPlainTextFile( zipWork.read(file.nameInputFile+".zip"));
                        result = calculator.calculateSimple(data);
                        File fileToDelete = new File( zipWork.read(file.nameInputFile+".zip"));
                        fileToDelete.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                }
                case "json"->{
                    JSONWork jsonWork = new JSONWork();
                    try {
                        Vector<String> data = jsonWork.readFromJSON( zipWork.read(file.nameInputFile+".zip"));
                        result = calculator.calculateSimple(data);
                        File fileToDelete = new File( zipWork.read(file.nameInputFile+".zip"));
                        fileToDelete.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case "xml"->{
                    XMLWork xmlWork = new XMLWork();
                    try {

                        Vector<String> data = xmlWork.readFromXML( zipWork.read(file.nameInputFile+".zip"));
                        result = calculator.calculateSimple(data);
                        File fileToDelete = new File( zipWork.read(file.nameInputFile+".zip"));
                        fileToDelete.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                default -> {

                }

            }

        }
        else if(file.isArchiveInputFile && file.ArchiveThanEncryptInput)
        {
            switch (file.typeInputFile)
            {
                case "txt"->{
                    Encryption encryption = new Encryption();
                    File inputFile = new File(file.nameInputFile + ".enc");
                    File outputFile = new File("enc_out.zip");
                    ZipWork zipWork = new ZipWork();
                    PlainTextProcess plainTextProcess = new PlainTextProcess();
                    try {
                        encryption.decryptFile(inputFile,outputFile);

                        Vector<String> data = plainTextProcess.readFromPlainTextFile(zipWork.read("enc_out.zip"));
                        result = calculator.calculateSimple(data);

                        File fileToDelete = new File( zipWork.read("enc_out.zip"));
                        fileToDelete.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e.toString());
                    }


                }
                case "json"->{
                    Encryption encryption = new Encryption();
                    File inputFile = new File(file.nameInputFile + ".enc");
                    File outputFile = new File("enc_out.zip");
                    ZipWork zipWork = new ZipWork();
                    JSONWork jsonWork = new JSONWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);

                        Vector<String> data = jsonWork.readFromJSON(zipWork.read("enc_out.zip"));
                        result = calculator.calculateSimple(data);
                        File fileToDelete = new File( zipWork.read("enc_out.zip"));
                        fileToDelete.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e.toString());
                    }


                }
                case "xml"->{
                    Encryption encryption = new Encryption();
                    File inputFile = new File(file.nameInputFile + ".enc");
                    File outputFile = new File("enc_out.zip");
                    ZipWork zipWork = new ZipWork();
                    XMLWork xmlWork = new XMLWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);

                        Vector<String> data = xmlWork.readFromXML(zipWork.read("enc_out.zip"));
                        result = calculator.calculateSimple(data);
                        File fileToDelete = new File( zipWork.read("enc_out.zip"));
                        fileToDelete.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e.toString());
                    }

                }
                default -> {

                }

            }

        }
        else if(file.isArchiveInputFile && file.encryptThanArchiveInput)
        {
            switch (file.typeInputFile)
            {
                case "txt"->{
                    PlainTextProcess txtWork = new PlainTextProcess();
                    Encryption encryption = new Encryption();

                    try {
                        ZipWork zipWork = new ZipWork();
                        File inputFile = new File( zipWork.read(file.nameInputFile+".zip"));
                        File outputFile = new File("enc_out.txt");
                        encryption.decryptFile(inputFile,outputFile);
                        result = calculator.calculateSimple( txtWork.readFromPlainTextFile("enc_out.txt"));
                        outputFile.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                }
                case "json"->{
                    JSONWork jsonWork = new JSONWork();
                    Encryption encryption = new Encryption();
                    try {
                        ZipWork zipWork = new ZipWork();
                        File inputFile = new File( zipWork.read(file.nameInputFile+".zip"));
                        File outputFile = new File("enc_out.json");
                        encryption.decryptFile(inputFile,outputFile);
                        result = calculator.calculateSimple( jsonWork.readFromJSON("enc_out.json"));
                        outputFile.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                }
                case "xml"->{
                    XMLWork xmlWork = new XMLWork();
                    Encryption encryption = new Encryption();
                    try {
                        ZipWork zipWork = new ZipWork();
                        File inputFile = new File( zipWork.read(file.nameInputFile+".zip"));
                        File outputFile = new File("enc_out.xml");
                        encryption.decryptFile(inputFile,outputFile);
                        result = calculator.calculateSimple( xmlWork.readFromXML("enc_out.xml"));
                        outputFile.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                default -> {

                }

            }

        }
        else {
            file.nameInputFile += "." + file.typeInputFile;
            switch (file.typeInputFile)
            {
                case "txt" -> {

                    PlainTextProcess txtWork = new PlainTextProcess();
                    try {
                            Vector<String> data = txtWork.readFromPlainTextFile(file.nameInputFile);
                            result = calculator.calculateSimple(data);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case "json" -> {
                    JSONWork jsonWork = new JSONWork();
                    try {
                        Vector<String> data = jsonWork.readFromJSON(file.nameInputFile);
                        result = calculator.calculateSimple(data);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                case "xml" -> {
                    XMLWork xmlWork = new XMLWork();
                    try {
                        Vector<String> data = xmlWork.readFromXML(file.nameInputFile);
                        result = calculator.calculateSimple(data);


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }

            }

        }


    }

    private void archiveTxt(){
        PlainTextProcess plainTextProcess = new PlainTextProcess();
        plainTextProcess.writeInPlainText(result, file.nameOutputFile + "." + file.typeOutputFile);
        ZipWork zipWork = new ZipWork();
        zipWork.write(file.nameOutputFile,file.typeOutputFile);
        File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
        boolean delete = fileToDelete.delete();

    }
    private void archiveJson(){
        JSONWork jsonWork = new JSONWork();
        jsonWork.writeInJSON(result,  file.nameOutputFile + "." + file.typeOutputFile);
        ZipWork zipWork = new ZipWork();
        zipWork.write(file.nameOutputFile,file.typeOutputFile);
        File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
        boolean delete = fileToDelete.delete();


    }
    private void archiveXml(){
        XMLWork xmlWork = new XMLWork();
        xmlWork.writeInXml(result,file.nameOutputFile + "." + file.typeOutputFile);
        ZipWork zipWork = new ZipWork();
        zipWork.write(file.nameOutputFile,file.typeOutputFile);

        File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
        boolean delete = fileToDelete.delete();


    }
    private void enc(){
        file.nameOutputFile += ".enc";
        Encryption encryption = new Encryption();
        File inputFile = new File(file.nameInputFile);
        File outputFile = new File(file.nameOutputFile);
        try {
            encryption.encryptFile(inputFile, outputFile);
        }
        catch (Exception e)
        {
            System.out.println("Error encrypt file " + e.toString());
        }
    }

    public void callFunctionOutput() {


        if(file.isEncryptOutputFile && !file.isArchiveOutputFile)
        {
            enc();
        }

        else if(!file.isEncryptOutputFile && file.isArchiveOutputFile)
        {

            switch (file.typeOutputFile) {
                case "txt" -> archiveTxt();
                case "json" -> archiveJson();
                case "xml" -> archiveXml();
                default -> throw new IllegalStateException("Unexpected value: " + file.typeOutputFile);
            }

        }
        else if(file.isEncryptOutputFile  && file.ArchiveThanEncryptOutput)
        {

            switch (file.typeOutputFile) {
                case "txt" -> {
                    archiveTxt();
                    enc();
                }
                case "json" -> {
                    archiveJson();
                    enc();
                }
                case "xml" -> {
                    archiveXml();
                    enc();
                }
                default -> throw new IllegalStateException("Unexpected value: " + file.typeOutputFile);
            }
        }
        else if(file.isEncryptOutputFile && file.encryptThanArchiveOutput)
        {
            enc();
            ZipWork zipWork = new ZipWork();
            zipWork.write(file.nameOutputFile,file.typeOutputFile);
            File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
            boolean delete = fileToDelete.delete();

        }
        else {
            file.nameOutputFile += "." + file.typeOutputFile;
            switch (file.typeOutputFile) {
                case "txt" -> {
                    PlainTextProcess txtWork = new PlainTextProcess();
                    txtWork.writeInPlainText(result, file.nameOutputFile);
                }
                case "json" -> {
                    JSONWork jsonWork = new JSONWork();
                    jsonWork.writeInJSON(result, file.nameOutputFile);
                }
                case "xml" -> {
                    XMLWork xmlWork = new XMLWork();
                    xmlWork.writeInXml(result, file.nameOutputFile);
                }
            }
        }
    }

}


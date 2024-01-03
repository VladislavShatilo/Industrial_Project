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
                        result = txtWork.readFromPlainTextFile("enc_out.txt");
                        outputFile.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e);
                    }

                }
                case "json"->{
                    File outputFile = new File("enc_out.json");
                    JSONWork jsonWork = new JSONWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        result = jsonWork.readFromJSON("enc_out.json");
                        outputFile.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e);
                    }

                }
                case "xml"->{

                    File outputFile = new File("enc_out.xml");
                    XMLWork xmlWork = new XMLWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        result = xmlWork.readFromXML("enc_out.xml");
                        outputFile.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e);
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
                        result = txtWork.readFromPlainTextFile( zipWork.read(file.nameInputFile+".zip"));
                        File fileToDelete = new File( zipWork.read(file.nameInputFile+".zip"));
                        fileToDelete.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                }
                case "json"->{
                    JSONWork jsonWork = new JSONWork();
                    try {
                        result = jsonWork.readFromJSON( zipWork.read(file.nameInputFile+".zip"));
                       File file1= new File( zipWork.read(file.nameInputFile+".zip"));
                       file1.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case "xml"->{
                    XMLWork xmlWork = new XMLWork();
                    try {
                        result = xmlWork.readFromXML( zipWork.read(file.nameInputFile+".zip"));
                        File file1 =new File( zipWork.read(file.nameInputFile+".zip"));
                        file1.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }

                default -> throw new IllegalStateException("Unexpected value: " + file.typeInputFile);
            }

        }
        else if(file.isArchiveInputFile && file.ArchiveThanEncryptInput)
        {
            Encryption encryption = new Encryption();
            File inputFile = new File(file.nameInputFile + ".enc");
            File outputFile = new File("enc_out.zip");
            ZipWork zipWork = new ZipWork();
            switch (file.typeInputFile)
            {
                case "txt"->{
                    PlainTextProcess plainTextProcess = new PlainTextProcess();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        result = plainTextProcess.readFromPlainTextFile(zipWork.read("enc_out.zip"));
                        File fileToDelete = new File( zipWork.read("enc_out.zip"));
                        fileToDelete.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e);
                    }
                    
                }
                case "json"->{

                    JSONWork jsonWork = new JSONWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        result = jsonWork.readFromJSON(zipWork.read("enc_out.zip"));
                        File fileToDelete = new File( zipWork.read("enc_out.zip"));
                        fileToDelete.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e);
                    }

                }
                case "xml"->{
                    XMLWork xmlWork = new XMLWork();
                    try {
                        encryption.decryptFile(inputFile,outputFile);
                        result = xmlWork.readFromXML(zipWork.read("enc_out.zip"));
                        File fileToDelete = new File( zipWork.read("enc_out.zip"));
                        fileToDelete.delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error decrypt file " + e);
                    }

                }

                default -> throw new IllegalStateException("Unexpected value: " + file.typeInputFile);
            }
            outputFile.delete();

        }
        else if(file.isArchiveInputFile && file.encryptThanArchiveInput)
        {
            Encryption encryption = new Encryption();
            ZipWork zipWork = new ZipWork();
            File inputFile = new File( zipWork.read(file.nameInputFile+".zip"));
            switch (file.typeInputFile)
            {
                case "txt"->{
                    PlainTextProcess txtWork = new PlainTextProcess();
                    try {

                        File outputFile = new File("enc_out.txt");
                        encryption.decryptFile(inputFile,outputFile);
                        result = txtWork.readFromPlainTextFile("enc_out.txt");
                        outputFile.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                case "json"->{
                    JSONWork jsonWork = new JSONWork();
                    try {
                        File outputFile = new File("enc_out.json");
                        encryption.decryptFile(inputFile,outputFile);
                        result =jsonWork.readFromJSON("enc_out.json");
                        outputFile.delete();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                case "xml"->{
                    XMLWork xmlWork = new XMLWork();
                    try {
                        File outputFile = new File("enc_out.xml");
                        encryption.decryptFile(inputFile,outputFile);
                        result =  xmlWork.readFromXML("enc_out.xml");
                        outputFile.delete();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                default -> throw new IllegalStateException("Unexpected value: " + file.typeInputFile);
            }
            inputFile.delete();

        }
        else {
            file.nameInputFile += "." + file.typeInputFile;
            switch (file.typeInputFile)
            {
                case "txt" -> {
                    PlainTextProcess txtWork = new PlainTextProcess();
                    try {
                        result = txtWork.readFromPlainTextFile(file.nameInputFile);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case "json" -> {
                    JSONWork jsonWork = new JSONWork();
                    try {
                        result = jsonWork.readFromJSON(file.nameInputFile);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                case "xml" -> {
                    XMLWork xmlWork = new XMLWork();
                    try {
                        result = xmlWork.readFromXML(file.nameInputFile);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        }
        calculate();

    }
    private void calculate(){
        switch (file.typeOfCalculate) {
            case "1" -> {
                result = calculator.calculateSimple(result);
            }
            case "2" -> {
                result = calculator.calculateWithRegex(result);

            }
            case "3" -> {
                result = calculator.calculateLibrary(result);
            }
            default -> throw new IllegalStateException("Unexpected value: " + file.typeOfCalculate);
        }
    }

    private void archiveTxt(){
        PlainTextProcess plainTextProcess = new PlainTextProcess();
        plainTextProcess.writeInPlainText(result, file.nameOutputFile + "." + file.typeOutputFile);
        ZipWork zipWork = new ZipWork();
        zipWork.write(file.nameOutputFile,file.typeOutputFile, file.typeOfOutputArchive);
        File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
        fileToDelete.delete();

    }
    private void archiveJson(){
        JSONWork jsonWork = new JSONWork();
        jsonWork.writeInJSON(result,  file.nameOutputFile + "." + file.typeOutputFile);
        ZipWork zipWork = new ZipWork();
        zipWork.write(file.nameOutputFile,file.typeOutputFile,file.typeOfOutputArchive);
        File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
        fileToDelete.delete();


    }
    private void archiveXml(){
        XMLWork xmlWork = new XMLWork();
        xmlWork.writeInXml(result,file.nameOutputFile + "." + file.typeOutputFile);
        ZipWork zipWork = new ZipWork();
        zipWork.write(file.nameOutputFile,file.typeOutputFile,file.typeOfOutputArchive);

        File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
        fileToDelete.delete();


    }
    private void encArch() {
        Encryption encryption = new Encryption();
        File inputFile = new File(file.nameOutputFile +".zip");
        File outputFile = new File(file.nameOutputFile + ".enc");
        try {
            encryption.encryptFile(inputFile, outputFile);
        } catch (Exception e) {
            System.out.println("Error encrypt file " + e);
        }
        inputFile.delete();
    }
    private void archEnc(){
        enc();
        ZipWork zipWork = new ZipWork();
        zipWork.write(file.nameOutputFile,"enc",file.typeOfOutputArchive);
        File fileToDelete = new File(file.nameOutputFile + ".enc");
        fileToDelete.delete();
    }

    private void enc() {
        Encryption encryption = new Encryption();
        File inputFile = new File(file.nameOutputFile + file.typeOutputFile);
        File outputFile = new File(file.nameOutputFile + ".enc");
        try {
            encryption.encryptFile(inputFile, outputFile);
        } catch (Exception e) {
            System.out.println("Error encrypt file " + e);
        }
        inputFile.delete();
    }

    public void callFunctionOutput() {


        if(file.isEncryptOutputFile && !file.isArchiveOutputFile)
        {

            switch (file.typeOutputFile) {
                case "txt" -> {
                    PlainTextProcess plainTextProcess = new PlainTextProcess();
                    plainTextProcess.writeInPlainText(result,file.nameOutputFile + file.typeOutputFile);
                    enc();
                }
                case "json" -> {
                    JSONWork jsonWork = new JSONWork();
                    jsonWork.writeInJSON(result, file.nameOutputFile + file.typeOutputFile);
                    enc();
                }
                case "xml" -> {
                    XMLWork xmlWork = new XMLWork();
                    xmlWork.writeInXml(result,file.nameOutputFile + file.typeOutputFile);
                    enc();
                }
            }
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
                    encArch();
                }
                case "json" -> {
                    archiveJson();
                    encArch();
                }
                case "xml" -> {
                    archiveXml();
                    encArch();
                }
                default -> throw new IllegalStateException("Unexpected value: " + file.typeOutputFile);
            }
        }
        else if(file.isEncryptOutputFile && file.encryptThanArchiveOutput)
        {
            switch (file.typeOutputFile) {
                case "txt" -> {
                    PlainTextProcess plainTextProcess = new PlainTextProcess();
                    plainTextProcess.writeInPlainText(result,file.nameOutputFile + file.typeOutputFile);
                    archEnc();

                }
                case "json" -> {
                    JSONWork jsonWork = new JSONWork();
                    jsonWork.writeInJSON(result, file.nameOutputFile + file.typeOutputFile);
                    archEnc();
                }
                case "xml" -> {
                    XMLWork xmlWork = new XMLWork();
                    xmlWork.writeInXml(result,file.nameOutputFile + file.typeOutputFile);
                    archEnc();
                }
                default -> throw new IllegalStateException("Unexpected value: " + file.typeOutputFile);
            }


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


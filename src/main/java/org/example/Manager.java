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
        file.nameInputFile += "." + file.typeInputFile;

        if(!file.isArchiveInputFile && file.isEncryptInputFile)
        {

            switch (file.typeInputFile)
            {
                case "txt"->{



                }
                case "json"->{

                }
                case "xml"->{

                }
                default -> {

                }

            }

        }
        else if(file.isArchiveInputFile && !file.isEncryptInputFile)
        {
            switch (file.typeInputFile)
            {
                case "txt"->{

                }
                case "json"->{

                }
                case "xml"->{

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

                }
                case "json"->{

                }
                case "xml"->{

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

                }
                case "json"->{

                }
                case "xml"->{

                }
                default -> {

                }

            }

        }
        else {
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


    public void callFunctionOutput() {


        if(file.isEncryptOutputFile && !file.isArchiveOutputFile)
        {
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

        else if(!file.isEncryptOutputFile && file.isArchiveOutputFile)
        {

            switch (file.typeOutputFile) {
                case "txt" -> {
                    PlainTextProcess plainTextProcess = new PlainTextProcess();
                    try {
                        plainTextProcess.writeInPlainText(result, file.nameOutputFile + "." + file.typeOutputFile);
                    }
                    catch (Exception e)
                    {
                        System.out.println("error write in txt");
                    }
                    ZipWork zipWork = new ZipWork();
                    zipWork.write(result,file.nameOutputFile,file.typeOutputFile);
                    File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
                    boolean delete = fileToDelete.delete();

                }
                case "json" -> {
                    JSONWork jsonWork = new JSONWork();
                    jsonWork.writeInJSON(result,  file.nameOutputFile + "." + file.typeOutputFile);
                    ZipWork zipWork = new ZipWork();
                    zipWork.write(result,file.nameOutputFile,file.typeOutputFile);

                    File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
                    boolean delete = fileToDelete.delete();

                }
                case "xml" -> {
                    XMLWork xmlWork = new XMLWork();
                    xmlWork.writeInXml(result,file.nameOutputFile + "." + file.typeOutputFile);
                    ZipWork zipWork = new ZipWork();
                    zipWork.write(result,file.nameOutputFile,file.typeOutputFile);

                    File fileToDelete = new File(file.nameOutputFile + "." + file.typeOutputFile);
                    boolean delete = fileToDelete.delete();


                }
                default -> {

                }
            }

        }
        else if(file.isEncryptOutputFile  && file.ArchiveThanEncryptOutput)
        {
            file.nameOutputFile += "." + file.typeOutputFile;
            switch (file.typeOutputFile) {
                case "txt" -> {

                }
                case "json" -> {

                }
                case "xml" -> {

                }
                default -> {

                }
            }
        }
        else if(file.isEncryptOutputFile && file.encryptThanArchiveOutput)
        {
            file.nameOutputFile += "." + file.typeOutputFile;
            switch (file.typeOutputFile) {
                case "txt" -> {

                }
                case "json" -> {

                }
                case "xml" -> {

                }
                default -> {

                }
            }
        }
        else {
            file.nameOutputFile += "." + file.typeOutputFile;
            switch (file.typeOutputFile) {
                case "txt" -> {


                    PlainTextProcess txtWork = new PlainTextProcess();
                    try {
                        txtWork.writeInPlainText(result, file.nameOutputFile);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                case "json" -> {
                    JSONWork jsonWork = new JSONWork();
                    try {
                        jsonWork.writeInJSON(result, file.nameOutputFile);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                case "xml" -> {
                    XMLWork xmlWork = new XMLWork();
                    try {
                        xmlWork.writeInXml(result, file.nameOutputFile);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }

            }

        }
    }

}


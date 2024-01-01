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

        file.nameOutputFile += "." + file.typeOutputFile;
        if(file.isEncryptOutputFile && !file.isArchiveOutputFile)
        {
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

        else if(!file.isEncryptOutputFile && file.isArchiveOutputFile)
        {
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
        else if(file.isEncryptOutputFile  && file.ArchiveThanEncryptOutput)
        {
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


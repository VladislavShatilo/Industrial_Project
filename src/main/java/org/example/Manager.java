package org.example;

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



        switch (file.typeInputFile) {
            case "txt": {
                PlainTextProcess txtWork = new PlainTextProcess();
                try {
                    if(file.isEncryptInputFile.equals("NO") ){
                        Vector<String> data = txtWork.readFromPlainTextFile(file.nameInputFile);
                        result = calculator.calculateSimple(data);}
                    else {
                        EncryptionWork encryptionWork =new EncryptionWork();
                        encryptionWork.decrypt(file.nameInputFile);


                    }



                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "json": {
                JSONWork jsonWork = new JSONWork();
                try {

                    Vector<String> data = jsonWork.readFromJSON(file.nameInputFile);
                    result = calculator.calculateSimple(data);


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            }

            case "xml": {
                XMLWork xmlWork = new XMLWork();
                try {
                        Vector<String> data = xmlWork.readFromXML(file.nameInputFile);
                        result = calculator.calculateSimple(data);



                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            }
            case "zip":{
                ZipWork zipWork = new ZipWork();
                zipWork.readZipTXT( file.nameInputFile);
            }



        }
    }


    public void callFunctionOutput()
    {

        file.nameOutputFile += "." + file.typeOutputFile;
        switch (file.typeOutputFile)
        {
            case "txt":{
                PlainTextProcess txtWork = new PlainTextProcess();
                try {
                    txtWork.writeInPlainText(result, file.nameOutputFile);
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            }
            case "json":{
                JSONWork jsonWork = new JSONWork();
                try {
                    jsonWork.writeInJSON(result, file.nameOutputFile);
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            }

            case "xml":{
                XMLWork xmlWork = new XMLWork();
                try {
                    xmlWork.writeInXml(result, file.nameOutputFile);
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            }
        }
    }

}


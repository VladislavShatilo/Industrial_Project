package com.example.ppfx;

public class Main {
    public static void main(String[] args) {
        FileInformationStructure file = new FileInformationStructure();
        ChooseFileInformation fillFileInfo = new ChooseFileInformation(file);
        Manager manage = new Manager(file);
        fillFileInfo.start();

        fillFileInfo.typeInputFileChoice();
        fillFileInfo.nameInputFile();
        fillFileInfo.encryptArchiveInputType();
        fillFileInfo.choseTypeOfCalculation();
        manage.callFunctionInput();

        fillFileInfo.typeOutputFileChoice();
        fillFileInfo.nameOutputFile();
        fillFileInfo.encryptArchiveOutputType();





        manage.callFunctionOutput();



    }


}
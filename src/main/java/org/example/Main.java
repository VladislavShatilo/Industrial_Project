package org.example;

public class Main {
    public static void main(String[] args) {
        FileInformationStructure file = new FileInformationStructure();
        ChooseFileInformation fillFileInfo = new ChooseFileInformation(file);
        Manager manage = new Manager(file);
        fillFileInfo.start();

        fillFileInfo.typeInputFileChoice();
        fillFileInfo.nameInputFile();

        fillFileInfo.encryptArchiveInputType();
        fillFileInfo.typeOutputFileChoice();
        fillFileInfo.nameOutputFile();
        fillFileInfo.encryptArchiveOutputType();
        fillFileInfo.choseTypeOfCalculation();



        manage.callFunctionInput();
        manage.callFunctionOutput();



    }


}
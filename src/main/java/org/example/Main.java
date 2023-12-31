package org.example;

public class Main {
    public static void main(String[] args) {
        FileInformationStructure file = new FileInformationStructure();
        ChooseFileInformation fillFileInfo = new ChooseFileInformation(file);
        Manager manage = new Manager(file);
        fillFileInfo.start();
        // fillFileInfo.isArchiveInputFile();
        fillFileInfo.typeInputFileChoice();
        fillFileInfo.nameInputFile();

        fillFileInfo.isEncryptInputFile();

        //fillFileInfo.typeOutputFileChoice();fillFileInfo.nameOutputFile();

        //fillFileInfo.isEncryptOutputFile();
        // fillFileInfo.isArchiveOutputFile();
        manage.callFunctionInput();
        //manage.callFunctionOutput();
    }


}
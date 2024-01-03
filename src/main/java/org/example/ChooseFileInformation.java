package org.example;

import java.util.Scanner;

public class ChooseFileInformation
{
    private boolean correctData;
    private final FileInformationStructure file ;
    private final Scanner scan ;
    ChooseFileInformation(FileInformationStructure basicFile)
    {
        file = basicFile;
        scan = new Scanner(System.in);

    }
    public void start()
    {

        System.out.println("""
                    
                    Hi this is application, which can read files, process information and write in file
                    You can work with different type of files(txt,json,xml,zip,rar, encrypt files)
                    Do you want to start?
                    1. YES
                    2. NO""");
        int startWork;
        startWork = scan.nextInt();
        do{
            if (startWork == 1) {

                correctData = true;

            } else if (startWork == 0) {

                correctData = true;
            } else {
                System.out.println("Incorrect data, please input again!\n");
                correctData = false;
            }
        } while(!correctData);

    }

    public void typeInputFileChoice()
    {
        System.out.println("What type of input file do you want to work with?\n" +
                "txt,json,xml,zip");
        do {

            file.typeInputFile = scan.next();

            if (file.typeInputFile.equals("txt") || file.typeInputFile.equals("json")||file.typeInputFile.equals("xml")||file.typeInputFile.equals("zip")) {
                //first work
                correctData = true;

            }
            else {
                System.out.println("Incorrect data, please input again!");
                correctData = false;
            }
        }while(!correctData);


    }
    public void encryptArchiveInputType()
    {
        int chose;
        System.out.println("What type of input file do you want to work with?\n" +
                "1. Just file\n" +
                "2. Encrypt file\n" +
                "3. Archive file\n" +
                "4. Encrypt -> Archive file\n" +
                "5. Archive -> Encrypt file");
        do {
            chose= scan.nextInt();
            if (chose == 1 || chose == 2 || chose == 3 || chose == 4 || chose ==5) {
                correctData = true;
            }
            else {
                System.out.println("Incorrect data, please input again!");
                correctData = false;
            }
        }while(!correctData);

        switch (chose) {
            case 1 -> {
                file.isArchiveInputFile = false;
                file.isEncryptInputFile = false;
            }
            case 2 -> {
                file.isArchiveInputFile = false;
                file.isEncryptInputFile = true;
            }
            case 3 -> {
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = false;
            }
            case 4 -> {
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = true;
                file.encryptThanArchiveInput =true;
            }
            case 5 ->{
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = true;
                file.ArchiveThanEncryptInput =true;
            }
            default -> {
            }
        }


    }
    public void choseTypeOfCalculation() {
        int chose;
        System.out.println("What type of calculation method do you want to work with?\n" +
                "1. Recursion\n" +
                "2. Regex\n" +
                "3. Library");

        do {
            chose = scan.nextInt();
            if (chose == 1 || chose == 2 || chose == 3) {
                correctData = true;
            } else {
                System.out.println("Incorrect data, please input again!");
                correctData = false;
            }
        } while (!correctData);

        switch (chose) {
            case 1 -> file.typeOfCalculate = "1";
            case 2 -> file.typeOfCalculate = "2";
            case 3 -> file.typeOfCalculate = "3";
            default -> throw new IllegalStateException("Unexpected value: " + chose);
        }
    }

    public void nameInputFile()
    {
        System.out.println("Input name of input file");
        file.nameInputFile = scan.next();
    }



    public void encryptArchiveOutputType()
    {
        int chose;
        System.out.println("What type of output file do you want to work with?\n" +
                "1. Just file\n" +
                "2. Encrypt file\n" +
                "3. Archive file\n" +
                "4. Encrypt -> Archive file\n" +
                "5. Archive -> Encrypt file");
        do {
            chose= scan.nextInt();
            if (chose == 1 || chose == 2 || chose == 3 || chose == 4 || chose ==5) {
                correctData = true;
            }
            else {
                System.out.println("Incorrect data, please input again!");
                correctData = false;
            }
        }while(!correctData);

        switch (chose) {
            case 1 -> {
                file.isArchiveOutputFile = false;
                file.isEncryptOutputFile = false;
            }
            case 2 -> {
                file.isArchiveOutputFile = false;
                file.isEncryptOutputFile = true;
            }
            case 3 -> {
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile = false;
            }
            case 4-> {
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile= true;
                file.encryptThanArchiveOutput= true;

            }
            case 5->{
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile= true;
                file.ArchiveThanEncryptOutput =true;
            }
            default -> {
            }
        }


    }



    public void typeOutputFileChoice()
    {
        System.out.println("What type of output file do you want to work with?\n" +
                "txt,json,xml");
        do {

            file.typeOutputFile = scan.next();

            if (file.typeOutputFile.equals("txt") || file.typeOutputFile.equals("json")||file.typeOutputFile.equals("xml")) {
                //first work
                correctData = true;

            }
            else {
                System.out.println("Incorrect data, please output again!\n");
                correctData = false;
            }
        } while(!correctData);


    }

    public void nameOutputFile()
    {
        System.out.println("Input name of output file");

        file.nameOutputFile = scan.next() ;

    }




}


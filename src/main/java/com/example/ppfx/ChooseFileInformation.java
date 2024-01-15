package com.example.ppfx;

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

        do{
            startWork = scan.nextInt();
            if (startWork == 1) {

                correctData = true;

            } else if (startWork == 2) {
                System.exit(0);
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
    private int correctCheckArchiveEnc( )
    {
        int chose;
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
        return chose;
    }
    public void encryptArchiveInputType()
    {

        System.out.println("""
                What type of input file do you want to work with?
                1. Just file
                2. Encrypt file
                3. Archive file
                4. Encrypt -> Archive file
                5. Archive -> Encrypt file""");

        int chose = correctCheckArchiveEnc();
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
                chooseInputArchive();
            }
            case 4 -> {
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = true;
                file.encryptThanArchiveInput =true;
                chooseInputArchive();
            }
            case 5 ->{
                file.isArchiveInputFile = true;
                file.isEncryptInputFile = true;
                file.ArchiveThanEncryptInput =true;
                chooseInputArchive();
            }
            default -> {
            }
        }


    }
    public void choseTypeOfCalculation() {
        int chose;
        System.out.println("""
                What type of calculation method do you want to work with?
                1. Recursion
                2. Regex
                3. Library""");

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

        System.out.println("""
                What type of output file do you want to work with?
                1. Just file
                2. Encrypt file
                3. Archive file
                4. Encrypt -> Archive file
                5. Archive -> Encrypt file""");
        int chose = correctCheckArchiveEnc();
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
                chooseOutputArchive();
            }
            case 4-> {
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile= true;
                file.encryptThanArchiveOutput= true;
                chooseOutputArchive();

            }
            case 5->{
                file.isArchiveOutputFile = true;
                file.isEncryptOutputFile= true;
                file.ArchiveThanEncryptOutput =true;
                chooseOutputArchive();
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
    private int correctCheckArchive()
    {
        int chose;
        do {
            chose= scan.nextInt();
            if (chose == 1 || chose == 2) {
                correctData = true;
            }
            else {
                System.out.println("Incorrect data, please input again!");
                correctData = false;
            }
        }while(!correctData);
        return chose;

    }
     private void chooseInputArchive()
     {
         System.out.println("""
                 Choose type of archive
                 1 .zip
                 2. .rar""");
         int chose = correctCheckArchive();


         switch (chose) {
             case 1 -> file.typeOfInputArchive = "zip";
             case 2 -> file.typeOfInputArchive = "rar";
             default -> throw new IllegalStateException("Unexpected value: " + chose);
         }


     }
    private void chooseOutputArchive()
    {
        System.out.println("""
                 Choose type of output archive
                 1 .zip
                 2. .rar""");
        int chose = correctCheckArchive();

        switch (chose) {
            case 1 -> file.typeOfOutputArchive = "zip";
            case 2 -> file.typeOfOutputArchive = "rar";
            default -> throw new IllegalStateException("Unexpected value: " + chose);
        }


    }

}


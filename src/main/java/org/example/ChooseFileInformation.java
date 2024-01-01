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
    public void encryptArchiveType()
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

        switch (chose){
            case 1:
            {

                break;
            }
            case 2:
            {
                break;
            }
            case 3:
            {
                break;
            }
            case 4:
            {
                break;
            }
            case 5:
            {
                break;
            }
            default:
            {
                break;
            }


        }


    }



    public void nameInputFile()
    {
        System.out.println("Input name of input file");

        file.nameInputFile = scan.next();
    }

    public  void isEncryptInputFile()
    {
        String isEncrypt;
        System.out.println("""
                    Input file is encrypt?
                    YES
                    NO""");
        do {
            isEncrypt = scan.next();
            if (isEncrypt.equals("YES")) {

                file.isEncryptInputFile =true;
                correctData = true;
            }
            else if(isEncrypt.equals("NO"))
            {
                file.isEncryptInputFile =false;
                correctData = true;
            }
            else {
                System.out.println("Incorrect data, please input again!\n");
                correctData = false;
            }
        } while(!correctData);
    }

    public void isArchiveInputFile()
    {
        String isArchive;
        System.out.println("""
                    Input file is archive?
                    YES
                    NO""");
        do {
            isArchive = scan.next();
            if (isArchive.equals("YES")) {
                file.isArchiveInputFile =true;
                correctData = true;
            }
            else if(isArchive.equals("NO"))
            {
                file.isArchiveInputFile = false;
                correctData = true;
            }
            else {
                System.out.println("Incorrect data, please input again!\n");
                correctData = false;
            }
        } while(!correctData);
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

    public  void isEncryptOutputFile()
    {
        String isEncrypt;
        System.out.println("""
                    Output file is encrypt?
                    YES
                    NO""");
        do {
            isEncrypt = scan.next();
            if (isEncrypt.equals("YES") ) {
                file.isEncryptOutputFile = true;
                correctData = true;
            }
            else if(isEncrypt.equals("NO"))
            {
                file.isEncryptOutputFile =false;
                correctData = true;
            }
            else {
                System.out.println("Incorrect data, please input again!\n");
                correctData = false;
            }
        } while(!correctData);
    }
    public void isArchiveOutputFile()
    {
        String isArchive;
        System.out.println("""
                    Output file is archive?
                    YES
                    NO""");
        do {
            isArchive = scan.next();
            if (isArchive.equals("YES")) {
                file.isArchiveOutputFile= true;

                correctData = true;
            }
            else if(isArchive.equals("NO"))
            {
                file.isArchiveOutputFile = false;
                correctData = true;
            }
            else {
                System.out.println("Incorrect data, please input again!\n");
                correctData = false;
            }
        } while(!correctData);
    }



}


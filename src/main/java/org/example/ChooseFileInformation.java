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

        public void nameInputFile()
        {
            System.out.println("Input name of input file");

            file.nameInputFile = scan.next();
        }

        public  void isEncryptInputFile()
        {
            System.out.println("""
                    Input file is encrypt?
                    YES
                    NO""");
          do {
                file.isEncryptInputFile = scan.next();
                if (file.isEncryptInputFile.equals("YES") || file.isEncryptInputFile.equals("NO")) {

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
            System.out.println("""
                    Input file is archive?
                    YES
                    NO""");
           do {
                file.isArchiveInputFile = scan.next();
                if (file.isArchiveInputFile.equals("YES") || file.isArchiveInputFile.equals("NO")) {

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
            System.out.println("""
                    Output file is encrypt?
                    YES
                    NO""");
            do {
                file.isEncryptOutputFile = scan.next();
                if (file.isEncryptOutputFile.equals("YES") || file.isEncryptOutputFile.equals("NO")) {

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
            System.out.println("""
                    Output file is archive?
                    YES
                    NO""");
            do {
                file.isArchiveOutputFile = scan.next();
                if (file.isArchiveOutputFile.equals("YES") || file.isArchiveOutputFile.equals("NO")) {

                    correctData = true;
                }
                else {
                    System.out.println("Incorrect data, please input again!\n");
                    correctData = false;
                }
            } while(!correctData);
        }



    }


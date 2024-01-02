package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final  String key = "ThisIsASecretKey";

    public  void encryptFile(File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);

    }

    public void  decryptFile( File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);

    }

    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile)
            throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }

    public static class CryptoException extends Exception {
        public CryptoException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }

//    public static void main(String[] args) {
//        try {
//
//            File inputFile = new File("zip_txt.zip");
//            File encryptedFile = new File("zip_enc_txt.enc");
//            encryptFile(inputFile, encryptedFile);
//
//            File inputFile1 = new File("zip_json.zip");
//            File encryptedFile1 = new File("zip_enc_json.enc");
//            encryptFile(inputFile1, encryptedFile1);
//
//            File inputFile2 = new File("zip_xml.zip");
//            File encryptedFile2 = new File("zip_enc_xml.enc");
//            encryptFile(inputFile2, encryptedFile2);
//
//
//        } catch (CryptoException e) {
//            System.err.println(e.getMessage());
//        }
//    }
}

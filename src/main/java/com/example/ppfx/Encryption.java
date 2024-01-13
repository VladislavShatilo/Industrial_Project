package com.example.ppfx;

import javafx.scene.control.Alert;

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
    private static void showErrorAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error encryption-decryption");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void encryptFile(File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile);

    }

    public  void  decryptFile( File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);

    }

    private static void doCrypto(int cipherMode, File inputFile, File outputFile)
            throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(Encryption.key.getBytes(), ALGORITHM);
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
           System.err.println("Error encryption-decryption" + ex.getMessage());
           showErrorAlert(ex.getMessage());
           System.exit(1);
        }
    }

    public static class CryptoException extends Exception {
        public CryptoException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }


}

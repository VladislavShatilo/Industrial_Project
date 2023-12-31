package org.example;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class EncryptionWork {

    public  byte[] encryptFile(String fileName) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec("oleg1337oleg2228".getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] fileContent = Files.readAllBytes(Paths.get(fileName));

            return cipher.doFinal(fileContent);
        } catch (Exception e)
        {
            System.out.println("encrypt file error");

        }
        return null;
    }

    public  void decrypt(String fileName) {
        byte[] decodedKey = Base64.getDecoder().decode("oleg1337oleg2228");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            String line;
            while ((line = reader.readLine()) != null) {
                Cipher cipher = Cipher.getInstance("AES");
                SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
                cipher.init(Cipher.DECRYPT_MODE, originalKey);
                byte[] fileContent = Files.readAllBytes(Paths.get(fileName));
                byte[] fileContent1 = cipher.doFinal(Base64.getDecoder().decode(line));
                for(byte b:fileContent1)
                {
                    System.out.print((char) b);
                }
            }




        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occured while decrypting data", e);
        }
    }


}

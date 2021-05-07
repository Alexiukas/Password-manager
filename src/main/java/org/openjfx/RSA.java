package org.openjfx;

import javax.crypto.Cipher;
import java.io.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class RSA {

    public byte[] encrypt(String password) {
        PublicKey key = FileController.getPublicKey();
        byte[] cipherText;
        try {
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(password.getBytes());
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(PrivateKey key, String fileName) {
        byte[] dectyptedText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            File file = new File("C:\\Users\\Alex\\Desktop\\Password manager\\" + User.username + "\\Manager\\" + fileName);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(":");
            scanner.next();
            String text = scanner.next();
            byte[] cipherText = PBKDF2.fromHex(text);
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(cipherText);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        assert dectyptedText != null;
        return new String(dectyptedText);
    }



}


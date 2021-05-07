package org.openjfx;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GenerateKeys {
    private KeyPairGenerator keyGenerator;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private final Base64.Encoder encoder = Base64.getEncoder();


    public GenerateKeys (int length){
        try {
            keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(length);
            createKeys();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createKeys (){
        KeyPair keyPair = keyGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    public void saveKeys(String name){
       saveKeysToFile(name);
    }

    private void savePBKey (String name){
        String path = "C:\\Users\\Alex\\Desktop\\Password manager\\";
        File file = new File(path + name + "\\Publickey.txt");
        try {
            boolean bool = file.createNewFile();
            if (bool){
                System.out.println("PBKey file created");
            } else{
                System.out.println("PBKey file not created");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        byte[] encodedPublicKey = publicKey.getEncoded();
        String b64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))){
            printWriter.print(b64PublicKey);
        } catch (Exception e){
            e.printStackTrace();
        }

//        try (OutputStreamWriter publicKeyWriter =
//                     new OutputStreamWriter(
//                             new FileOutputStream(file),
//                             StandardCharsets.US_ASCII.newEncoder())) {
//            publicKeyWriter.write(b64PublicKey);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    private void savePRKey (String name){
        String path = "C:\\Users\\Alex\\Desktop\\Password manager\\";
        File file = new File(path + name + "\\Privatekey.txt");
        try {
            boolean bool = file.createNewFile();
            if (bool){
                System.out.println("PRKey file created");
            } else{
                System.out.println("PRKey file not created");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        byte[] encodedPrivateKey = privateKey.getEncoded();
        String b64PrivateKey = Base64.getEncoder().encodeToString(encodedPrivateKey);

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))){
            printWriter.print(b64PrivateKey);
        } catch (Exception e){
            e.printStackTrace();
        }

//        try (OutputStreamWriter privateKeyWriter =
//                     new OutputStreamWriter(
//                             new FileOutputStream(file),
//                             StandardCharsets.US_ASCII.newEncoder())) {
//            privateKeyWriter.write(b64PrivateKey);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public void saveKeysToFile (String name){
        String path = "C:\\Users\\Alex\\Desktop\\Password manager\\";
        File file = new File(path + name + "\\Privatekey.txt");
        try {
            boolean bool = file.createNewFile();
            if (bool){
                System.out.println("PRKey file created");
            } else{
                System.out.println("PRKey file not created");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(file));
            publicKeyOS.writeObject(privateKey);
            publicKeyOS.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        File filex = new File(path + name + "\\Publickey.txt");
        try {
            boolean bool = filex.createNewFile();
            if (bool){
                System.out.println("PBKey file created");
            } else{
                System.out.println("PBKey file not created");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(filex));
            publicKeyOS.writeObject(publicKey);
            publicKeyOS.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

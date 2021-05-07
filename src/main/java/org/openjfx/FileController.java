package org.openjfx;

import java.io.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FileController extends PBKDF2 {

    private final static String path = "C:\\Users\\Alex\\Desktop\\Password manager\\";

    public static boolean doesUserExist(String name){
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        String lowCaseName = name.toLowerCase();
        for(File fileName: listOfFiles){
            if(fileName.getName().toLowerCase().equals(lowCaseName))
                return true;
        }
        System.out.println("User doesn't exist");
        return false;
    }

    public static void createUserFolder(String name) {
        File newFolder = new File(path + name);
        boolean bool = newFolder.mkdir();
        if(bool)
            System.out.println("directory was created");
        else
            System.out.println("directory wasn't created");

    }

    public static void createPasswordMangerFolder (String user){
        File newFolder = new File(path + user + "\\Manager");
        if (!newFolder.exists()){
            boolean bool = newFolder.mkdir();
            if(bool)
                System.out.println("directory was created");
            else
                System.out.println("directory wasn't created");
        } else
            System.out.println("Folder already  exists");

    }

    public static void createPasswordFile(String title, byte[] password, String application, String info){
        String pathToPass = path + User.username + "\\Manager\\" + title + ".txt";
        File newFile = new File(pathToPass);
        if(!newFile.exists()){
            try {
                boolean bool = newFile.createNewFile();
                if(bool){
                    try (FileWriter fos = new FileWriter(newFile)) {
                        fos.write(title+":"+PBKDF2.toHex(password)+":"+application+":"+info+":");
                        fos.flush();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void createUserFile(String name, String password)  {
        try {
            File newFile = new File(path + name +"\\"+name +".txt");
            boolean bool = newFile.createNewFile();
            if (bool)
                System.out.println("file created");
            else {
                System.out.println("file was not created");
                return;
            }
            FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(hash(password));
            printWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readPasswordFile(String title){
        String pathToFile = path + User.username + "\\Manager\\" + title;
        File file = new File(pathToFile);
        try(Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(":");
            String realTitle, app, info;
            realTitle = scanner.next();
            scanner.next();
            app = scanner.next();
            info = scanner.next();
            ArrayList<String> list = new ArrayList<>();
            list.add(realTitle);
            list.add(app);
            list.add(info);
            return list;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void deletePasswordFile (String title){
        String pathToFile = path + User.username + "\\Manager\\" + title;
        File file = new File(pathToFile);
        if(file.delete()){
            System.out.println("File has been deleted");
        } else {
            System.out.println("File hasn't been deleted");
        }
    }

    public static void updatePassword(String title, String newPass){
        String pathToFile = path + User.username + "\\Manager\\" + title;
        File file = new File(pathToFile);
        try(Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(":");
            scanner.next();
            String currentPass = scanner.next();
            RSA rsa = new RSA();
            byte[] bytedPass =  rsa.encrypt(newPass);
            String newHexedPass = PBKDF2.toHex(bytedPass);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            String maulei;
            maulei = line.replace(currentPass, newHexedPass);
            FileWriter fileWriter = new FileWriter(pathToFile);
            fileWriter.write(maulei);
            fileWriter.close();
            bufferedReader.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean readFile(String name, String password){
        File file = new File(path  + name + "\\" + name + ".txt");
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(":");
            String line = scanner.next();
            byte[] salt = fromHex(line);
            line = scanner.next().trim();
            scanner.close();
            return isPasswordsEquals(password, salt, line);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isPathValid(String fileName){
        File file = new File(path+fileName);
        return file.exists();
    }

    public static PrivateKey getPrivateKey(){
        String pathToKey = path + User.username + "\\Privatekey.txt";
        File file = new File(pathToKey);
        ObjectInputStream inputStream;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            return (PrivateKey) inputStream.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static PublicKey getPublicKey(){
        String pathToKey = path + User.username + "\\Publickey.txt";
        File file = new File(pathToKey);
        ObjectInputStream inputStream;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            return (PublicKey) inputStream.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

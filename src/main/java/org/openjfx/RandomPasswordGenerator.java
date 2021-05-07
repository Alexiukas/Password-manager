package org.openjfx;

import java.util.Random;

public class RandomPasswordGenerator {
    private final String all = "qwertyuiop[]asdfghjkl;'zxcvbnm,./123456789!@#$%^&*()_-=+";

    public String generatePassword(){
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for(int i = 0; i<15;i++){
            int x = random.nextInt(56);
            int y = random.nextInt(2);
            char letter = all.charAt(x);
            if(Character.isLetter(letter)){
                if(y == 0)
                    letter = Character.toLowerCase(letter);
                else
                    letter = Character.toUpperCase(letter);
            }
            password.append(letter);
        }
        return password.toString();
    }
}

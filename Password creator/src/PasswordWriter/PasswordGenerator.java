package PasswordWriter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.swing.SwingConstants.*;
import static javax.swing.WindowConstants.*;

public class PasswordGenerator  {

    JFrame frame = new JFrame("Пароль");
    JLabel label = new JLabel("Сгенерированный пароль:");
    JTextField passwordResult;

    PasswordGenerator(boolean hasNumbers, boolean hasCapitalLetters, int length){

        frame.setBounds(400, 250, 350, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String password = "";
        String arrayOfSymbols = getStringUnderConditions(hasNumbers,hasCapitalLetters);
        boolean flag = true;

        while (flag){
            for (int i = 0; i < length; i++){
                password+= ""+randomCharUnderConditions(arrayOfSymbols);
            }

            if(hasNumbers && hasCapitalLetters){
                if (password.length() - password.replaceAll("[0-9]", "").length() != 0
                        &&
                        password.length() - password.replaceAll("[A-Z]", "").length() != 0) {
                    flag=false;
                } else password="";
            }

            else if (hasNumbers){
                if (password.length() - password.replaceAll("[0-9]", "").length() != 0) {
                    flag=false;
                } else password="";
            }

            else if(hasCapitalLetters){
                if (password.length() - password.replaceAll("[A-Z]", "").length() != 0) {
                    flag=false;
                } else password="";
            }

            else flag=false;
        }

        label.setText("Ваш пароль: ");
        label.setVisible(true);
        label.setLayout(null);
        label.setBounds(105,50,200,25);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        frame.add(label);

        passwordResult = new JTextField();
        passwordResult.setVisible(true);
        passwordResult.setLocation(10,80);
        passwordResult.setSize(315,30);
        passwordResult.setHorizontalAlignment(CENTER);
        passwordResult.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        passwordResult.setEditable(false);
        passwordResult.setText(password);
        passwordResult.setLayout(null);
        frame.add(passwordResult);

    }

    private static String getStringUnderConditions(
            boolean hasNumbers,
            boolean hasCapitalLetters){
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if(hasNumbers && hasCapitalLetters) lowerCaseChars+=upperCaseChars+numbers;
        else if(hasNumbers) lowerCaseChars+=numbers;
        else if (hasCapitalLetters) lowerCaseChars+=upperCaseChars;
        return lowerCaseChars;
    }

    private static char randomCharUnderConditions(String string) {
        Random r = new Random();
        return string.charAt(r.nextInt(string.length()));
    }

}
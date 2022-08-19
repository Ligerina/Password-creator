package PasswordWriter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.swing.SwingConstants.*;
import static javax.swing.WindowConstants.*;

public class PasswordGenerator {

    PasswordGenerator(boolean hasNumbers, boolean hasCapitalLetters, int length) {

        JFrame frame = new JFrame("Пароль");
        frame.setBounds(400, 250, 350, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        StringBuilder password = new StringBuilder();
        String arrayOfSymbols = getStringUnderConditions(hasNumbers, hasCapitalLetters);
        boolean hasMoreSymbols = true;

        while (hasMoreSymbols) {
            for (int i = 0; i < length; i++) {
                password.append(getRandomChar(arrayOfSymbols));
            }

            if (hasNumbers && hasCapitalLetters) {
                if (password.length() - password.toString().replaceAll("[0-9]", "").length() != 0
                        &&
                        password.length() - password.toString().replaceAll("[A-Z]", "").length() != 0) {
                    hasMoreSymbols = false;
                } else password = new StringBuilder();
            } else if (hasNumbers) {
                if (password.length() - password.toString().replaceAll("[0-9]", "").length() != 0) {
                    hasMoreSymbols = false;
                } else password = new StringBuilder();
            } else if (hasCapitalLetters) {
                if (password.length() - password.toString().replaceAll("[A-Z]", "").length() != 0) {
                    hasMoreSymbols = false;
                } else password = new StringBuilder();
            } else hasMoreSymbols = false;
        }

        JLabel label = new JLabel("Сгенерированный пароль:");
        label.setText("Ваш пароль: ");
        label.setVisible(true);
        label.setLayout(null);
        label.setBounds(105, 50, 200, 25);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        frame.add(label);

        JTextField passwordResult = new JTextField();
        passwordResult.setVisible(true);
        passwordResult.setLocation(10, 80);
        passwordResult.setSize(315, 30);
        passwordResult.setHorizontalAlignment(CENTER);
        passwordResult.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        passwordResult.setEditable(false);
        passwordResult.setText(password.toString());
        passwordResult.setLayout(null);
        frame.add(passwordResult);

    }

    private static String getStringUnderConditions(
            boolean hasNumbers,
            boolean hasCapitalLetters) {
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        final String numbers = "0123456789";

        if (hasNumbers && hasCapitalLetters) lowerCaseChars += lowerCaseChars.toUpperCase() + numbers;
        else if (hasNumbers) lowerCaseChars += numbers;
        else if (hasCapitalLetters) lowerCaseChars += lowerCaseChars.toUpperCase();
        return lowerCaseChars;
    }

    private static char getRandomChar(String string) {
        Random r = new Random();
        return string.charAt(r.nextInt(string.length()));
    }

}
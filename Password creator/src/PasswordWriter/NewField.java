package PasswordWriter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewField extends JFrame implements ChangeListener {

    private static int gap = 0;
    private int increaseGap(int gap) {
        gap += 30;
        return gap;
    }

    JLabel description;
    JRadioButton hasNumbers;
    JRadioButton hasCapitalLetters;
    JLabel lengthDescription;
    JSlider lengthOfPassword;
    JLabel numberFromSlider;
    JButton createPassword;

    NewField() {

        final int X_PLACEMENT = 35;
        final int Y_PLACEMENT = 20;
        final int WIDTH = 300;
        final int HEIGHT = 25;

        //Название и свойства окна
        setTitle("Создатель паролей");
        setBounds(350, 200, 400, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //Описание условий
        description = new JLabel("Укажите условия для пароля");
        description.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        description.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(description);

        //Кнопка для добавления цифр в пароль
        gap = increaseGap(gap);
        hasNumbers = new JRadioButton("Добавить цифры в пароль");
        hasNumbers.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        hasNumbers.setFocusable(false);
        hasNumbers.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        add(hasNumbers);

        //Кнопка для добавления заглавных букв в пароль
        gap = increaseGap(gap);
        hasCapitalLetters = new JRadioButton("Добавить заглавные буквы в пароль");
        hasCapitalLetters.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH + 50, HEIGHT);
        hasCapitalLetters.setFocusable(false);
        hasCapitalLetters.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        add((hasCapitalLetters));

        //Фраза для длины пароля
        gap = increaseGap(gap);
        lengthDescription = new JLabel("Укажите длину пароля:");
        lengthDescription.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        lengthDescription.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(lengthDescription);

        //Вывод цифры из слайдера
        lengthOfPassword = new JSlider(5,20,5);
        numberFromSlider = new JLabel();
        lengthOfPassword.addChangeListener(this);
        numberFromSlider.setText(lengthOfPassword.getValue() + "");
        numberFromSlider.setBounds(WIDTH-30, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        numberFromSlider.setFont(new Font("Times New Roman", Font.BOLD, 20));
        numberFromSlider.setVisible(true);
        add(numberFromSlider);

        //Выбор длины пароля, исходя из положения слайдера
        gap = increaseGap(gap);
        lengthOfPassword.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT+20);
        lengthOfPassword.setVisible(true);
        lengthOfPassword.setMajorTickSpacing(5);
        lengthOfPassword.setPaintLabels(true);
        lengthOfPassword.setFont(new Font("MV Boli",Font.PLAIN,12));
        add(lengthOfPassword);

        //Кнопка запуска
        gap = increaseGap(gap);
        gap = increaseGap(gap);
        createPassword = new JButton("Сгенерировать пароль");
        createPassword.setVisible(true);
        createPassword.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT+10);
        createPassword.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        //createPassword.setFocusable(false);
        add(createPassword);
        createPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordGenerator pg = new PasswordGenerator(
                        hasNumbers.isSelected(),
                        hasCapitalLetters.isSelected(),
                        Integer.parseInt(numberFromSlider.getText())
                );
            }
        });

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        numberFromSlider.setText(lengthOfPassword.getValue() + "");
    }
}
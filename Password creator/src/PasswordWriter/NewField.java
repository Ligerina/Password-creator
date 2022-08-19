package PasswordWriter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class NewField extends JFrame implements ChangeListener {

    private static int gap = 0;

    private int increaseGap(int gap) {
        gap += 30;
        return gap;
    }

    private final JRadioButton hasNumbers = new JRadioButton("Добавить цифры в пароль");
    private final JRadioButton hasCapitalLetters = new JRadioButton("Добавить заглавные буквы в пароль");
    private final JSlider lengthOfPassword = new JSlider(5, 20, 5);
    private final JLabel numberFromSlider = new JLabel();

    final int X_PLACEMENT = 35;
    final int Y_PLACEMENT = 20;
    final int WIDTH = 300;
    final int HEIGHT = 25;

    NewField() {

        //Название и свойства окна
        setTitle("Создатель паролей");
        setBounds(350, 200, 400, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //Описание условий
        setConditionsOnScreen();

        //Кнопка для добавления цифр в пароль
        setButtonForNumbers();

        //Кнопка для добавления заглавных букв в пароль
        setButtonForCapitalChars();

        //Фраза для длины пароля
        setPhraseForLengthOfPassword();

        //Вывод цифры из слайдера
        setNumberFromSliderOnScreen();

        //Выбор длины пароля, исходя из положения слайдера
        setSlider();

        //Кнопка запуска
        setButtonForPasswordGeneration();

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        numberFromSlider.setText(lengthOfPassword.getValue() + "");
    }

    private void setConditionsOnScreen() {
        final JLabel description;
        description = new JLabel("Укажите условия для пароля");
        description.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        description.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(description);
    }

    private void setButtonForNumbers() {
        gap = increaseGap(gap);
        hasNumbers.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        hasNumbers.setFocusable(false);
        hasNumbers.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        add(hasNumbers);
    }

    private void setButtonForCapitalChars() {
        gap = increaseGap(gap);
        hasCapitalLetters.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH + 50, HEIGHT);
        hasCapitalLetters.setFocusable(false);
        hasCapitalLetters.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        add((hasCapitalLetters));
    }

    private void setPhraseForLengthOfPassword(){
        gap = increaseGap(gap);
        JLabel lengthDescription = new JLabel("Укажите длину пароля:");
        lengthDescription.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        lengthDescription.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(lengthDescription);
    }

    private void setNumberFromSliderOnScreen(){
        lengthOfPassword.addChangeListener(this);
        numberFromSlider.setText(lengthOfPassword.getValue() + "");
        numberFromSlider.setBounds(WIDTH - 30, Y_PLACEMENT + gap, WIDTH, HEIGHT);
        numberFromSlider.setFont(new Font("Times New Roman", Font.BOLD, 20));
        numberFromSlider.setVisible(true);
        add(numberFromSlider);
    }

    private void setSlider(){
        gap = increaseGap(gap);
        lengthOfPassword.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT + 20);
        lengthOfPassword.setVisible(true);
        lengthOfPassword.setMajorTickSpacing(5);
        lengthOfPassword.setPaintLabels(true);
        lengthOfPassword.setFont(new Font("MV Boli", Font.PLAIN, 12));
        add(lengthOfPassword);
    }

    private void setButtonForPasswordGeneration(){
        gap = increaseGap(gap);
        gap = increaseGap(gap);
        JButton createPassword = new JButton("Сгенерировать пароль");
        createPassword.setVisible(true);
        createPassword.setBounds(X_PLACEMENT, Y_PLACEMENT + gap, WIDTH, HEIGHT + 10);
        createPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        //createPassword.setFocusable(false);
        add(createPassword);
        createPassword.addActionListener(e -> new PasswordGenerator(
                hasNumbers.isSelected(),
                hasCapitalLetters.isSelected(),
                Integer.parseInt(numberFromSlider.getText())
        ));
    }

}
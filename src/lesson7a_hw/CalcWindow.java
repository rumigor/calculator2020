package lesson7a_hw;

import javax.swing.*;
import java.awt.*;

public class CalcWindow extends JFrame {

    static double result = 0;
    static int index = 0;
    public CalcWindow() {
        setBounds(800, 600, 800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Калькулятор - 2020");
        Font font = new Font("Arial", NORMAL, 20);

//        JTextField textField = new JTextField();
//        add(textField, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        setLayout(null);
        textArea.setBounds(0,0, 800,100);
        textArea.setFont(font);
        add(textArea);

        JButton buttonPercent = new JButton("%");
        buttonPercent.setBounds(0,500, 200,100);
        add(buttonPercent);
        JButton buttonC = new JButton("Очистить поле");
        buttonC.setBounds(0,600, 800,100);
        add(buttonC);
        buttonC.setFont(font);
        JButton buttonSquare = new JButton("x^2");
        buttonSquare.setBounds(200,500, 200,100);
        add(buttonSquare);
        JButton buttonSQRT = new JButton("√x");
        buttonSQRT.setBounds(400,500, 200,100);
        add(buttonSQRT);
        JButton buttonDivision = new JButton("/");
        buttonDivision.setBounds(600,100, 200,100);
        add(buttonDivision);
        JButton buttonMultiply = new JButton("*");
        buttonMultiply.setBounds(600,200, 200,100);
        add(buttonMultiply);
        JButton buttonPlus = new JButton("+");
        buttonPlus.setBounds(600,300, 200,100);
        add(buttonPlus);
        JButton buttonMinus = new JButton("-");
        buttonMinus.setBounds(600,400, 200,100);
        add(buttonMinus);
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");
        JButton buttonChangeSign = new JButton("+/-");
        buttonChangeSign.setBounds(400,400, 200,100);
        add(buttonChangeSign);
        JButton buttonComma = new JButton(".");
        JButton buttonCalculation = new JButton("=");
        buttonCalculation.setBounds(600,500, 200,100);
        add(buttonCalculation);
        JButton [] numbers = new JButton[] {button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonComma};
        JButton [] mathFunc = new JButton[] {buttonDivision, buttonMultiply, buttonPlus, buttonMinus, buttonPercent, buttonSQRT,
                buttonSquare};

        buttonC.addActionListener(e -> {
            textArea.setText("");
            result = 0;
        });



        int x = 0;
        int y = 100;
        for (int i = 0; i < numbers.length; i++) {
            if (x == 600) {
                x = 0;
                y += 100;
                numbers[i].setBounds(x,y,200,100);
                add(numbers[i]);
            }
            numbers[i].setBounds(x, y, 200, 100);
            numbers[i].setFont(font);
            add(numbers[i]);
            x += 200;
            int finalI = i;
            numbers[i].addActionListener(e -> {
                textArea.append(numbers[finalI].getText());
            });
        }

        for (int i = 0; i < mathFunc.length; i++) {
            mathFunc[i].setFont(font);
            int finalI = i;
            mathFunc[i].addActionListener(e -> {
                if ((!mathFunc[finalI].getText().equals("√x")) && (!mathFunc[finalI].getText().equals("x^2")))
                { textArea.append(" " + mathFunc[finalI].getText() + " ");}
            });
        }

        buttonChangeSign.addActionListener(e -> {
            textArea.append(" * -1 ");
        });

        buttonCalculation.addActionListener(e -> {
            if (textArea.getText().equals("")) result = 0;
            String s = textArea.getText();
            index = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '=') {
                    index = i + 2;
                    break;
                }
            }
            result = calculation(s);
            double number = 0;
            while (index < s.length()){
                String calc = "";
                for (int i = index; i < s.length(); i++) {
                    if (s.charAt(i) != ' ') {
                        calc += s.charAt(i);
                    } else {
                        index = i + 1;
                        break;
                    }
                    index = i;
                }

                number = calculation(s);
                if (calc.equals("*")) {result *= number;}
                else if (calc.equals("+")) {result += number;}
                else if (calc.equals("-")) {result -= number;}
                else if (calc.equals("/")) {
                    if (number != 0) {result /= number;}
                    else {
                        textArea.setText("Деление на ноль невозможно!");
                        break;
                    }
                }
            }
            if (!textArea.getText().equals("Деление на ноль невозможно!")) {textArea.append(" = " + result);}
        });

        buttonSQRT.addActionListener(e -> {
            if (textArea.getText().equals("")) result = 0;
            if (result == 0) {
                try {
                    result = Double.parseDouble(textArea.getText());
                } catch (Exception ex) {
                    result = 0;
                }
            }
            result = Math.sqrt(result);
            textArea.setText("" + result);
        });

        buttonSquare.addActionListener(e -> {
            if (textArea.getText().equals("")) result = 0;
            if (result == 0) {
                try {
                    result = Double.parseDouble(textArea.getText());
                } catch (Exception ex) {
                    result = 0;
                }
            }
            result = result*result;
            textArea.setText("" + result);
        });



        setVisible(true);



    }

    public double calculation (String s){
        String nText = "";
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) !=' ') {nText += s.charAt(i);}
            else {
                index = i+1;
                return Double.parseDouble(nText);
            }
            index = i+1;
        }
        return Double.parseDouble(nText);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private JPanel buttonPanel;
    private JPanel operatorPanel;
    private JPanel advancedPanel;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton[] advancedButtons;
    private JButton equalButton;
    private JButton clearButton;

    private double num1, num2, result;
    private String operator;

    public Calculator() {
        initializeFrame();
        initializeTextField();
        initializeButtons();
        initializePanels();
        addComponentsToFrame();
        addActionListeners();

        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());
    }

    private void initializeTextField() {
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(textField, BorderLayout.NORTH);
    }

    private void initializeButtons() {
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (JButton button : operatorButtons) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
        }

        advancedButtons = new JButton[3];
        advancedButtons[0] = new JButton("√");
        advancedButtons[1] = new JButton("^");
        advancedButtons[2] = new JButton("log");
        for (JButton button : advancedButtons) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
        }

        equalButton = new JButton("=");
        equalButton.setFont(new Font("Arial", Font.BOLD, 20));

        clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void initializePanels() {
        buttonPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        operatorPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        advancedPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        for (int i = 1; i <= 9; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(new JLabel());
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(new JLabel());

        operatorPanel.add(operatorButtons[0]);
        operatorPanel.add(operatorButtons[1]);
        operatorPanel.add(operatorButtons[2]);
        operatorPanel.add(operatorButtons[3]);

        advancedPanel.add(advancedButtons[0]);
        advancedPanel.add(advancedButtons[1]);
        advancedPanel.add(advancedButtons[2]);

        JPanel buttonsContainer = new JPanel(new BorderLayout());
        buttonsContainer.add(buttonPanel, BorderLayout.CENTER);
        buttonsContainer.add(operatorPanel, BorderLayout.EAST);
        buttonsContainer.add(advancedPanel, BorderLayout.WEST);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.add(clearButton);
        bottomPanel.add(equalButton);

        frame.add(buttonsContainer, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addComponentsToFrame() {
        frame.add(textField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(operatorPanel, BorderLayout.EAST);
        frame.add(advancedPanel, BorderLayout.WEST);
        frame.add(clearButton, BorderLayout.SOUTH);
        frame.add(equalButton, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        for (int i = 0; i < 10; i++) {
            int number = i;
            numberButtons[i].addActionListener(e -> {
                textField.setText(textField.getText() + number);
            });
        }

        operatorButtons[0].addActionListener(e -> {
            num1 = Double.parseDouble(textField.getText());
            operator = "+";
            textField.setText("");
        });

        operatorButtons[1].addActionListener(e -> {
            num1 = Double.parseDouble(textField.getText());
            operator = "-";
            textField.setText("");
        });

        operatorButtons[2].addActionListener(e -> {
            num1 = Double.parseDouble(textField.getText());
            operator = "*";
            textField.setText("");
        });

        operatorButtons[3].addActionListener(e -> {
            num1 = Double.parseDouble(textField.getText());
            operator = "/";
            textField.setText("");
        });

        advancedButtons[0].addActionListener(e -> {
            num1 = Double.parseDouble(textField.getText());
            result = Math.sqrt(num1);
            textField.setText(String.valueOf(result));
        });

        advancedButtons[1].addActionListener(e -> {
            num1 = Double.parseDouble(textField.getText());
            operator = "^";
            textField.setText("");
        });

        advancedButtons[2].addActionListener(e -> {
            num1 = Double.parseDouble(textField.getText());
            result = Math.log(num1);
            textField.setText(String.valueOf(result));
        });

        equalButton.addActionListener(e -> {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                case "^":
                    result = Math.pow(num1, num2);
                    break;
            }
            textField.setText(String.valueOf(result));
        });

        clearButton.addActionListener(e -> {
            textField.setText("");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}

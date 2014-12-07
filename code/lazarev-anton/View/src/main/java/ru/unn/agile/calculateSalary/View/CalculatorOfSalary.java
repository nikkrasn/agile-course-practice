package ru.unn.agile.calculateSalary.View;

import javax.swing.*;

public final class CalculatorOfSalary {
    private JPanel mainPanel;
    private JTextField txtSalary;
    private JTextField txtWorkedHours;
    private JTextField txtCountYear;
    private JTextField txtVacationLength;
    private JTextField txtVacationYear;
    private JTextField txtResult;
    private JButton calculateButton;
    private JLabel lbStatus;
    private JTextField txtVacationMonth;
    private JTextField txtCountMonth;
    private JTextField txtStartDayVacation;

    private CalculatorOfSalary() { }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("CalculatorOfSalary");
        frame.setContentPane(new CalculatorOfSalary().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

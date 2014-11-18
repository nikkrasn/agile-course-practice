package ru.unn.agile.TemperatureConverter.view;

import javax.swing.*;

public final class Converter {

    private JPanel mainJPanel;
    private JComboBox cbScale;
    private JTextField txtValue;
    private JTextField txtResult;
    private JButton btnConvert;
    private JTextField txtErrorMessage;

    private Converter() { }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter().mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

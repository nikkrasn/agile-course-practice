package ru.unn.agile.ConverterWeight.view;

import javax.swing.*;

public final class Converter {
    private JPanel mainJPanel;
    private JComboBox cbSourceUnit;
    private JComboBox cbEndUnit;
    private JTextField txtValues;
    private JTextField txtResult;
    private JButton btnConvert;
    private JTextField txtErrorWindow;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter().mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Converter() { }
}

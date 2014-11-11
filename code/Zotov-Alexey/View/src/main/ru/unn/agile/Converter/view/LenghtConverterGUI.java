package ru.unn.agile.Converter.view;

import javax.swing.*;

/**
 * Created by Алексей on 11.11.2014.
 */
public class LenghtConverterGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LenghtConverterGUI");
        frame.setContentPane(new LenghtConverterGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
}

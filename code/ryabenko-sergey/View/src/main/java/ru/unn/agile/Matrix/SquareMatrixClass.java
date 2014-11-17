package ru.unn.agile.Matrix;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class SquareMatrixClass {

    private JPanel mainPanel;
    private ViewModel viewModel;
    private JButton buttonCalculate;
    private JLabel labelStatus;
    private JTextField textStatus;
    private JTextArea textArray;
    private JLabel labelResult;
    private JTextField textResult;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("SquareMatrix");
        frame.setContentPane(new SquareMatrixClass(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private SquareMatrixClass() { }

    private SquareMatrixClass(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        buttonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                SquareMatrixClass.this.viewModel.calculate();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                SquareMatrixClass.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        textArray.addKeyListener(keyListener);
    }

    private void bind() {
        viewModel.setTextInput(textArray.getText());
    }

    private void backBind() {
        buttonCalculate.setEnabled(viewModel.isCalculateButtonEnabled());
        textResult.setText(viewModel.getResult());
        textStatus.setText(viewModel.getStatus());
    }

}

package ru.unn.agile.Matrix;

import javax.swing.*;
import java.awt.event.*;

public final class SquareMatrixClass {

    private JPanel mainPanel;
    private final ViewModel viewModel;
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

    private SquareMatrixClass(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        ActionListener onButtonClick = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                viewModel.calculate();
                backBind();
            }
        };

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bind();
                backBind();
            }
        };

        textArray.addKeyListener(keyListener);
        textArray.addFocusListener(focusLostListener);
        buttonCalculate.addActionListener(onButtonClick);
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

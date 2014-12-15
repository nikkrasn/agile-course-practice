package ru.unn.agile.Matrix;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public final class SquareMatrixClass {

    private JPanel mainPanel;
    private final ViewModel viewModel;
    private JButton buttonCalculate;
    private JLabel labelStatus;
    private JTextField textStatus;
    private JTextArea textArray;
    private JLabel labelResult;
    private JTextField textResult;
    private JList<String> listLogger;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("SquareMatrix");
        TxtLogger logger = new TxtLogger("./SquareMatrix.log");
        frame.setContentPane(new SquareMatrixClass(new ViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private SquareMatrixClass(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
        buttonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.calculate();
                backBind();
            }
        });

        textArray.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                bind();
                backBind();
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                bind();
                backBind();
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        });

        textArray.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(final FocusEvent e) {
                bind();
                backBind();
            }

            @Override
            public void focusLost(final FocusEvent e) {
                bind();
                viewModel.focusLost();
                backBind();
            }
        });

    }

    private void bind() {
        viewModel.setTextInput(textArray.getText());
    }

    private void backBind() {
        buttonCalculate.setEnabled(viewModel.isCalculateButtonEnabled());
        textResult.setText(viewModel.getResult());
        textStatus.setText(viewModel.getStatus());
        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        listLogger.setListData(items);
    }
}

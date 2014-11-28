package ru.unn.agile.ComplexNumber.view.legacy;

import ru.unn.agile.ComplexNumber.viewmodel.legacy.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class Calculator {

    private JPanel mainPanel;
    private JButton btnCalc;
    private ViewModel viewModel;

    private JTextField txtZ1Re;
    private JTextField txtZ1Im;
    private JTextField txtZ2Re;
    private JTextField txtZ2Im;
    private JComboBox<ViewModel.Operation> cbOperation;
    private JTextField txtResult;
    private JLabel lbStatus;

    private Calculator() { }

    private Calculator(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.calculate();
                backBind();
            }
        });

        cbOperation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                Calculator.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        txtZ1Re.addKeyListener(keyListener);
        txtZ1Im.addKeyListener(keyListener);
        txtZ2Re.addKeyListener(keyListener);
        txtZ2Im.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");

        frame.setContentPane(new Calculator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfOperations() {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        cbOperation.setModel(new JComboBox<>(operations).getModel());
    }

    private void bind() {
        viewModel.setRe1(txtZ1Re.getText());
        viewModel.setIm1(txtZ1Im.getText());
        viewModel.setRe2(txtZ2Re.getText());
        viewModel.setIm2(txtZ2Im.getText());

        viewModel.setOperation((ViewModel.Operation) cbOperation.getSelectedItem());
    }

    private void backBind() {
        btnCalc.setEnabled(viewModel.isCalculateButtonEnabled());

        txtResult.setText(viewModel.getResult());
        lbStatus.setText(viewModel.getStatus());
    }
}

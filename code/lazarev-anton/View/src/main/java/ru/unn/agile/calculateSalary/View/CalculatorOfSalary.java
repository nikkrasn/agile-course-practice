package ru.unn.agile.calculateSalary.View;

import ru.unn.agile.calculateSalary.ViewModel.ViewModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private ViewModel viewModel;

    private CalculatorOfSalary() { }

    private CalculatorOfSalary(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                CalculatorOfSalary.this.viewModel.calculate();
                backBind();
            }
        });

        KeyAdapter whenInCountType = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                CalculatorOfSalary.this.viewModel.checkCountFields();
                backBind();
            }
        };

        KeyAdapter whenInVacationType = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                CalculatorOfSalary.this.viewModel.checkVacationFields();
                backBind();
            }
        };

        txtSalary.addKeyListener(whenInCountType);
        txtWorkedHours.addKeyListener(whenInCountType);
        txtCountMonth.addKeyListener(whenInCountType);
        txtCountYear.addKeyListener(whenInCountType);
        txtVacationLength.addKeyListener(whenInVacationType);
        txtStartDayVacation.addKeyListener(whenInVacationType);
        txtVacationMonth.addKeyListener(whenInVacationType);
        txtVacationYear.addKeyListener(whenInVacationType);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("CalculatorOfSalary");
        frame.setContentPane(new CalculatorOfSalary(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setSalary(txtSalary.getText());
        viewModel.setWorkedHours(txtWorkedHours.getText());
        viewModel.setCountMonth(txtCountMonth.getText());
        viewModel.setCountYear(txtCountYear.getText());
        viewModel.setVacationLength(txtVacationLength.getText());
        viewModel.setStartVacationDay(txtStartDayVacation.getText());
        viewModel.setVacationMonth(txtVacationMonth.getText());
        viewModel.setVacationYear(txtVacationYear.getText());
    }

    private void backBind() {
        calculateButton.setEnabled(viewModel.getCalculateButtonEnable());
        txtResult.setText(viewModel.getResult());
        lbStatus.setText(viewModel.getStatus());
    }
}

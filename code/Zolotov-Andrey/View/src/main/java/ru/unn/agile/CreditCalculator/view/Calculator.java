package ru.unn.agile.CreditCalculator.view;

import ru.unn.agile.CreditCalculator.ViewModel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Calculator {

    private JPanel mainPanel;
    private JButton btnCalc;
    private ViewModel viewModel;

    private JTextField sum;
    private JTextField paymentPeriod;
    private JTextField interestRate;
    private JTextField startMonth;
    private JComboBox<ViewModel.TypePayment> typePayment;
    private JComboBox<ViewModel.Currency> currency;
    private JTextField allSum;
    private JLabel lbStatus;
    private JTextField startDateOfPayment;
    private JTextField finishDateOfPayment;
    private JTextField firstPayment;
    private JTextField overPayment;

    private Calculator() { }

    private Calculator(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfTypePayment();
        loadListOfCurrency();

        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.calculate();
                backBind();
            }
        });

        typePayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        currency.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");

        frame.setContentPane(new Calculator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfTypePayment() {
        ViewModel.TypePayment[] typePayments = ViewModel.TypePayment.values();
        typePayment.setModel(new JComboBox<>(typePayments).getModel());
    }

    private void loadListOfCurrency() {
        ViewModel.Currency[] currencies = ViewModel.Currency.values();
        currency.setModel(new JComboBox<>(currencies).getModel());
    }

    private void bind() {
        viewModel.setSum(sum.getText());
        viewModel.setPaymentPeriod(paymentPeriod.getText());
        viewModel.setInterestRate(interestRate.getText());
        viewModel.setStartMonth(startMonth.getText());

        viewModel.setTypePayment((ViewModel.TypePayment) typePayment.getSelectedItem());
        viewModel.setCurrency((ViewModel.Currency) currency.getSelectedItem());
    }

    private void backBind() {
        allSum.setText(viewModel.getAllSum());
        startDateOfPayment.setText(viewModel.getStartDateOfPayment());
        finishDateOfPayment.setText(viewModel.getFinishDateOfPayment());
        overPayment.setText(viewModel.getOverPayment());
        firstPayment.setText(viewModel.getFirstPayment());
        lbStatus.setText(viewModel.getStatus());
    }

}

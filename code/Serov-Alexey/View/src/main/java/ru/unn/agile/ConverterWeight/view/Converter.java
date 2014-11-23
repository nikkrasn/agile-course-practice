package ru.unn.agile.ConverterWeight.view;

import ru.unn.agile.ConverterWeight.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class Converter {
    private final ViewModel viewModel;
    private JPanel mainJPanel;
    private JTextField txtValues;
    private JTextField txtResult;
    private JButton btnConvert;
    private JTextField txtStatusWindow;
    private JComboBox<ViewModel.UnitWeight> cbSourceUnit;
    private JComboBox<ViewModel.UnitWeight> cbEndUnit;

    private Converter(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfUnitWeight();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                Converter.this.viewModel.convert();
                backBind();
            }
        });

        cbSourceUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        cbEndUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                Converter.this.viewModel.prepareForConvert();
                backBind();
            }
        };

        txtValues.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {

        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter(new ViewModel()).mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfUnitWeight() {
        ViewModel.UnitWeight[] unitWeights = ViewModel.UnitWeight.values();
        cbSourceUnit.setModel(new JComboBox<>(unitWeights).getModel());
        cbEndUnit.setModel(new JComboBox<>(unitWeights).getModel());
    }

    private void bind() {
        viewModel.setValue(txtValues.getText());
        viewModel.setValueUnit((ViewModel.UnitWeight) cbSourceUnit.getSelectedItem());
        viewModel.setResultUnit((ViewModel.UnitWeight) cbEndUnit.getSelectedItem());
    }

    private void backBind() {
        btnConvert.setEnabled(viewModel.isConvertButton());
        txtResult.setText(viewModel.getResult());
        txtStatusWindow.setText(viewModel.getStatus());
    }

   // private Converter() { }
}

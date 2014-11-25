package ru.unn.agile.Converter.view;

import ru.unn.agile.Converter.Model.AreaConverter.Measures;
import ru.unn.agile.Converter.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.*;

public final class SimpleAreaConverter {
    private JTextField tbValue;
    private JComboBox<Measures> cbFrom;
    private JComboBox<Measures> cbTo;
    private JTextField tbResult;

    private JButton btnConvert;
    private JPanel mainPanel;
    private ViewModel viewModel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Simple area converter");

        frame.setContentPane(new SimpleAreaConverter(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private  SimpleAreaConverter() { }

    private SimpleAreaConverter(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListAreaMeasures();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.convert();
                backBind();
            }
        });

        cbFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });
        cbTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.processKeyInTextField();
                backBind();
            }
        };
        tbValue.addKeyListener(keyListener);
    }

    private void loadListAreaMeasures() {
        Measures[] measures = Measures.values();
        cbFrom.setModel(new JComboBox<Measures>(measures).getModel());
        cbTo.setModel(new JComboBox<Measures>(measures).getModel());
    }

    private void bind() {
        viewModel.setValue(tbValue.getText());
        viewModel.setMeasureOfAreaFrom((Measures) cbFrom.getSelectedItem());
        viewModel.setMeasureOfAreaTo((Measures) cbTo.getSelectedItem());
    }

    private void backBind() {
        btnConvert.setEnabled(viewModel.isCalculateButtonEnabled());
        tbResult.setText(viewModel.getResult());
    }
}

package ru.unn.agile.Converter.view;

import ru.unn.agile.Converter.Model.AreaConverter.Measures;
import ru.unn.agile.Converter.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.*;

public final class SimpleAreaConverter {
    private JTextField tbValueA;
    private JComboBox<Measures> cbFromA;
    private JComboBox<Measures> cbToA;
    private JTextField tbResultA;
    private JButton btnConvertA;
    private JPanel mainPanelA;
    private ViewModel viewModelA;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Simple area converter");
        frame.setContentPane(new SimpleAreaConverter(new ViewModel()).mainPanelA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private  SimpleAreaConverter() { }

    private SimpleAreaConverter(final ViewModel viewModel) {
        this.viewModelA = viewModel;
        backBind();

        loadListAreaMeasures();

        btnConvertA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.convert();
                backBind();
            }
        });

        cbFromA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });
        cbToA.addActionListener(new ActionListener() {
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
        tbValueA.addKeyListener(keyListener);
    }

    private void loadListAreaMeasures() {
        Measures[] measures = Measures.values();
        cbFromA.setModel(new JComboBox<Measures>(measures).getModel());
        cbToA.setModel(new JComboBox<Measures>(measures).getModel());
    }

    private void bind() {
        viewModelA.setInput(tbValueA.getText());
        viewModelA.setMeasureOfAreaFrom((Measures) cbFromA.getSelectedItem());
        viewModelA.setMeasureOfAreaTo((Measures) cbToA.getSelectedItem());
    }

    private void backBind() {
        btnConvertA.setEnabled(viewModelA.isCalculateButtonEnabled());
        tbResultA.setText(viewModelA.getResult());
    }
}

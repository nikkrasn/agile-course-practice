package ru.unn.agile.ConverterWeight.view;

import ru.unn.agile.ConverterWeight.Infrastructure.LoggerTxt;
import ru.unn.agile.ConverterWeight.viewmodel.ViewModel;
import ru.unn.agile.ConverterWeight.Model.ConverterWeight.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public final class Converter {
    private ViewModel viewModel;
    private JPanel mainJPanel;
    private JTextField txtValues;
    private JTextField txtResult;
    private JButton btnConvert;
    private JTextField txtStatusWindow;
    private JComboBox<UnitWeight> cbSourceUnit;
    private JComboBox<UnitWeight> cbEndUnit;
    private JList<String> listLog;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("ConverterWeight");
        LoggerTxt logger = new LoggerTxt("./Converter.log");
        frame.setContentPane(new Converter(new ViewModel(logger)).mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    private Converter() { }

    private Converter(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfUnitWeight();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.convert();
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
                viewModel.prepareForConvert();
                backBind();
            }
        };

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bind();
                viewModel.editingParams();
                backBind();
            }
        };

        txtValues.addFocusListener(focusLostListener);
        txtValues.addKeyListener(keyListener);
    }

    private void bind() {
        viewModel.setValue(txtValues.getText());
        viewModel.setValueUnit((UnitWeight) cbSourceUnit.getSelectedItem());
        viewModel.setResultUnit((UnitWeight) cbEndUnit.getSelectedItem());
    }

    private void backBind() {
        btnConvert.setEnabled(viewModel.isConvertButton());
        txtResult.setText(viewModel.getResult());
        txtStatusWindow.setText(viewModel.getStatus());
        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        listLog.setListData(items);
       }

    private void loadListOfUnitWeight() {
        UnitWeight[] unitWeights = UnitWeight.values();
        cbSourceUnit.setModel(new JComboBox<>(unitWeights).getModel());
        cbEndUnit.setModel(new JComboBox<>(unitWeights).getModel());
    }
}

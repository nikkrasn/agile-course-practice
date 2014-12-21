package ru.unn.agile.TemperatureConverter.view;

import ru.unn.agile.TemperatureConverter.Model.Converter.Scale;
import ru.unn.agile.TemperatureConverter.infrastructure.TxtLogger;
import ru.unn.agile.TemperatureConverter.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public final class Converter {
    private JPanel mainJPanel;
    private JTextField txtValue;
    private JTextField txtResult;
    private JButton btnConvert;
    private JTextField txtStatus;
    private JComboBox<Scale> cbScale;
    private JList<String> logList;
    private ViewModel viewModel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Converter");
        TxtLogger logger = new TxtLogger("./Converter.log");
        frame.setContentPane(new Converter(new ViewModel(logger)).mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Converter() { }

    private Converter(final ViewModel viewModel) {
        this.viewModel = viewModel;
        loadListOfScales();
        backBind();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.convert();
                backBind();
            }
        });

        cbScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                bind();
                viewModel.parseInput();
                backBind();
            }
        };
        txtValue.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bind();
                viewModel.focusLost();
                backBind();
            }
        };
        txtValue.addFocusListener(focusLostListener);
    }

    private void loadListOfScales() {
        Scale[] scales = Scale.values();
        cbScale.setModel(new JComboBox<>(scales).getModel());
    }

    private void bind() {
        viewModel.setInputValue(txtValue.getText());
        viewModel.setScale((Scale) cbScale.getSelectedItem());
    }

    private void backBind() {
        cbScale.setSelectedItem(viewModel.getScale());
        btnConvert.setEnabled(viewModel.isConvertButtonEnabled());
        txtResult.setText(viewModel.getResult());
        txtStatus.setText(viewModel.getStatus());
        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        logList.setListData(items);
    }
}

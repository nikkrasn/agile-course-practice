package ru.unn.agile.Converter.view;

import ru.unn.agile.Converter.Model.LengthConverter.Measure;
import ru.unn.agile.Converter.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.*;

public final class LenghtConverterGUI {

    private JPanel mainPanel;
    private JComboBox<Measure> cbFrom;
    private JComboBox<Measure> cbTo;
    private JButton btnConvert;
    private JTextField txtInput;
    private JTextField txtResult;
    private ViewModel viewModel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("");
        frame.setContentPane(new LenghtConverterGUI(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private LenghtConverterGUI() { }

    private LenghtConverterGUI(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfMeasures();

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
                viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        txtInput.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bind();
                backBind();
            }
        };
        txtInput.addFocusListener(focusLostListener);
    }

    private void backBind() {
        btnConvert.setEnabled(viewModel.isConvertButtonEnabled());
        txtResult.setText(viewModel.getResult());
    }

    private void loadListOfMeasures() {
        Measure[] operations = Measure.values();
        cbFrom.setModel(new JComboBox<Measure>(operations).getModel());
        cbTo.setModel(new JComboBox<Measure>(operations).getModel());
    }

    private void bind() {
        viewModel.setInputValue(txtInput.getText());
        viewModel.setInputMeasure((Measure) cbFrom.getSelectedItem());
        viewModel.setOutputMeasure((Measure) cbTo.getSelectedItem());
    }
}

package ru.unn.agile.LengthConverter.view;

import ru.unn.agile.LengthConverter.Model.LengthConverter.Measure;
import ru.unn.agile.LengthConverter.infrastructure.TxtLogger;
import ru.unn.agile.LengthConverter.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public final class LenghtConverterGUI {

    private JPanel mainPanel;
    private JComboBox<Measure> cbFromMeasure;
    private JComboBox<Measure> cbToMeasure;
    private JButton btnConvert;
    private JTextField txtInput;
    private JTextField txtResult;
    private ViewModel viewModel;
    private JList<String> lstLog;

    public static void main(final String[] args) {
        TxtLogger logger = new TxtLogger("./Calculator.log");
        JFrame frame = new JFrame("");
        frame.setContentPane(new LenghtConverterGUI(new ViewModel(logger)).mainPanel);
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
            public void actionPerformed(final ActionEvent event) {
                bind();
                viewModel.convert();
                backBind();
            }
        });

        cbToMeasure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                bind();
                backBind();
            }
        });

        cbFromMeasure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
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
                viewModel.focusLost();
                backBind();
            }
        };
        txtInput.addFocusListener(focusLostListener);
    }

    private void backBind() {
        btnConvert.setEnabled(viewModel.isConvertButtonEnabled());
        txtResult.setText(viewModel.getResult());
        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        lstLog.setListData(items);
    }

    private void loadListOfMeasures() {
        Measure[] operations = Measure.values();
        cbFromMeasure.setModel(new JComboBox<Measure>(operations).getModel());
        cbToMeasure.setModel(new JComboBox<Measure>(operations).getModel());
    }

    private void bind() {
        viewModel.setInputValue(txtInput.getText());
        viewModel.setInputMeasure((Measure) cbFromMeasure.getSelectedItem());
        viewModel.setOutputMeasure((Measure) cbToMeasure.getSelectedItem());
    }
}

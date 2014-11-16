package ru.unn.agile.Converter.view;

import ru.unn.agile.Converter.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.*;
//import java.util.List;
//import java.util.List;

public final class LenghtConverterGUI {

    private JPanel mainPanel;
    private JComboBox<ViewModel.Measure> cbFrom;
    private JComboBox<ViewModel.Measure> cbTo;
    private JButton btnConvert;
    private JTextField txtInput;
    private JTextField txtResult;
    private ViewModel viewModel;

    private LenghtConverterGUI() { }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("");
        frame.setContentPane(new LenghtConverterGUI(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private LenghtConverterGUI(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                LenghtConverterGUI.this.viewModel.convert();
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
                LenghtConverterGUI.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        txtInput.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bind();
                //LenghtConverterGUI.this.viewModel.focusLost();
                backBind();
            }
        };
        txtInput.addFocusListener(focusLostListener);
    }

    private void backBind() {
        btnConvert.setEnabled(viewModel.isConvertButtonEnabled());

        txtResult.setText(viewModel.getResult());
        //lbStatus.setText(viewModel.getStatus());

        //List<String> log = viewModel.getLog();
        //String[] items = log.toArray(new String[log.size()]);
        //lstLog.setListData(items);
    }

    private void loadListOfOperations() {
        ViewModel.Measure[] operations = ViewModel.Measure.values();
        cbFrom.setModel(new JComboBox<ViewModel.Measure>(operations).getModel());
        cbTo.setModel(new JComboBox<ViewModel.Measure>(operations).getModel());
    }

    private void bind() {
        viewModel.setInputValue(txtInput.getText());
        viewModel.setInputMeasure((ViewModel.Measure) cbFrom.getSelectedItem());
        viewModel.setOutputMeasure((ViewModel.Measure) cbTo.getSelectedItem());
    }

}

package ru.unn.agile.AreaConverter.view;

import ru.unn.agile.AreaConverter.Infrastructure.TxtLoggerAreaConverter;
import ru.unn.agile.AreaConverter.Model.AreaConverter.Measures;
import ru.unn.agile.AreaConverter.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public final class SimpleAreaConverter {
    private JTextField tbValueA;
    private JComboBox<Measures> cbFromA;
    private JComboBox<Measures> cbToA;
    private JTextField tbResultA;
    private JButton btnConvertA;
    private JPanel mainPanelA;
    private JList<String> lstLogArea;
    private ViewModel viewModelA;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Simple area converter");
        TxtLoggerAreaConverter logger = new TxtLoggerAreaConverter("./Calculator.log");
        frame.setContentPane(new SimpleAreaConverter(new ViewModel(logger)).mainPanelA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private  SimpleAreaConverter() { }

    private SimpleAreaConverter(final ViewModel viewModel) {
        this.viewModelA = viewModel;
        backBindAreaConverter();

        loadListAreaMeasures();

        btnConvertA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bindAreaConverter();
                viewModelA.convert();
                backBindAreaConverter();
            }
        });

        cbFromA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bindAreaConverter();
                backBindAreaConverter();
            }
        });
        cbToA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bindAreaConverter();
                backBindAreaConverter();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bindAreaConverter();
                viewModelA.processKeyInTextField();
                backBindAreaConverter();
            }
        };
        tbValueA.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bindAreaConverter();
                viewModelA.focusLost();
                //Calculator.this.viewModel.focusLost();
                backBindAreaConverter();
            }
        };
        //txtZ1Re.addFocusListener(focusLostListener);
        tbValueA.addFocusListener(focusLostListener);
    }

    private void loadListAreaMeasures() {
        Measures[] measures = Measures.values();
        cbFromA.setModel(new JComboBox<Measures>(measures).getModel());
        cbToA.setModel(new JComboBox<Measures>(measures).getModel());
    }

    private void bindAreaConverter() {
        viewModelA.setInput(tbValueA.getText());
        viewModelA.setMeasureOfAreaFrom((Measures) cbFromA.getSelectedItem());
        viewModelA.setMeasureOfAreaTo((Measures) cbToA.getSelectedItem());
    }

    private void backBindAreaConverter() {
        btnConvertA.setEnabled(viewModelA.isCalculateButtonEnabled());
        tbResultA.setText(viewModelA.getResult());
        List<String> log = viewModelA.getLog();
        String[] items = log.toArray(new String[log.size()]);
        lstLogArea.setListData(items);
    }
}

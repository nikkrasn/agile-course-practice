package ru.unn.agile.Matrix;

import javax.swing.*;

public final class Main extends JFrame {

    private Main() {
        setTitle("SquareMatrix");
        setContentPane(new SquareMatrixClass(
                new ViewModel(new TxtLogger("./SquareMatrix.log"))).getMainPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(final String [] args) {
        new Main();
    }
}

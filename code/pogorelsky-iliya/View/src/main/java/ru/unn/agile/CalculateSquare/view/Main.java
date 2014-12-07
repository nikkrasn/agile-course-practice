package ru.unn.agile.CalculateSquare.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage mainStep) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
        mainStep.setTitle("Calculator");
        mainStep.setScene(new Scene(root));
        mainStep.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}

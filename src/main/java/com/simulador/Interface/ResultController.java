package com.simulador.Interface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/ResultView.fxml"));
        primaryStage.setTitle("Resultados y Estadisticas");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void goToMain(MouseEvent mouseEvent) throws IOException {

        SimulationGUI mainGUI = new SimulationGUI();
        mainGUI.start(new Stage());

        Node source = (Node)mouseEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

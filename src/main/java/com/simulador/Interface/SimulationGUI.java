package com.simulador.Interface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import javax.swing.*;

public class SimulationGUI extends Application {

    ConfigurationController configController = new ConfigurationController();
    RunningController runController = new RunningController();
    public Stage currentStage;

    @FXML
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/GUI.fxml"));
        currentStage = primaryStage;
        currentStage.setTitle("Simulaicon-DBMS");
        currentStage.setScene(new Scene(root));
        currentStage.setResizable(false);
        currentStage.show();
    }

    @FXML
    public void openConfigView(ActionEvent actionEvent) throws Exception {

        configController.start(new Stage());


    }

    public void runSimulation(ActionEvent actionEvent) throws Exception {

        runController.start(new Stage());
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void showInfo(MouseEvent mouseEvent) {

        JOptionPane.showMessageDialog(null, "Programa de simulacion de un Data Base Mangement System \n " +
                                        "Desarrollado por: \n" +
                                        "- Ignacio Vargas Ocampo \n -Alejandro Chacon \n -Joel Chaves", "Ayuda e Informacion", JOptionPane.INFORMATION_MESSAGE);
    }
}

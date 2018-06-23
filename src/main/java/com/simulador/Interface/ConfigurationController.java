package com.simulador.Interface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;

public class ConfigurationController extends Application {

    public RadioButton idModeL;
    public RadioButton idModeR;
    public Stage currentStage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/ConfigurationView.fxml"));
        primaryStage.setTitle("Panel de configuracion");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        currentStage = primaryStage;
    }

    public void saveData(ActionEvent actionEvent) {

        currentStage.close();
    }

    public void selectSlowMode(ActionEvent actionEvent) {

        if(idModeR.isSelected()){

            idModeR.setSelected(false);
        }
    }

    public void selectFastMode(ActionEvent actionEvent) {

        if(idModeL.isSelected()){

            idModeL.setSelected(false);
        }
    }
}

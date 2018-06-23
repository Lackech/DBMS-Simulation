package com.simulador.Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RunningController extends Application {

    public Label idLabelNext = new Label();
    public ImageView idNextRunning = new ImageView();
    ResultController result = new ResultController();
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/RunningView.fxml"));
        primaryStage.setTitle("Ejecutando Simulacion");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        idNextRunning.setDisable(true);
        idLabelNext.setDisable(true);
    }

    public void showNext_Label(MouseEvent mouseEvent) throws Exception {

        if(idLabelNext.isDisable()) {
            result.start(new Stage());
        }
    }

    public void showNextView(MouseEvent mouseEvent) throws Exception {

        if(idLabelNext.isDisable()) {
            result.start(new Stage());
        }
    }
}

package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StudentHubController extends AdminHubController{
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void home(ActionEvent event) throws IOException {
        //loading a scene

        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-hub.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Student Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void showGradesTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student/show-grades-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Student Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();

    }

}

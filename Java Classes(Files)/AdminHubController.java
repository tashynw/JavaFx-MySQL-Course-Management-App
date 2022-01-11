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


public class AdminHubController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void home(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-hub.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Admin Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();

    }

    public void createLecturerTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/create-lecturer-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Admin Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();

    }
    public void deleteLecturerTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/delete-lecturer-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Admin Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void deleteStudentTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/delete-student-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Admin Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void changeStudentPasswordTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/update-student-password-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Admin Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void changeLecturerPasswordTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/update-lecturer-password-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Admin Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void showStudentAccountsTab(ActionEvent event){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

                //complete login logic for admin
            PreparedStatement query;
            query = connection.prepareStatement("select username,idnumber from studentlogin;");
            ResultSet resultSet = query.executeQuery();
            String result="";
            String result1="";
            while (resultSet.next()) {
                for (int i=1;i<=2;i++) {
                    result+=resultSet.getString(i)+"     ";
                }
                result1+=result+"\n\n\n";
                result="";
            }

            connection.close();
            //loading a scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/show-student-accounts-tab.fxml"));
            root = loader.load();
            ShowStudentAccountsAdminHubController tabInstance = loader.getController();
            tabInstance.setText(result1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.setTitle("Admin Dashboard");
            stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
            stage.setResizable(true);
            stage.show();


        }
        catch (SQLException e){
            e.printStackTrace();
            Alert failedConnection = new Alert(Alert.AlertType.ERROR);
            failedConnection.setContentText("Could not connect to the database");
            failedConnection.show();
        }
        catch (Exception e){
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error connecting to the database");
            error.show();
        }


    }
    public void showLecturerAccountsTab(ActionEvent event){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

            //complete login logic for admin
            PreparedStatement query;
            query = connection.prepareStatement("select username,firstname,lastname from lecturerlogin;");
            ResultSet resultSet = query.executeQuery();
            String result="";
            String result1="";
            while (resultSet.next()) {
                for (int i=1;i<=3;i++) {
                    result+=resultSet.getString(i)+"     ";
                }
                result1+=result+"\n\n\n";
                result="";
            }

            connection.close();
            //loading a scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/show-lecturer-accounts-tab.fxml"));
            root = loader.load();
            ShowLecturerAccountsAdminHubController tabInstance = loader.getController();
            tabInstance.setText(result1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.setTitle("Admin Dashboard");
            stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
            stage.setResizable(true);
            stage.show();


        }
        catch (SQLException e){
            e.printStackTrace();
            Alert failedConnection = new Alert(Alert.AlertType.ERROR);
            failedConnection.setContentText("Could not connect to the database");
            failedConnection.show();
        }
        catch (Exception e){
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error connecting to the database");
            error.show();
        }


    }
    public void logout(ActionEvent event) throws IOException {

        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setTitle("Login");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }


}

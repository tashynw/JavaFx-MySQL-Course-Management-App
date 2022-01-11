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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LecturerHubController extends AdminHubController{
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void home(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer-hub.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Lecturer Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void addGrade(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/add-grade-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Lecturer Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void editGradesTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/edit-grades-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Lecturer Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void deleteGradesTab(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/delete-grades-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Lecturer Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void showStudents(ActionEvent event){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

            //complete login logic for admin
            PreparedStatement query;
            query = connection.prepareStatement("select firstname,lastname,idnumber from studentlogin;");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/show-students-tab.fxml"));
            root = loader.load();
            ShowStudentsLecturerHubController tabInstance = loader.getController();
            tabInstance.setText(result1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.setTitle("Lecturer Dashboard");
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
    public void showStudentGrades(ActionEvent event){
        /* COMPLETE*/
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

            //complete login logic for admin
            PreparedStatement query;
            query = connection.prepareStatement("select firstname,lastname,idnumber,grade from studentlogin where grade is not null;");
            ResultSet resultSet = query.executeQuery();
            String result="";
            String result1="";
            while (resultSet.next()) {
                for (int i=1;i<=4;i++) {
                    if(i==4) {
                        String[] grades = resultSet.getString(i).trim().split(",");
                        for (String singleGrade:grades){
                            result+=singleGrade+"%      ";
                        }
                    }
                    else {
                        result += resultSet.getString(i) + "     ";
                    }
                }
                result1+=result+"\n\n\n";
                result="";
            }

            connection.close();
            //loading a scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/show-student-grades-tab.fxml"));
            root = loader.load();
            ShowStudentGradesLecturerHubController tabInstance = loader.getController();
            tabInstance.setText(result1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.setTitle("Lecturer Dashboard");
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
    public void deleteStudent(ActionEvent event) throws IOException {
        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/delete-student-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Lecturer Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }
    public void showPassingStudents(ActionEvent event){
        /* COMPLETE*/
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

            //complete login logic for admin
            PreparedStatement query;
            query = connection.prepareStatement("select firstname,lastname,idnumber,grade from studentlogin where grade is not null;");
            ResultSet resultSet = query.executeQuery();
            String result="";
            String result1="";
            double sum=0;
            double average=0;
            while (resultSet.next()) {
                for (int i=1;i<=4;i++) {
                    if(i==4) {
                        if (!(resultSet.getString(i).trim().equals(""))) {
                            String[] grades = resultSet.getString(i).trim().split(",");
                            for (String singleGrade : grades) {
                                sum += Double.parseDouble(singleGrade);
                            }
                            average=sum/(double) grades.length;
                            sum=0;
                            if (average>=50){
                                average = (double) Math.round(average * 100) / 100;
                                result+=average+"%";
                            }
                            else{
                                result="";
                            }
                        }
                        else{
                            result="";
                            result1="";
                        }
                    }
                    else {
                        result += resultSet.getString(i) + "      ";
                    }
                }
                if(!(result.equals(""))) {
                    result1 += result + "\n\n\n";
                    result = "";
                }
            }

            connection.close();
            //loading a scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/show-passing-students-tab.fxml"));
            root = loader.load();
            ShowPassingStudentsLecturerHubController tabInstance = loader.getController();
            tabInstance.setString(result1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.setTitle("Lecturer Dashboard");
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
    public void showFailingStudents(ActionEvent event){
        /* COMPLETE*/
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

            //complete login logic for admin
            PreparedStatement query;
            query = connection.prepareStatement("select firstname,lastname,idnumber,grade from studentlogin where grade is not null;");
            ResultSet resultSet = query.executeQuery();
            String result="";
            String result1="";
            double sum=0;
            double average=0;
            while (resultSet.next()) {
                for (int i=1;i<=4;i++) {
                    if(i==4) {
                        if (!(resultSet.getString(i).trim().equals(""))) {
                            String[] grades = resultSet.getString(i).trim().split(",");
                            for (String singleGrade : grades) {
                                sum += Double.parseDouble(singleGrade);
                            }
                            average=sum/(double) grades.length;
                            sum=0;
                            if (average<50){
                                average = (double) Math.round(average * 100) / 100;
                                result+=average+"%";
                            }
                            else{
                                result="";
                            }
                        }
                        else{
                            result="";
                            result1="";
                        }
                    }
                    else {
                        result += resultSet.getString(i) + "      ";
                    }
                }
                if(!(result.equals(""))) {
                    result1 += result + "\n\n\n";
                    result = "";
                }
            }

            connection.close();
            //loading a scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/show-failing-students-tab.fxml"));
            root = loader.load();
            ShowFailingStudentsLecturerHubController tabInstance = loader.getController();
            tabInstance.setString(result1);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.setTitle("Lecturer Dashboard");
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
    public void queryGrades(ActionEvent event) throws IOException {
        //FIX

        //loading a scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer/query-grades-tab.fxml"));
        root = loader.load();
        //AdminHubController adminHub = loader.getController();
        //adminHub.displayUsername(result);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.setTitle("Lecturer Dashboard");
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setResizable(true);
        stage.show();
    }


}

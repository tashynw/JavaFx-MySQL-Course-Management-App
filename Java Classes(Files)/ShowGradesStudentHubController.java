package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;

public class ShowGradesStudentHubController extends StudentHubController{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label gradesOutput;
    @FXML
    private TextField studentID;

    public void showGradesButton(ActionEvent event){

        if (!(studentID.getText().trim().equals(""))) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

                //complete login logic for admin
                PreparedStatement query;
                query = connection.prepareStatement("select grade from studentlogin where idnumber=?;");
                query.setInt(1, Integer.parseInt(studentID.getText()));
                ResultSet resultSet = query.executeQuery();
                String result = "";
                double sum = 0;
                double average=0;
                while (resultSet.next()) {
                    if (!(resultSet.getString(1).trim().equals(""))) {
                        String[] grades = resultSet.getString(1).trim().split(",");
                        for (String singleGrade : grades) {
                            result += singleGrade + "%     ";
                            sum += Double.parseDouble(singleGrade);
                        }
                        result += "\n\n\nAverage: ";
                        average = sum / (double) grades.length;
                        average = (double) Math.round(average * 100) / 100;
                        result += average+"%";
                    } else {
                        result = "";
                    }
                }
                connection.close();
                gradesOutput.setText(result);
            } catch (SQLException e) {
                e.printStackTrace();
                Alert failedConnection = new Alert(Alert.AlertType.ERROR);
                failedConnection.setContentText("Could not connect to the database");
                failedConnection.show();
            } catch (Exception e) {
                e.printStackTrace();
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Error connecting to the database. Try entering a valid ID");
                error.show();
            }
        }else{
            Alert emptyParameter=new Alert(Alert.AlertType.ERROR);
            emptyParameter.setContentText("Enter your ID number!");
            emptyParameter.show();
        }
    }

}

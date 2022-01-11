package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;


public class DeleteStudentAdminHubController extends AdminHubController{
    @FXML
    private TextField usernameField;

    public void deleteStudent(ActionEvent event){
        try{
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
            if (usernameField.getText().trim().equals("")){
                //if parameter is blank
                Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                emptyParameter.setTitle("Empty Parameter Detected");
                emptyParameter.setContentText("Enter Student username");
                emptyParameter.show();
            }
            else{
                //perform deletion
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
                PreparedStatement query;
                PreparedStatement queryCheck;
                queryCheck = connection.prepareStatement("select username from studentlogin where username=?");
                queryCheck.setString(1,usernameField.getText());
                ResultSet resultSet = queryCheck.executeQuery();
                if (resultSet.next()) {
                    query = connection.prepareStatement("delete from studentlogin where username=?;");
                    query.setString(1, usernameField.getText());
                    query.executeUpdate();
                    connection.close();
                    usernameField.clear();
                    Alert completed = new Alert(Alert.AlertType.INFORMATION);
                    completed.setContentText("Student Account deleted successfully");
                    completed.show();
                }
                else {
                    Alert invalidUsername = new Alert(Alert.AlertType.ERROR);
                    invalidUsername.setContentText("The username is not present in the database");
                    invalidUsername.show();
                    connection.close();
                }

            }
        }
        catch (SQLException e){
            e.printStackTrace();
            usernameField.clear();
            Alert errorSignUp = new Alert(Alert.AlertType.ERROR);
            errorSignUp.setContentText("Error in deleting account. Select a different username");
            errorSignUp.show();
        }
        catch (Exception e){
            e.printStackTrace();
            Alert errorSignUp = new Alert(Alert.AlertType.ERROR);
            errorSignUp.setContentText("Error in deleting account. Enter valid fields");
            errorSignUp.show();
        }
    }

}

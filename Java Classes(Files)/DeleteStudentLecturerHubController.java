package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class DeleteStudentLecturerHubController extends LecturerHubController{
    @FXML
    private TextField idNumber;

    public void deleteStudentButton(ActionEvent event){
        try{
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
            if (idNumber.getText().trim().equals("")){
                //if parameter is blank
                Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                emptyParameter.setTitle("Empty Parameter Detected");
                emptyParameter.setContentText("Enter Student ID");
                emptyParameter.show();
            }
            else{
                //perform deletion
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
                PreparedStatement query;
                PreparedStatement queryCheck;
                queryCheck = connection.prepareStatement("select idnumber from studentlogin where idnumber=?");
                queryCheck.setInt(1,Integer.parseInt(idNumber.getText()));
                ResultSet resultSetCheck = queryCheck.executeQuery();
                if (resultSetCheck.next()) {
                    query = connection.prepareStatement("delete from studentlogin where idnumber=?;");
                    query.setInt(1, Integer.parseInt(idNumber.getText().trim()));
                    query.executeUpdate();
                    connection.close();
                    idNumber.clear();
                    Alert completed = new Alert(Alert.AlertType.INFORMATION);
                    completed.setContentText("Student Account deleted successfully");
                    completed.show();
                }
                else {
                    Alert invalidUsername = new Alert(Alert.AlertType.ERROR);
                    invalidUsername.setContentText("The id number is not present in the database");
                    invalidUsername.show();
                    connection.close();
                }

            }
        }
        catch (SQLException e){
            e.printStackTrace();
            idNumber.clear();
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

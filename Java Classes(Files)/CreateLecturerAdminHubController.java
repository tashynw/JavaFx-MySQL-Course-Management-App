package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateLecturerAdminHubController extends AdminHubController{
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    public void createLecturer(ActionEvent event){
        try{
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
            if (firstName.getText().trim().equals("") || lastName.getText().trim().equals("") || usernameField.getText().trim().equals("") || passwordField.getText().trim().equals("") || confirmPasswordField.getText().trim().equals("")){
                //if any parameter is blank
                Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                emptyParameter.setTitle("Empty Parameter Detected");
                emptyParameter.setContentText("Fill in every parameter");
                emptyParameter.show();
            }
            else{
                //ADD MORE VERIFICATION(SUCH AS REGEX) TO PARAMETERS! ETC...
                if (!(passwordField.getText().equals(confirmPasswordField.getText()))){
                    //if password is different from the confirm one
                    Alert passwordParameter = new Alert(Alert.AlertType.ERROR);
                    passwordParameter.setTitle("Password error");
                    passwordParameter.setContentText("Enter the same password twice");
                    passwordParameter.show();
                }
                else{
                    String nameRegex = "[A-Z]" + "[a-zA-Z\\-]{1,}"; //Has to start with a Capital letter. Can contain hyphens
                    String idNumberRegex = "[0-9]{1,9}"; //can hold 1-9 digits
                    String usernameRegex = "[a-zA-Z]" + "[a-zA-Z0-9\\._\\-]{4,15}"; //must start with an alphabet letter and the rest can include alphanumeric characters. The range of the username should be 5-15 long.
                    String passwordRegex = "[a-zA-Z]" + "[a-zA-Z0-9@$%&]{5,12}"; //must start with an alphabetic character, can include special characters(@$%&). Rest can be alphanumeric characters between range 6-14
                    //CHECKING IF PARAMETERS MEET REGEX
                    if ((firstName.getText().trim().matches(nameRegex)) && (lastName.getText().trim().matches(nameRegex)) &&  (usernameField.getText().trim().matches(usernameRegex)) && (passwordField.getText().trim().matches(passwordRegex))) {
                        //perform create of student account
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
                        PreparedStatement query;
                        query = connection.prepareStatement("insert into lecturerlogin values(?,?,?,?);");
                        query.setString(1, usernameField.getText());
                        query.setString(2, passwordField.getText());
                        query.setString(3, firstName.getText().trim());
                        query.setString(4, lastName.getText().trim());

                        query.executeUpdate();

                        connection.close();

                        firstName.clear();
                        lastName.clear();
                        usernameField.clear();
                        passwordField.clear();
                        confirmPasswordField.clear();

                        Alert completed = new Alert(Alert.AlertType.INFORMATION);
                        completed.setContentText("Lecturer Account created successfully");
                        completed.show();
                    }
                    else {
                        Alert regexError = new Alert(Alert.AlertType.ERROR);
                        regexError.setContentText("An invalid parameter has been detected.\nThe name fields have to start with a Capital letter. Can contain hyphens.\nThe ID number field should hold 1-9 digits\nThe username must start with an alphabet letter and the rest can include alphanumeric characters. The range of the username should be 5-15 long.\nThe password must start with an alphabetic character, can include special characters(@$%&). Rest can be alphanumeric characters between range 6-14\nTry again!");
                        regexError.show();
                    }

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            firstName.clear();
            lastName.clear();
            usernameField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            Alert errorSignUp = new Alert(Alert.AlertType.ERROR);
            errorSignUp.setContentText("Error in creating account. Select a different username or id");
            errorSignUp.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

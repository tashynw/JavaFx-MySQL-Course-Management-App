package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class UpdateLecturerPasswordAdminHubController extends AdminHubController{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;

    public void updateLecturerPassword(ActionEvent event){
        try{
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
            if (usernameField.getText().trim().equals("") || newPassword.getText().equals("")){
                //if any parameter is blank
                Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                emptyParameter.setTitle("Empty Parameter Detected");
                emptyParameter.setContentText("Fill every fields");
                emptyParameter.show();
            }
            else{
                //ADD MORE VERIFICATION(SUCH AS REGEX) TO PARAMETERS! ETC...
                if (!(newPassword.getText().equals(confirmNewPassword.getText()))){
                    //if password is different from the confirm one
                    Alert passwordParameter = new Alert(Alert.AlertType.ERROR);
                    passwordParameter.setTitle("Password error");
                    passwordParameter.setContentText("Enter the same password twice");
                    passwordParameter.show();
                }
                else{
                    String passwordRegex = "[a-zA-Z]" + "[a-zA-Z0-9@$%&]{5,12}"; //must start with an alphabetic character, can include special characters(@$%&). Rest can be alphanumeric characters between range 6-14
                    //CHECKING IF PARAMETERS MEET REGEX
                    if ((newPassword.getText().trim().matches(passwordRegex))) {
                        //perform edit
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
                        PreparedStatement query;
                        PreparedStatement queryCheck;
                        queryCheck = connection.prepareStatement("select username from lecturerlogin where username=?");
                        queryCheck.setString(1,usernameField.getText());
                        ResultSet resultSet = queryCheck.executeQuery();
                        if (resultSet.next()) {
                            query = connection.prepareStatement("update lecturerlogin set password=? where username=?");
                            query.setString(1, newPassword.getText());
                            query.setString(2, usernameField.getText());

                            query.executeUpdate();

                            connection.close();

                            usernameField.clear();
                            newPassword.clear();
                            confirmNewPassword.clear();

                            Alert completed = new Alert(Alert.AlertType.INFORMATION);
                            completed.setContentText("Password changed successfully");
                            completed.show();
                        }
                        else {
                            Alert invalidUsername = new Alert(Alert.AlertType.ERROR);
                            invalidUsername.setContentText("The username is not present in the database");
                            invalidUsername.show();
                            connection.close();
                        }
                    }
                    else {
                        Alert regexError = new Alert(Alert.AlertType.ERROR);
                        regexError.setContentText("An invalid parameter has been detected.\nThe password must start with an alphabetic character, can include special characters(@$%&). Rest can be alphanumeric characters between range 6-14\nTry again!");
                        regexError.show();
                    }

                }

            }
        }
        catch (SQLException e){
            e.printStackTrace();
            usernameField.clear();
            Alert errorSignUp = new Alert(Alert.AlertType.ERROR);
            errorSignUp.setContentText("Error in changing password. Select a different username or password");
            errorSignUp.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

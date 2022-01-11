package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Locale;

public class SignUpController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField idNumber;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPassword;

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
    }

    public void signUpStudent(ActionEvent event){
        try {
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
            if (firstName.getText().trim().equals("") || lastName.getText().trim().equals("") || idNumber.getText().trim().equals("") || usernameField.getText().trim().equals("") || passwordField.getText().trim().equals("") || confirmPassword.getText().trim().equals("")) {
                //if any parameter is blank
                Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                emptyParameter.setTitle("Empty Parameter Detected");
                emptyParameter.setContentText("Fill in every parameter");
                emptyParameter.show();
            } else {
                //ADD MORE VERIFICATION(SUCH AS REGEX) TO PARAMETERS! ETC...
                String nameRegex = "[A-Z]" + "[a-zA-Z\\-]{1,}"; //Has to start with a Capital letter. Can contain hyphens
                String idNumberRegex = "[0-9]{1,9}"; //can hold 1-9 digits
                String usernameRegex = "[a-zA-Z]" + "[a-zA-Z0-9\\._\\-]{4,15}"; //must start with an alphabet letter and the rest can include alphanumeric characters. The range of the username should be 5-15 long.
                String passwordRegex = "[a-zA-Z]" + "[a-zA-Z0-9@$%&]{5,12}"; //must start with an alphabetic character, can include special characters(@$%&). Rest can be alphanumeric characters between range 6-14
                //CHECKING IF PARAMETERS MEET REGEX
                if ((firstName.getText().trim().matches(nameRegex)) && (lastName.getText().trim().matches(nameRegex)) && (idNumber.getText().trim().matches(idNumberRegex)) && (usernameField.getText().trim().matches(usernameRegex)) && (passwordField.getText().trim().matches(passwordRegex))) {
                    if (!(passwordField.getText().equals(confirmPassword.getText()))) {
                        //if password is different from the confirmed one
                        Alert passwordParameter = new Alert(Alert.AlertType.ERROR);
                        passwordParameter.setTitle("Password error");
                        passwordParameter.setContentText("Enter the same password twice");
                        passwordParameter.show();
                    } else {
                        //perform create of student account
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
                        PreparedStatement query;
                        query = connection.prepareStatement("insert into studentlogin values(?,?,?,?,?,null);");
                        query.setString(1, usernameField.getText());
                        query.setString(2, passwordField.getText());
                        query.setString(3, firstName.getText().trim());
                        query.setString(4, lastName.getText().trim());
                        query.setInt(5, Integer.parseInt(idNumber.getText()));

                        query.executeUpdate();

                        connection.close();

                        firstName.clear();
                        lastName.clear();
                        idNumber.clear();
                        usernameField.clear();
                        passwordField.clear();
                        confirmPassword.clear();

                        Alert completed = new Alert(Alert.AlertType.INFORMATION);
                        completed.setContentText("Student Account created successfully");
                        completed.show();

                    }
                }
                else {
                    Alert regexError = new Alert(Alert.AlertType.ERROR);
                    regexError.setContentText("An invalid parameter has been detected.\nThe name fields have to start with a Capital letter. Can contain hyphens.\nThe ID number field should hold 1-9 digits\nThe username must start with an alphabet letter and the rest can include alphanumeric characters. The range of the username should be 5-15 long.\nThe password must start with an alphabetic character, can include special characters(@$%&). Rest can be alphanumeric characters between range 6-14\nTry again!");
                    regexError.show();
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            firstName.clear();
            lastName.clear();
            idNumber.clear();
            usernameField.clear();
            passwordField.clear();
            confirmPassword.clear();
            Alert errorSignUp = new Alert(Alert.AlertType.ERROR);
            errorSignUp.setContentText("Error in creating account. Select a different username or id");
            errorSignUp.show();
        }
        catch (Exception e){
            e.printStackTrace();
            Alert regexError = new Alert(Alert.AlertType.ERROR);
            regexError.setContentText("An invalid parameter has been detected.\nThe name fields have to start with a Capital letter. Can contain hyphens.\nThe ID number field should hold 1-9 digits\nThe username must start with an alphabet letter and the rest can include alphanumeric characters. The range of the username should be 5-15 long.\nThe password must start with an alphabetic character, can include special characters(@$%&). Rest can be alphanumeric characters between range 6-14\nTry again!");
            regexError.show();
        }
    }
}

package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameField;
    @FXML
    private RadioButton rButton1,rButton2,rButton3;
    @FXML
    private PasswordField passwordField;



    public void switchToSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sign-up.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
        stage.setTitle("Sign up");
        stage.setResizable(false);
        stage.show();
    }

    public void login(ActionEvent event){

        if (rButton1.isSelected()){
            //if admin is selected
            try{

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

                if (usernameField.getText().trim().equals("") || passwordField.getText().trim().equals("")){
                    //error message if any field is empty
                    Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                    emptyParameter.setContentText("Fill in both fields");
                    emptyParameter.setTitle("Empty field");
                    emptyParameter.show();
                }
                else{
                    //complete login logic for admin
                    PreparedStatement query;
                    query = connection.prepareStatement("select * from adminlogin where username= ? and password = ?;");
                    query.setString(1,usernameField.getText());
                    query.setString(2,passwordField.getText());
                    ResultSet resultSet = query.executeQuery();
                    String result="";
                    String result1="";
                    while (resultSet.next()) {
                        for (int i=1;i<=2;i++) {
                            if (i==2) {
                                result1 = resultSet.getString(i);
                            }
                            else{
                                result = resultSet.getString(i);
                            }
                        }
                    }


                    if(result.equals("") || result1.equals("")){
                        //means that login was failed
                        Alert failedLogin = new Alert(Alert.AlertType.ERROR);
                        failedLogin.setTitle("Login failed");
                        failedLogin.setContentText("Incorrect Parameters, try again");
                        failedLogin.show();
                    }
                    else{
                        //login successful

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
                        stage.setResizable(true);
                        stage.setTitle("Admin Dashboard");
                        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
                        stage.centerOnScreen();
                        stage.setMaximized(true);
                        stage.show();

                    }




                }

                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }
        else if(rButton2.isSelected()){
            //if user chooses lecturer option
            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");


                if (usernameField.getText().trim().equals("") || passwordField.getText().trim().equals("")){
                    //error message if any field is empty
                    Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                    emptyParameter.setContentText("Fill in both fields");
                    emptyParameter.setTitle("Empty field");
                    emptyParameter.show();
                }
                else{
                    //complete login logic for admin
                    PreparedStatement query;
                    query = connection.prepareStatement("select * from lecturerlogin where username= ? and password = ?;");
                    query.setString(1,usernameField.getText());
                    query.setString(2,passwordField.getText());
                    ResultSet resultSet = query.executeQuery();
                    String username="";
                    String password="";
                    String firstName="";
                    String lastName="";
                    while (resultSet.next()) {
                        for (int i=1;i<=4;i++) {
                            if (i==1) {
                                username = resultSet.getString(i);
                            }
                            else if (i==2){
                                password = resultSet.getString(i);
                            }
                            else if (i==3){
                                firstName = resultSet.getString(i);
                            }
                            else if (i==4){
                                lastName = resultSet.getString(i);
                            }
                        }
                    }


                    if(username.equals("") || password.equals("")){
                        //means that login was failed
                        Alert failedLogin = new Alert(Alert.AlertType.ERROR);
                        failedLogin.setTitle("Login failed");
                        failedLogin.setContentText("Incorrect Parameters, try again");
                        failedLogin.show();
                    }
                    else{
                        //login successful

                        //loading a scene
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer-hub.fxml"));
                        root = loader.load();
                        //LecturerHubController lecturerHub = loader.getController();
                        //lecturerHub.setLecturerInfo(firstName,lastName);
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
                        stage.setScene(scene);
                        stage.setResizable(true);
                        stage.setTitle("Lecturer Dashboard");
                        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
                        stage.centerOnScreen();
                        stage.setMaximized(true);
                        stage.show();
                    }




                }

                connection.close();

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (rButton3.isSelected()){
            //if user chooses student
            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");

                if (usernameField.getText().trim().equals("") || passwordField.getText().trim().equals("")){
                    //error message if any field is empty
                    Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
                    emptyParameter.setContentText("Fill in both fields");
                    emptyParameter.setTitle("Empty field");
                    emptyParameter.show();
                }
                else{
                    //complete login logic for admin
                    PreparedStatement query;
                    query = connection.prepareStatement("select * from studentlogin where username= ? and password = ?;");
                    query.setString(1,usernameField.getText());
                    query.setString(2,passwordField.getText());
                    ResultSet resultSet = query.executeQuery();
                    String username="";
                    String password="";
                    String firstName="";
                    String lastName="";
                    int idNumber = 0;
                    while (resultSet.next()) {
                        for (int i=1;i<=5;i++) {
                            if (i==1) {
                                username = resultSet.getString(i);
                            }
                            else if (i==2){
                                password = resultSet.getString(i);
                            }
                            else if (i==3){
                                firstName = resultSet.getString(i);
                            }
                            else if (i==4){
                                lastName = resultSet.getString(i);
                            }
                            else if (i==5){
                                idNumber = Integer.parseInt(resultSet.getString(i));
                            }
                        }
                    }


                    if(username.equals("") || password.equals("")){
                        //means that login was failed
                        Alert failedLogin = new Alert(Alert.AlertType.ERROR);
                        failedLogin.setTitle("Login failed");
                        failedLogin.setContentText("Incorrect Parameters, try again");
                        failedLogin.show();
                    }
                    else{
                        //login successful

                        //loading a scene
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-hub.fxml"));
                        root = loader.load();
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
                        stage.setScene(scene);
                        stage.setTitle("Student Dashboard");
                        stage.getIcons().add(new Image("C:\\Users\\Tashyn Wallace\\Downloads\\uwi-icon.png"));
                        stage.setResizable(true);
                        stage.centerOnScreen();
                        stage.setMaximized(true);
                        stage.show();


                    }

                }

                connection.close();

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            //if no radiobutton is chosen and user wants to login
            Alert noneChoice = new Alert(Alert.AlertType.ERROR);
            noneChoice.setContentText("Select a choice to login!");
            noneChoice.show();

            usernameField.clear();
            passwordField.clear();


        }



    }
}

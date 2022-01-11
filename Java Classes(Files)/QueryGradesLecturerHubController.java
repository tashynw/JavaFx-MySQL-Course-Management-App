package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class QueryGradesLecturerHubController extends LecturerHubController implements Initializable{
    @FXML
    private TextField averageChoice;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label studentContent;
    @FXML
    private ScrollPane scrollPane;

    private String[] choices={"Lower than","Greater than or equal to","Equal to"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(choices);
    }

    public void queryButton(ActionEvent event){
        /* COMPLETE*/
        scrollPane.setFitToWidth(true);
        if (!(averageChoice.getText().trim().equals("") || choiceBox.getSelectionModel().isEmpty())){
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
                if(choiceBox.getValue().equals("Lower than")){
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
                                    if (average<Float.parseFloat(averageChoice.getText().trim())){
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
                                result += resultSet.getString(i) + "     ";
                            }
                        }
                        if(!(result.equals(""))) {
                            result1 += result + "\n\n\n";
                            result = "";
                        }

                    }

                }
                else if (choiceBox.getValue().equals("Greater than or equal to")){
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
                                    if (average>=Float.parseFloat(averageChoice.getText().trim())){
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
                                result += resultSet.getString(i) + "     ";
                            }
                        }
                        if(!(result.equals(""))) {
                            result1 += result + "\n\n\n";
                            result = "";
                        }

                    }

                }
                else if (choiceBox.getValue().equals("Equal to")){
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
                                    if (average==Float.parseFloat(averageChoice.getText().trim())){
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
                                result += resultSet.getString(i) + "     ";
                            }
                        }
                        if(!(result.equals(""))) {
                            result1 += result + "\n\n\n";
                            result = "";
                        }

                    }

                }

                connection.close();
                //loading a scene
                studentContent.setText(result1);
                /*
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

                 */


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
        else{
            Alert emptyParameter = new Alert(Alert.AlertType.ERROR);
            emptyParameter.setContentText("Fill in every parameter");
            emptyParameter.show();
        }
    }
}

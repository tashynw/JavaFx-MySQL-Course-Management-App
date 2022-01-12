package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class AddGradeLecturerHubController extends LecturerHubController{
    @FXML
    private TextField studentID;
    @FXML
    private TextField gradeText;

    public void addGradeButton(ActionEvent event){
        try {
            //PERFORM MORE VALIDATION
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");
            PreparedStatement query;
            query = connection.prepareStatement("select grade from studentlogin where idnumber=?;");
            query.setInt(1, Integer.parseInt(studentID.getText()));
            ResultSet resultSet = query.executeQuery();
            String result = "";
            while (resultSet.next()) {
                result += resultSet.getString(1);
            }
            if (result.trim().equals("")) {
                //if empty
                Alert invalidUsername = new Alert(Alert.AlertType.ERROR);
                invalidUsername.setContentText("The id number is not present in the database");
                invalidUsername.show();
            } else {
                if(!(result.equals("null"))) {
                    String gradeRegex = "([0-9]|[1-9][0-9]|100)"; //between 0-100
                    //CHECKING IF PARAMETERS MEET REGEX
                    if ((gradeText.getText().trim().matches(gradeRegex))) {

                        String newGradeString = result+gradeText.getText().trim() + ",";
                        //perform edit
                        PreparedStatement editDb;
                        editDb = connection.prepareStatement("update studentlogin set grade =? where idnumber=?; ");
                        editDb.setString(1, newGradeString);
                        editDb.setInt(2, Integer.parseInt(studentID.getText().trim()));
                        editDb.executeUpdate();
                        Alert completeEdit = new Alert(Alert.AlertType.INFORMATION);
                        completeEdit.setContentText("Student grade added successfully");
                        completeEdit.show();

                        studentID.clear();
                        gradeText.clear();
                    } else {
                        Alert regexError = new Alert(Alert.AlertType.ERROR);
                        regexError.setContentText("An invalid parameter has been detected.\nThe grade should be between 0-100");
                        regexError.show();
                    }
                }
                else{
                    String gradeRegex = "([0-9]|[1-9][0-9]|100)"; //between 0-100
                    //CHECKING IF PARAMETERS MEET REGEX
                    if ((gradeText.getText().trim().matches(gradeRegex))) {

                        String newGradeString = gradeText.getText().trim() + ",";
                        //perform edit
                        PreparedStatement editDb;
                        editDb = connection.prepareStatement("update studentlogin set grade =? where idnumber=?; ");
                        editDb.setString(1, newGradeString);
                        editDb.setInt(2, Integer.parseInt(studentID.getText().trim()));
                        editDb.executeUpdate();
                        Alert completeEdit = new Alert(Alert.AlertType.INFORMATION);
                        completeEdit.setContentText("Student grade added successfully");
                        completeEdit.show();

                        studentID.clear();
                        gradeText.clear();
                    } else {
                        Alert regexError = new Alert(Alert.AlertType.ERROR);
                        regexError.setContentText("An invalid parameter has been detected.\nThe grade should be between 0-100");
                        regexError.show();
                    }


                }

            }

            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
            Alert noGrades = new Alert(Alert.AlertType.ERROR);
            noGrades.setContentText("Error connecting to the database");
            noGrades.show();
        }
        catch (Exception e){
            e.printStackTrace();
            Alert noGrades = new Alert(Alert.AlertType.ERROR);
            noGrades.setContentText("Error editing the database. Ensure the idnumber is correct");
            noGrades.show();
        }
    }
}

package com.example.coursemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class EditGradesLecturerHubController extends LecturerHubController{
    @FXML
    private TextField studentID;
    @FXML
    private TextField gradePosition;
    @FXML
    private TextField newGrade;

    public void editGradeButton(ActionEvent event){
        try {
            //PERFORM MORE VALIDATION
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemanagementdb", "root", "/*YOUR PASSWORD*/");


            PreparedStatement query;
            query = connection.prepareStatement("select grade from studentlogin where grade is not null and idnumber=?;");
            query.setInt(1,Integer.parseInt(studentID.getText()));
            ResultSet resultSet = query.executeQuery();
            String result = "";
            String result1 = "";
            while (resultSet.next()) {
                result += resultSet.getString(1);
            }
            if (result.trim().equals("")){
                //if empty
                Alert noGrades = new Alert(Alert.AlertType.ERROR);
                noGrades.setContentText("The student ID has no grades");
                noGrades.show();
            }
            else{
                String[] grades = result.trim().split(",");
                ArrayList<String> gradeList = new ArrayList<String>();
                Collections.addAll(gradeList,grades);
                if(gradePosition.getText().equals("") || newGrade.getText().equals("")){
                    //if no grade position or new grade entered
                    Alert noGrades = new Alert(Alert.AlertType.ERROR);
                    noGrades.setContentText("Fill in every parameter!");
                    noGrades.show();
                }
                else{
                    String gradeRegex="([0-9]|[1-9][0-9]|100)"; //between 0-100
                    //CHECKING IF PARAMETERS MEET REGEX
                    if ((newGrade.getText().trim().matches(gradeRegex))) {
                        //perform edit
                        gradeList.set(Integer.parseInt(gradePosition.getText().trim()) - 1, newGrade.getText());
                        String[] newGradeArray = gradeList.toArray(new String[gradeList.size()]);
                        String newGradeString = String.join(",", newGradeArray) + ",";
                        PreparedStatement editDb;
                        editDb = connection.prepareStatement("update studentlogin set grade =? where idnumber=?; ");
                        editDb.setString(1, newGradeString);
                        editDb.setInt(2, Integer.parseInt(studentID.getText().trim()));
                        editDb.executeUpdate();
                        Alert completeEdit = new Alert(Alert.AlertType.INFORMATION);
                        completeEdit.setContentText("Student grade edit successfully");
                        completeEdit.show();

                        studentID.clear();
                        gradePosition.clear();
                        newGrade.clear();

                    }
                    else {
                        Alert regexError = new Alert(Alert.AlertType.ERROR);
                        regexError.setContentText("An invalid parameter has been detected.\nThe grade should be between 0-100");
                        regexError.show();
                    }

                }

            }

            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            Alert noGrades = new Alert(Alert.AlertType.ERROR);
            noGrades.setContentText("Error connecting to the database");
            noGrades.show();
        }
        catch (Exception e){
            e.printStackTrace();
            Alert noGrades = new Alert(Alert.AlertType.ERROR);
            noGrades.setContentText("Error editing the database. Ensure the position number is valid!");
            noGrades.show();
        }
    }
}

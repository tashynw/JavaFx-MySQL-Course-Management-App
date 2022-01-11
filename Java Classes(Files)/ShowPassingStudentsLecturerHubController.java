package com.example.coursemanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class ShowPassingStudentsLecturerHubController extends LecturerHubController{
    @FXML
    private Label studentContent;
    @FXML
    private ScrollPane scrollPane;

    public void setString(String content){
        studentContent.setText(content);
        scrollPane.setFitToWidth(true);
    }
}

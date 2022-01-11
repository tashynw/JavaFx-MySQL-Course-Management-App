package com.example.coursemanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class ShowStudentsLecturerHubController extends LecturerHubController{
    @FXML
    private Label contentLabel;
    @FXML
    private ScrollPane scrollPane;

    public void setText(String content){
        contentLabel.setText(content);
        scrollPane.setFitToWidth(true);
    }
}

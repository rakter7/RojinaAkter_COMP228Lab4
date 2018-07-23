package exercise2;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class StudentForm2 extends Application {
    String[] courseArray;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Top left pane declaration
        GridPane main = new GridPane();
        main.setAlignment(Pos.TOP_LEFT);
        main.setHgap(5);
        main.setVgap(5);

        //Top left pane declaration
        GridPane paneOne = new GridPane();
        paneOne.setAlignment(Pos.TOP_LEFT);
        paneOne.setHgap(5);
        paneOne.setVgap(5);

        GridPane paneTwo = new GridPane();
        paneTwo.setAlignment(Pos.TOP_LEFT);
        paneTwo.setHgap(2);
        paneTwo.setVgap(5);

        GridPane paneThree = new GridPane();
        paneThree.setAlignment(Pos.TOP_LEFT);
        paneThree.setHgap(2);
        paneThree.setVgap(5);

        //Bottom pane declaration

        GridPane paneFour = new GridPane();
        paneFour.setAlignment(Pos.BOTTOM_CENTER);
        paneFour.setHgap(5);
        paneFour.setVgap(5);

        GridPane paneFive = new GridPane();
        paneFive.setAlignment(Pos.BOTTOM_CENTER);
        paneFive.setHgap(5);
        paneFive.setVgap(5);

        //Declaring label
        Label[] allLabel;
        String[] labelNames = {"Name:", "Address:", "Province:", "City:", "Postal Code:", "Phone Number:", "Email:"};
        allLabel = new Label[labelNames.length];

        //Declaring Text field
        TextField[] allText = new TextField[labelNames.length];
        for (int i = 0; i < labelNames.length; i++) {
            allLabel[i] = new Label(String.format("%-10s", labelNames[i]));
            allText[i] = new TextField();
            paneOne.add(allLabel[i], 0, i);
            paneOne.add(allText[i], 5, i);
        }
        //Declaring Text Area to display information
        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(60);
        textArea.setPrefRowCount(7);
        textArea.setEditable(false);

        //declaring radio button
        RadioButton radioComputer = new RadioButton("Computer Science");
        RadioButton radioBusiness = new RadioButton("Business");
        paneFive.add(radioComputer, 0, 0);
        paneFive.add(radioBusiness, 1, 0);

        //creating button
        Button display = new Button("Display");
        GridPane.setHalignment(display, HPos.CENTER);

        //Button group creating
        ToggleGroup toggleGroup = new ToggleGroup();
        radioComputer.setToggleGroup(toggleGroup);
        radioBusiness.setToggleGroup(toggleGroup);

        //combo box
        ComboBox<String> courseCombo = new ComboBox<String>();
        courseCombo.setVisibleRowCount(3);
        courseCombo.setPrefSize(300, 50);

        //list declaration
        ListView<String> courseList = new ListView<String>();
        courseList.setPrefSize(1, 100);
        List<String> courseChoice = new ArrayList<>();
        ObservableList<String> listModel = FXCollections.observableArrayList();
        ObservableList<String> computerScienceCourses = FXCollections.observableArrayList("Java, ", "C#, ", "Database, ", "Web Interface Design, ",
                "Software Methodologies, ");
        ObservableList<String> businessCourses = FXCollections.observableArrayList("Accounting, ", "Human Resources Management",
                "Organizational Behavior, ", "Introduction to Finance, ", "Managerial Accounting, ");

        //declaring check box
        CheckBox studentCheckBox = new CheckBox("Student Council");
        CheckBox volunteerCheckBox = new CheckBox("Volunteer");

       //pane elements
        paneOne.add(studentCheckBox, 6, 1);
        paneOne.add(volunteerCheckBox, 6, 4);

        paneTwo.add(paneFive, 7, 0);
        paneTwo.add(courseCombo, 7, 2);
        paneTwo.add(courseList, 7, 3);

        paneFour.add(display, 7, 6);
        paneFour.add(textArea, 7, 10);

        paneThree.add(paneOne, 0, 0);
        paneThree.add(paneTwo, 1, 0);

        main.add(paneThree, 0, 0);
        main.add(paneFour, 0, 1);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();

                if (selected.getText().equals("Computer")) {
                    courseCombo.getSelectionModel().clearSelection();
                    listModel.clear();
                    courseCombo.setItems(computerScienceCourses);
                } else if (selected.getText().equals("Business")) {
                    courseCombo.getSelectionModel().clearSelection();
                     listModel.clear();
                    courseCombo.setItems(businessCourses);
                }
            }
        });

        courseCombo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String courseSelected = courseCombo.getValue().toString();
                if (!courseChoice.contains(courseSelected)) {
                    courseChoice.add(courseSelected);
                    courseArray = new String[courseChoice.size()];
                    for (int i = 0; i < courseArray.length; i++)

                        courseArray[i] = courseChoice.get(i);
                    courseList.getItems().clear();
                    courseList.getItems().addAll(courseArray);
                }
            }
        });

        display.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String myCourses = "";
                String otherActivities = "";
                String program = "";

                if (studentCheckBox.isSelected() && volunteerCheckBox.isSelected()) {
                    otherActivities = studentCheckBox.getText() + "-" + volunteerCheckBox.getText();
                } else if (studentCheckBox.isSelected() && !volunteerCheckBox.isSelected()) {
                    otherActivities = studentCheckBox.getText();
                } else if (volunteerCheckBox.isSelected() && !studentCheckBox.isSelected()) {
                    otherActivities = volunteerCheckBox.getText();
                } else if (!studentCheckBox.isSelected() && !studentCheckBox.isSelected()) {
                    otherActivities = "No activities";
                }

                //radio button checking

                if (radioBusiness.isSelected()) {
                    program = radioBusiness.getText();
                } else if (radioComputer.isSelected()) {
                    program = radioComputer.getText();
                } else {
                    program = "";
                }

                for (int i = 0; i < courseArray.length; i++) {
                    myCourses += courseArray[i];
                }

                textArea.setText(String.format("%s, %s, %s, %s, %s, %s, %s, %n%s: %s, %n%s, %s, %n%s: %s", allText[0].getText(), allText[1].getText(),
                        allText[2].getText(), allText[3].getText(), allText[4].getText(), allText[5].getText(), allText[6].getText(),"Activities",
                        otherActivities,"Program Name:", program, "Courses:", myCourses));
            }
        });

        // creating scene
        Scene scene = new Scene(main, 900, 450);

        // stage
        primaryStage.setTitle("Student Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
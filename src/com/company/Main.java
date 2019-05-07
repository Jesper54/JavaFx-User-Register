package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    //Control Index
    Button btnAdd = new Button();
    Button btnEdit = new Button();
    Button btnDelete = new Button();

    TableView<Person> table = new TableView<Person>();
    ObservableList<Person> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        btnAdd.setText("Add New User");
        btnEdit.setText("Edit User");
        btnDelete.setText("Delete User");

        final Label label = new Label("Overview");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn insertionCol = new TableColumn("Insertion");
        insertionCol.setMinWidth(50);
        insertionCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("insert"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn emailCol = new TableColumn("E-mail");
        emailCol.setMinWidth(150);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

        TableColumn streetCol = new TableColumn("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("street"));

        TableColumn numberCol = new TableColumn("Number");
        numberCol.setMinWidth(50);
        numberCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("number"));

        TableColumn postalCol = new TableColumn("Postal Code");
        postalCol.setMinWidth(50);
        postalCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("postal"));

        TableColumn townCol = new TableColumn("Town");
        townCol.setMinWidth(100);
        townCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("town"));

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("date"));

        TableColumn phoneCol = new TableColumn("Phone Number");
        phoneCol.setMinWidth(100);
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phone"));


        table.setItems(data);
        table.getColumns().addAll(firstNameCol, insertionCol, lastNameCol, emailCol, streetCol, numberCol, postalCol, townCol, dateCol, phoneCol);


        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line= br.readLine()) != null ) {
                if (!line.equals("")){
                    String[] separated = line.split("\\,");
                    data.add(new Person(separated[0], separated[1], separated[2], separated[3], separated[4], separated[5], separated[6], separated[7], separated[8], separated[9]));
                }
            }
        }
        catch(Exception e){
            e.getMessage();
        }


        //EDIT USER
        btnEdit.setOnAction(e -> {

            Person selectIf = table.getSelectionModel().getSelectedItem();
            if (selectIf == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Something went wrong");
                alert.setContentText("Please select a table row!");
                alert.showAndWait();
            }
            else {

                try {
                    Person selectedItem = table.getSelectionModel().getSelectedItem();

                    File originalFile = new File("users.csv");
                    File copyFile = new File("editUsers.csv");

                    BufferedReader reader = new BufferedReader(new FileReader(originalFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(copyFile));

                    String lineToCopy =
                            selectedItem.getFirstName() + "," +
                                    selectedItem.getInsert() + "," +
                                    selectedItem.getLastName() + "," +
                                    selectedItem.getEmail() + "," +
                                    selectedItem.getStreet() + "," +
                                    selectedItem.getNumber() + "," +
                                    selectedItem.getPostal() + "," +
                                    selectedItem.getTown() + "," +
                                    selectedItem.getDate() + "," +
                                    selectedItem.getPhone();

                    writer.write(lineToCopy);
                    writer.close();
                    reader.close();
                } catch (Exception u) {
                    u.getMessage();
                }

                try {
                    Person selectedItem = table.getSelectionModel().getSelectedItem();
                    table.getItems().remove(selectedItem);

                    File inputFile = new File("users.csv");
                    File tempFile = new File("usersTemp.csv");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String lineToRemove = selectedItem.getFirstName() + "," +
                            selectedItem.getInsert() + "," +
                            selectedItem.getLastName() + "," +
                            selectedItem.getEmail() + "," +
                            selectedItem.getStreet() + "," +
                            selectedItem.getNumber() + "," +
                            selectedItem.getPostal() + "," +
                            selectedItem.getTown() + "," +
                            selectedItem.getDate() + "," +
                            selectedItem.getPhone();
                    String currentLine;

                    while ((currentLine = reader.readLine()) != null) {
                        String trimmedLine = currentLine.trim();
                        if (trimmedLine.equals(lineToRemove)) continue;
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    writer.close();
                    reader.close();
                    inputFile.delete();
                    tempFile.renameTo(inputFile);

                } catch (Exception i) {
                    i.getMessage();
                }

                EditScreen NewEditScreen = new EditScreen();
                NewEditScreen.start(stage);
            }
        });




        //DELETE USER
        btnDelete.setOnAction(e -> {

            Person selectIf = table.getSelectionModel().getSelectedItem();
            if (selectIf == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Something went wrong");
                alert.setContentText("Please select a table row!");
                alert.showAndWait();
            }
            else {

                try {
                    Person selectedItem = table.getSelectionModel().getSelectedItem();
                    table.getItems().remove(selectedItem);

                    File inputFile = new File("users.csv");
                    File tempFile = new File("usersTemp.csv");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String lineToRemove = selectedItem.getFirstName() + "," +
                            selectedItem.getInsert() + "," +
                            selectedItem.getLastName() + "," +
                            selectedItem.getEmail() + "," +
                            selectedItem.getStreet() + "," +
                            selectedItem.getNumber() + "," +
                            selectedItem.getPostal() + "," +
                            selectedItem.getTown() + "," +
                            selectedItem.getDate() + "," +
                            selectedItem.getPhone();
                    String currentLine;

                    while ((currentLine = reader.readLine()) != null) {
                        String trimmedLine = currentLine.trim();
                        if (trimmedLine.equals(lineToRemove)) continue;
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    writer.close();
                    reader.close();
                    inputFile.delete();
                    tempFile.renameTo(inputFile);

                } catch (Exception i) {
                    i.getMessage();
                }
            }
        });


        //ADD USER
        btnAdd.setOnAction(e -> {
            AddScreen AddScreen = new AddScreen();
            AddScreen.start(stage);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, table);
        vbox.getChildren().addAll(btnAdd, btnEdit, btnDelete);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        stage.setTitle("Users");
        Scene primaryScene = new Scene(vbox, 1025, 600);
        stage.setScene(primaryScene);
        stage.show();
    }
}
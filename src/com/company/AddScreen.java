package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddScreen extends Application {
    //Control AddUser
    TextField first_name = new TextField();
    TextField last_name = new TextField();
    TextField tussenvoegsel = new TextField();
    TextField adres = new TextField();
    TextField huisnummer = new TextField();
    TextField postcode = new TextField();
    TextField plaats = new TextField();
    TextField contactgegevens = new TextField();
    TextField email = new TextField();

    Label first_name_label = new Label();
    Label last_name_label = new Label();
    Label tussenvoegsel_label = new Label();
    Label adres_label = new Label();
    Label huisnummer_label = new Label();
    Label postcode_label = new Label();
    Label plaats_label = new Label();
    Label AddDate_Label = new Label();
    Label contactgegevens_label = new Label();
    Label email_label = new Label();

    DatePicker AddDate = new DatePicker();

    Button SubmitAdd = new Button();
    Button CancelAdd = new Button();

    // ------------------------------------------------------------------- //
    @Override
    public void start(Stage stage) {
        // SetText
        first_name_label.setText("First Name");
        last_name_label.setText("Last Name");
        tussenvoegsel_label.setText("Insertion");
        adres_label.setText("Adress");
        huisnummer_label.setText("House Number");
        postcode_label.setText("Postal Code");
        plaats_label.setText("Plaats");
        contactgegevens_label.setText("Phone Number");
        AddDate_Label.setText("Date of birth");
        SubmitAdd.setText("Submit");
        email_label.setText("Email Adress");
        CancelAdd.setText("Cancel");

        SubmitAdd.setOnAction(e -> {

            if(first_name.getText().equals("") || AddDate.getValue() == null || last_name.getText().equals("") || adres.getText().equals("") || huisnummer.getText().equals("") || postcode.getText().equals("") || plaats.getText().equals("") || email.getText().equals("") || contactgegevens.getText().equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Something went wrong");
                alert.setContentText("Some input fields are empty!");
                alert.showAndWait();
            }
            else {
                String first_nameText = first_name.getText();
                String last_nameText = last_name.getText();
                String tussenvoegselText = tussenvoegsel.getText();
                String adresText = adres.getText();
                String huisnummerText = huisnummer.getText();
                String postcodeText = postcode.getText();
                String plaatsText = plaats.getText();
                String emailText = email.getText();

                LocalDate date = AddDate.getValue();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                String strDate = dateFormat.format(date);

                String contactgegevensText = contactgegevens.getText();

                AddFunction NewPerson = new AddFunction();
                NewPerson.Add(first_nameText, last_nameText, tussenvoegselText, emailText, adresText, huisnummerText, postcodeText, plaatsText, strDate, contactgegevensText);

                Main homepage = new Main();
                homepage.start(stage);
            }
        });

        //Cancel button
        CancelAdd.setOnAction(e ->{
            Main homepage = new Main();
            homepage.start(stage);
        });

        // Add Elements
        VBox secondaryLayout = new VBox();
        secondaryLayout.getChildren().addAll(
                first_name_label, first_name,
                last_name_label, last_name,
                tussenvoegsel_label, tussenvoegsel,
                email_label, email,
                adres_label, adres,
                huisnummer_label, huisnummer,
                postcode_label, postcode,
                plaats_label, plaats,
                AddDate_Label, AddDate,
                contactgegevens_label, contactgegevens, SubmitAdd, CancelAdd);

        // Koppelen aan stage
        Scene secondScene = new Scene(secondaryLayout, 1025, 600);
        stage.setTitle("Add User");
        stage.setScene(secondScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
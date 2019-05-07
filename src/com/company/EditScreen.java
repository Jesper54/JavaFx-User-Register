package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditScreen extends Application {

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

    Button SubmitEdit = new Button();
    Button CancelEdit = new Button();

    @Override
    public void start(Stage stage) {
        //Set Text Label
        first_name_label.setText("First Name");
        last_name_label.setText("Last Name");
        tussenvoegsel_label.setText("Insertion");
        adres_label.setText("Adress");
        huisnummer_label.setText("House Number");
        postcode_label.setText("Postal Code");
        plaats_label.setText("Plaats");
        contactgegevens_label.setText("Phone Number");
        AddDate_Label.setText("Date of birth");
        SubmitEdit.setText("Submit");
        email_label.setText("Email Adress");
        CancelEdit.setText("Cancel");

        //LOAD
        File copyFile = new File("editUsers.csv");
        File originalFile = new File("users.csv");
            try (BufferedReader br = new BufferedReader(new FileReader(copyFile))) {
                String line;
                while ((line= br.readLine()) != null ) {
                    if (!line.equals("")){
                        String[] separated = line.split("\\,");
                        first_name.setText(separated[0]);
                        tussenvoegsel.setText(separated[1]);
                        last_name.setText(separated[2]);
                        email.setText(separated[3]);
                        adres.setText(separated[4]);
                        huisnummer.setText(separated[5]);
                        postcode.setText(separated[6]);
                        plaats.setText(separated[7]);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                        String date = separated[8];
                        LocalDate localDate = LocalDate.parse(date, formatter);
                        AddDate.setValue(localDate);

                        contactgegevens.setText(separated[9]);

                        String fullSentence;
                        fullSentence = separated[0] + "," + separated[1] + "," + separated[2] + "," + separated[3] + "," + separated[4] + "," + separated[5] + "," + separated[6] + "," + separated[7] + "," + separated[8] + "," + separated[9];
                        System.out.println(fullSentence);
                        line.replace(fullSentence, "");
                    }
                }
            }
            catch(Exception e){
                e.getMessage();
            }

        // Scene
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
                contactgegevens_label, contactgegevens,
                SubmitEdit, CancelEdit);


        // SUBMIT EDIT
        SubmitEdit.setOnAction(e -> {

            if(first_name.getText().equals("") || AddDate.getValue() == null || last_name.getText().equals("") || tussenvoegsel.getText().equals("") || adres.getText().equals("") || huisnummer.getText().equals("") || postcode.getText().equals("") || plaats.getText().equals("") || email.getText().equals("") || contactgegevens.getText().equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Something went wrong");
                alert.setContentText("Some input fields are empty!");
                alert.showAndWait();

                System.out.println("Some input fields are empty!");
            }
            else {
                String first_nameText = first_name.getText();
                String tussenvoegselText = tussenvoegsel.getText();
                String last_nameText = last_name.getText();
                String emailText = email.getText();
                String adresText = adres.getText();
                String huisnummerText = huisnummer.getText();
                String postcodeText = postcode.getText();
                String plaatsText = plaats.getText();

                LocalDate date = AddDate.getValue();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                String strDate = dateFormat.format(date);

                String contactgegevensText = contactgegevens.getText();
                AddFunction EditPerson = new AddFunction();
                EditPerson.Add(first_nameText, last_nameText, tussenvoegselText, emailText, adresText, huisnummerText, postcodeText, plaatsText, strDate, contactgegevensText);

                copyFile.delete();
                Main homepage = new Main();
                homepage.start(stage);
            }
        });


        //Cancel button
        CancelEdit.setOnAction(e ->{

            try (FileWriter writer = new FileWriter("users.csv",true)) {

                BufferedReader br = new BufferedReader(new FileReader(copyFile));
                String line = br.readLine();

                StringBuilder sb = new StringBuilder();
                sb.append(line);
                sb.append('\n');

                writer.write(sb.toString());

            } catch (IOException h) {
                System.out.println(h.getMessage());
            }

            /*String first_nameText = first_name.getText();
            String tussenvoegselText = tussenvoegsel.getText();
            String last_nameText = last_name.getText();
            String emailText = email.getText();
            String adresText = adres.getText();
            String huisnummerText = huisnummer.getText();
            String postcodeText = postcode.getText();
            String plaatsText = plaats.getText();

            LocalDate date = AddDate.getValue();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String strDate = dateFormat.format(date);

            String contactgegevensText = contactgegevens.getText();

            AddFunction EditPerson = new AddFunction();
            EditPerson.Add(first_nameText, last_nameText, tussenvoegselText, emailText, adresText, huisnummerText, postcodeText, plaatsText, strDate, contactgegevensText);*/


            File editUsers = new File("editUsers.csv");
            editUsers.delete();
            Main homepage = new Main();
            homepage.start(stage);
        });

        // Koppelen aan stage
        Scene secondScene = new Scene(secondaryLayout, 1025, 600);
        stage.setTitle("Edit User");
        stage.setScene(secondScene);
        stage.show();
    }
}
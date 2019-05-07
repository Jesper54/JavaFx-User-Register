package com.company;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    SimpleStringProperty firstName;
        SimpleStringProperty lastName;
        SimpleStringProperty insert;
        SimpleStringProperty email;
        SimpleStringProperty street;
        SimpleStringProperty number;
        SimpleStringProperty postal;
        SimpleStringProperty town;
        SimpleStringProperty date;
        SimpleStringProperty phone;

        Person(String fName, String insertion, String lName, String email, String sName, String sNumber, String pCode, String tName, String gDate, String phoneNumber) {
            this.firstName = new SimpleStringProperty(fName);
            this.insert = new SimpleStringProperty(insertion);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.street = new SimpleStringProperty(sName);
            this.number = new SimpleStringProperty(sNumber);
            this.postal = new SimpleStringProperty(pCode);
            this.town = new SimpleStringProperty(tName);
            this.date = new SimpleStringProperty(gDate);
            this.phone = new SimpleStringProperty(phoneNumber);
        }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getInsert() {
        return insert.get();
    }

    public SimpleStringProperty insertProperty() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert.set(insert);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getPostal() {
        return postal.get();
    }

    public SimpleStringProperty postalProperty() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal.set(postal);
    }

    public String getTown() {
        return town.get();
    }

    public SimpleStringProperty townProperty() {
        return town;
    }

    public void setTown(String town) {
        this.town.set(town);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
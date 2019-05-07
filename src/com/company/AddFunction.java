package com.company;

import java.io.*;

public class AddFunction extends AddScreen{
    public void Add(
            String first_nameText, String last_nameText, String tussenvoegselText, String emailText, String adresText, String huisnummerText, String postcodeText, String plaatsText, String strDate, String contactgegevensText
                    ) {

        try (FileWriter writer = new FileWriter("users.csv",true)) {

            StringBuilder sb = new StringBuilder();
            sb.append(first_nameText);
            sb.append(',');

            if (tussenvoegselText == "") {
            } else {
                sb.append("nvt");
                sb.append(',');
            }

            sb.append(last_nameText);
            sb.append(',');
            sb.append(emailText);
            sb.append(',');
            sb.append(adresText);
            sb.append(',');
            sb.append(huisnummerText);
            sb.append(',');
            sb.append(postcodeText);
            sb.append(',');
            sb.append(plaatsText);
            sb.append(',');
            sb.append(strDate);
            sb.append(',');
            sb.append(contactgegevensText);
            sb.append('\n');

            writer.write(sb.toString());

            System.out.println(sb.toString());
            System.out.println("User Added!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
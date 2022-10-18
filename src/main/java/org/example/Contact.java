package org.example;

public class Contact  {
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;

    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.fullName = firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String toString() {
        String stringContact = String.format("%-25s|%19s         |", fullName, phone);
        return stringContact;
    }



//System.out.printf("%-23s| %s   |%n",nameField, phoneNumberField);

}

package org.example;
import org.apache.commons.lang3.StringUtils;
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

// The toString Method overrides the java Object toString method and dictates how the object should be converted to a string
    public String toString() {
        String stringContact = "|" + StringUtils.center(fullName, 24) + "|" + StringUtils.center(phone, 23) + "|";
        return stringContact;
    }
// - The StringUtils id part of the Apache common langs package


//System.out.printf("%-23s| %s   |%n",nameField, phoneNumberField);

}

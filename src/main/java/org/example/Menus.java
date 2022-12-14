package org.example;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.function.Consumer;

public class Menus {

    // variables to make printing menus and messages more compact
    private static String byee = StringUtils.center("Thank you for using GREY Contact Manager!", 50);
    public static String breakPt = StringUtils.repeat("-", 50);

    // Method accepts sc, a prompt, and a method. Prints prompt as menu item and inserts method in switch statement.
    public static Consumer<Scanner> returnMenu(Scanner sc, String prompt, Consumer<Scanner> method) {
        System.out.println("\n" + breakPt + "\n" + prompt +
                "\n2. Return to main menu\n" +
                "3. Exit\n" + breakPt);
        int choice = getChoice(sc);
        switch (choice) {
            case 1:
                Consumer<Scanner> method1 = method;
                break;
            case 2:
                printMainMenu(sc);
                break;
            case 3:
                exit();
        }
        return method;
    }

    //Overloaded of above to set default menu and methods for switch - Only in use for 1. View Contacts
    public static void returnMenu(Scanner sc) {
        System.out.println("\n" + breakPt +
                "\n1. Return to main menu\n" +
                "2. Exit\n" + breakPt);
        int choice = getChoice(sc);
        switch (choice) {
            case 1:
                printMainMenu(sc);
                break;
            case 2:
                exit();
                break;
        }
    }

    /*  1. Prints a goodbye messages
        2. writes each String within the contactList array to a separate line in the contact.txt file
        3. executes a System.exit to close the program */
    public static void exit() {
        try {
            System.out.println(breakPt + "\n" + byee + "\n" + breakPt);
            Files.write(ContactManager.filepath, ContactManager.contactList);
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Prints the main menu on application startup, runs the mainMenu Method for control.
    public static void printMainMenu(Scanner sc) {
        System.out.println(
                breakPt + "\n" +
                        StringUtils.center("Welcome", 50) + "\n" +
                        breakPt + "\n" +
                        "1. View contacts\n" +
                        "2. Add a new contact\n" +
                        "3. Search a contact by name\n" +
                        "4. Delete an existing contact\n" +
                        "5. Exit\n" + breakPt +
                        "\n" +
                        StringUtils.center("Enter an option (1, 2, 3, 4, 5)", 50) + "\n" + breakPt +
                        "\n");
        mainMenu(sc);
    }


// Main menu switch statement for navigation through the application
    public static void mainMenu(Scanner sc) {
        int choice = getChoice(sc);
        switch (choice) {
            case 1:
                ContactManager.printList(sc);
                break;
            case 2:
                ContactManager.addContact(sc);
                break;
            case 3:
                ContactManager.searchContact(sc);
                break;
            case 4:
                ContactManager.deleteContact(sc);
                break;
            case 5:
                exit();
                break;
            default:
                printMainMenu(sc);
        }
    }

    public static int getChoice(Scanner sc) {
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
            return choice;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again!");
            return getChoice(sc);
        }
    }

} // Menu CLASS close


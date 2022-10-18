package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.lang.System.out;

// TODO: 10/15/22 formatting for all the strings centered etc.
public class ContactManager {
    public static String filename = "contacts.txt";
    public static Path filepath = Paths.get(filename);
    public static List<String> contactList = new ArrayList<>();

    // Take in new Contact object and add it to the ArrayList of all contacts as a STRING
    public static void addToContactList(Contact newContact) {
        if (contactList.contains(String.valueOf(newContact))) {
            out.println("Contact already exist");
        } else {
            contactList.add(String.valueOf(newContact));
        }
    }

    // TODO: 10/15/22 Verify contact does not already exist before adding new contact
    // Creates a new Contact object via user inputs in the terminal
    public static Consumer<Scanner> addContact(Scanner sc) {
        String firstName;
        String lastName;
        String number;
        out.println("\n" + Menus.breakPt + "\n                      ADD CONTACT\n" + Menus.breakPt +
                "\nEnter the first name:");
        firstName = sc.nextLine();
        out.println("Enter the last name");
        lastName = sc.nextLine();
        out.println("Enter the phone number");
        number = sc.nextLine();
        Contact newContact = new Contact(firstName, lastName, number);
        addToContactList(newContact);
        Menus.returnMenu(sc, "1. Add another contact", ContactManager::searchContact);
        return addContact(sc);

    }

    // deleteContact takes in a search value and returns a list of matching contacts, then allows user to select which contact to delete
    public static Consumer<Scanner> deleteContact(Scanner sc) {
        out.println("\n" + Menus.breakPt + "\n                    DELETE CONTACT\n" + Menus.breakPt);
        List<String> result = searchfunction(sc);
        int whichContact = 1;
        if (result.size() > 1) {
            out.println(Menus.breakPt + "\nSelect the contact you would like to delete:");
            whichContact = sc.nextInt();
            sc.nextLine();
        }
        int deleteIndex = contactList.indexOf(result.get(whichContact-1));
        out.println("\n" + Menus.breakPt + "\n" + contactList.remove(deleteIndex) + "has been deleted");
        Menus.returnMenu(sc, "1. Delete another contact", ContactManager::deleteContact);
        out.println(Menus.breakPt + "\n\nThat name does not match any contact in the GREY");
        Menus.returnMenu(sc, "1. Return to Delete Contact", ContactManager::deleteContact);
        return deleteContact(sc);
    }


    // Searches contactList ArrayList based off a user input String, prints an array list of any Contact that has values that match the input. ignores case.

    public static Consumer<Scanner> searchContact(Scanner sc) {
        out.println("\n" + Menus.breakPt + "\n                    SEARCH CONTACT\n" + Menus.breakPt);
        List<String> result = searchfunction(sc);
        out.println(Menus.breakPt + "\n");
        if (!result.isEmpty()) {
            Menus.returnMenu(sc, "1. Search another contact", ContactManager::searchContact);
        } else {
            out.println("No contact found");
        }
        Menus.returnMenu(sc, "1. Search another contact", ContactManager::searchContact);
        return searchContact(sc);
    }

    //Search functionality
    public static List<String> searchfunction(Scanner sc) {
        List<String> result;
        out.println("Enter the name of the contact");
        String contact = sc.nextLine();
        result = contactList.stream()
                .filter(x -> x.toLowerCase().contains(contact.toLowerCase())).collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            out.println((i + 1) + ". | " + result.get(i));
        }
        return result;
    }


    // Prints the contactList in alphabetical order based on first name values
    public static void printList(Scanner sc) {
        Collections.sort(contactList);
        contactList.forEach(out::println);
        Menus.returnMenu(sc);
    }


}// end ContactManager CLASS


package org.example;

import org.apache.commons.lang3.StringUtils;

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
    //    Variables for file name and path
    public static String filename = "contacts.txt";
    public static Path filepath = Paths.get(filename);

    //    Create new ArrayList for file to write to
    public static List<String> contactList = new ArrayList<>();

    // Prints the contactList in alphabetical order based on first name values

    public static void printList(Scanner sc) {
        String header = "|" + StringUtils.center("NAME", 31) + "|" + StringUtils.center("PHONE", 16) + "|";
        out.println(header + "\n" + Menus.breakPt);
        Collections.sort(contactList);
        contactList.forEach(out::println);
        Menus.returnMenu(sc);
    }


    // Creates a new Contact object via user inputs in the terminal
    public static Consumer<Scanner> addContact(Scanner sc) {
        String firstName;
        String lastName;
        String number;
        out.println("\n" + Menus.breakPt + "\n" + StringUtils.center("ADD A CONTACT", 50) + "\n" + Menus.breakPt +
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

    //Take in new Contact object and add it to the ArrayList of all contacts as a STRING
    public static void addToContactList(Contact newContact) {
        if (contactList.contains(String.valueOf(newContact))) {
            out.println("Contact already exist");
        } else {
            contactList.add(String.valueOf(newContact));
        }
    }

    //Search functionality, used in both search and delete
    public static List<String> searchfunction(Scanner sc) {
        List<String> result;
        out.println("Enter the name of the contact");
        String contact = sc.nextLine();
        result = contactList.stream()
                .filter(x -> x.toLowerCase().contains(contact.toLowerCase())).collect(Collectors.toList());
        return result;
    }

    // Searches contactList ArrayList based off a user input String, ignores case.

    public static Consumer<Scanner> searchContact(Scanner sc) {
        out.println("\n" + Menus.breakPt + "\n" + StringUtils.center("SEARCH CONTACT", 50) +"\n" + Menus.breakPt);
        List<String> result = searchfunction(sc);
        result.forEach(out::println);
        out.println(Menus.breakPt + "\n");
        if (!result.isEmpty()) {
            Menus.returnMenu(sc, "1. Search another contact", ContactManager::searchContact);
        } else {
            out.println("No contact found");
        }
        Menus.returnMenu(sc, "1. Search another contact", ContactManager::searchContact);
        return searchContact(sc);
    }

    // deleteContact takes in a search value and returns a list of matching contacts, then allows user to select which contact to delete
    public static Consumer<Scanner> deleteContact(Scanner sc) {
        out.println("\n" + Menus.breakPt + "\n" + StringUtils.center("DELETE CONTACT", 50)  + "\n" + Menus.breakPt);
        List<String> result = searchfunction(sc);
        for (int i = 0; i < result.size(); i++) {
            out.println((i + 1) + result.get(i));
        }
        int whichContact = 1;
        if (result.size() > 1) {
            out.println(Menus.breakPt + "\nSelect the contact you would like to delete:");
            whichContact = sc.nextInt();
            sc.nextLine();
        }
        int deleteIndex = contactList.indexOf(result.get(whichContact - 1));
        out.println("\n" + Menus.breakPt + "\n" +StringUtils.center("The following contact has been deleted", 50) + "\n" + Menus.breakPt + "\n" +  contactList.remove(deleteIndex)+ "\n");
        Menus.returnMenu(sc, "1. Delete another contact", ContactManager::deleteContact);
        out.println(Menus.breakPt + "\n\nThat name does not match any contact in the GREY");
        Menus.returnMenu(sc, "1. Return to Delete Contact", ContactManager::deleteContact);
        return deleteContact(sc);
    }










}// end ContactManager CLASS


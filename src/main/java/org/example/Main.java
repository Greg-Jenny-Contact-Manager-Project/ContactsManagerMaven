package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

// reads each line from the txt file and add to the ArrayList contactList
        ContactManager.contactList = Files.readAllLines(ContactManager.filepath);

// Opens a scanner to be passed through each Method
        Scanner sc = new Scanner(System.in);

// Prints the main menu - also calls the mainMenu Method which includes a switch stmt for control.
        Menus.printMainMenu(sc);

    } // main method
}
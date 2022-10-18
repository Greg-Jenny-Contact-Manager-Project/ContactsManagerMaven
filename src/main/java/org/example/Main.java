package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ContactManager.contactList = Files.readAllLines(ContactManager.filepath);

        Scanner sc = new Scanner(System.in);


        Menus.printMainMenu(sc);

    } // main method
}
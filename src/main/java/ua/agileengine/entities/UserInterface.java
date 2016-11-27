package ua.agileengine.entities;

import java.util.Scanner;

/**
 * The class represents an object which is able to communicate with the user.
 */
public class UserInterface  {

    private String userAnswer;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {
        System.out.println("---------------------------");
        System.out.println("| Image comparison system |");
        System.out.println("---------------------------");
    }
    
    public String askPathToFile() {
        System.out.println("Please enter the full path of the image:");
        userAnswer = scanner.nextLine();
        return userAnswer;
    }
}

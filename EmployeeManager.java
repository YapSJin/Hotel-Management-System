/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager {
    private final List<String> employees = new ArrayList<>();

    public EmployeeManager() {
        employees.add("Alice");
        employees.add("Bob");
    }

    public void showEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. List employees");
            System.out.println("2. Add employee");
            System.out.println("3. Remove employee");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) { scanner.next(); System.out.print("Enter number: "); }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> listEmployees();
                case 2 -> addEmployee(scanner);
                case 3 -> removeEmployee(scanner);
                case 4 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 4);
    }

    private void listEmployees() {
        if (employees.isEmpty()) { System.out.println("No employees."); return; }
        int i = 1;
        for (String name : employees) System.out.println(i++ + ". " + name);
    }

    private void addEmployee(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Name cannot be empty."); return; }
        employees.add(name);
        System.out.println("Added: " + name);
    }

    private void removeEmployee(Scanner scanner) {
        listEmployees();
        if (employees.isEmpty()) return;
        System.out.print("Enter index to remove: ");
        int idx = scanner.nextInt();
        if (idx < 1 || idx > employees.size()) { System.out.println("Invalid index."); return; }
        String removed = employees.remove(idx - 1);
        System.out.println("Removed: " + removed);
    }
}

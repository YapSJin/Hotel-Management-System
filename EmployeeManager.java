/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager {
    private final Scanner scanner = new Scanner(System.in);

    // Add some initial data
    public EmployeeManager() {
        if (Employee.getEmployeeList().isEmpty()) {
            new GeneralEmployee("E001", "Manager", 50, LocalTime.of(9, 0));
            new GeneralEmployee("E002", "Receptionist", 30, LocalTime.of(8, 0));
            new GeneralEmployee("E003", "Cleaner", 20, LocalTime.of(10, 0));
        }
    }

    public void showEmployeeMenu() {
        int choice;
        do {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. List employees");
            System.out.println("2. Add employee");
            System.out.println("3. Update schedule");
            System.out.println("4. Remove employee");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) { scanner.next(); }
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> listEmployees();
                case 2 -> addEmployee();
                case 3 -> updateSchedule();
                case 4 -> removeEmployee();
                case 5 -> System.out.println("Returning...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private void listEmployees() {
        ArrayList<Employee> list = Employee.getEmployeeList();
        if (list.isEmpty()) {
            System.out.println("No employees.");
            return;
        }
        for (Employee e : list) {
            System.out.println(e);
            System.out.println("Calculated Monthly Salary: RM " + e.calculateMonthlySalary());
        }
    }

    private void addEmployee() {
        System.out.print("Enter staff ID: ");
        String id = scanner.next();
        System.out.print("Enter role: ");
        String role = scanner.next();
        System.out.print("Enter hourly salary: ");
        double hourlyRate = scanner.nextDouble();
        System.out.print("Enter start hour (0-23): ");
        int hour = scanner.nextInt();

        new GeneralEmployee(id, role, hourlyRate, LocalTime.of(hour, 0));
        System.out.println("Employee added.");
    }

    private void updateSchedule() {
        System.out.print("Enter staff ID to update: ");
        String id = scanner.next();
        for (Employee e : Employee.getEmployeeList()) {
            if (e.getStaffID().equals(id)) {
                System.out.print("Enter new hour: ");
                int h = scanner.nextInt();
                System.out.print("Enter new minute: ");
                int m = scanner.nextInt();
                e.setSchedule(LocalTime.of(h, m));
                System.out.println("Schedule updated.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    private void removeEmployee() {
        System.out.print("Enter staff ID to remove: ");
        String id = scanner.next();
        Employee target = null;
        for (Employee e : Employee.getEmployeeList()) {
            if (e.getStaffID().equals(id)) {
                target = e;
                break;
            }
        }
        if (target != null) {
            Employee.getEmployeeList().remove(target);
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}

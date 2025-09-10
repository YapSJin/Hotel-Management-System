/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple file-backed employee manager.
 * Stores employee rows in Employee.txt with fields:
 * staffID,role,basicSalary,monthlySalary,schedule(HH:mm:ss)
 *
 * Note: Does NOT depend on Employee.java and leaves it untouched.
 */
public class EmployeeManager {

    private static final String FILE_NAME = "Employee.txt";

    // Simple record to avoid touching Employee.java
    private static class EmployeeRow {
        String staffID;
        String role;
        double basicSalary;
        double monthlySalary;
        String schedule; // as text HH:mm:ss

        EmployeeRow(String staffID, String role, double basicSalary, double monthlySalary, String schedule) {
            this.staffID = staffID;
            this.role = role;
            this.basicSalary = basicSalary;
            this.monthlySalary = monthlySalary;
            this.schedule = schedule;
        }

        String serialize() {
            // Very simple CSV; replace commas in fields with spaces
            String safeRole = role == null ? "" : role.replace(',', ' ');
            String safeSchedule = schedule == null ? "" : schedule.replace(',', ' ');
            return staffID + "," + safeRole + "," + basicSalary + "," + monthlySalary + "," + safeSchedule;
        }

        static EmployeeRow parse(String line) {
            String[] parts = line.split(",", -1);
            if (parts.length < 5) return null;

            String staffID = parts[0].trim();
            String role = parts[1].trim();

            double basicSalary = 0.0;
            double monthlySalary = 0.0;
            try { basicSalary = Double.parseDouble(parts[2].trim()); } catch (Exception ignored) {}
            try { monthlySalary = Double.parseDouble(parts[3].trim()); } catch (Exception ignored) {}

            String schedule = parts[4].trim();

            return new EmployeeRow(staffID, role, basicSalary, monthlySalary, schedule);
        }
    }

    private final List<EmployeeRow> rows = new ArrayList<>();

    public EmployeeManager() {
        loadFromFile();
    }

    public void showEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. List employees");
            System.out.println("2. Add employee");
            System.out.println("3. Modify employee");
            System.out.println("4. Remove employee");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) { scanner.next(); System.out.print("Enter number: "); }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    listEmployees();
                    break;
                case 2:
                    addEmployee(scanner);
                    break;
                case 3:
                    modifyEmployee(scanner);
                    break;
                case 4:
                    removeEmployee(scanner);
                    break;
                case 5:
                    System.out.println("Returning...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }

    private void listEmployees() {
        if (rows.isEmpty()) {
            System.out.println("No employees.");
            return;
        }
        System.out.println(String.format("%-12s | %-12s | %-12s | %-14s | %-8s",
                "StaffID", "Role", "BasicSalary", "MonthlySalary", "Schedule"));
        System.out.println("---------------------------------------------------------------------");
        for (EmployeeRow r : rows) {
            System.out.println(String.format("%-12s | %-12s | RM%-10.2f | RM%-12.2f | %-8s",
                    r.staffID, r.role, r.basicSalary, r.monthlySalary, r.schedule));
        }
    }

    private void addEmployee(Scanner scanner) {
        System.out.print("Staff ID: ");
        String id = scanner.nextLine().trim();
        if (findIndexById(id) != -1) {
            System.out.println("An employee with that Staff ID already exists.");
            return;
        }
        System.out.print("Role: ");
        String role = scanner.nextLine().trim();
        System.out.print("Basic salary (number): ");
        double basic = readDouble(scanner);
        System.out.print("Monthly salary (number): ");
        double monthly = readDouble(scanner);
        System.out.print("Schedule (HH:mm:ss): ");
        String schedule = scanner.nextLine().trim();

        rows.add(new EmployeeRow(id, role, basic, monthly, schedule));
        saveToFile();
        System.out.println("Added employee " + id + " and saved to " + FILE_NAME + ".");
    }

    private void modifyEmployee(Scanner scanner) {
        System.out.print("Enter Staff ID to modify: ");
        String id = scanner.nextLine().trim();
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("Employee not found.");
            return;
        }
        EmployeeRow r = rows.get(idx);

        System.out.println("Leave blank to keep current value.");
        System.out.println("Current Role: " + r.role);
        System.out.print("New Role: ");
        String role = scanner.nextLine().trim();
        if (!role.isEmpty()) r.role = role;

        System.out.println("Current Basic Salary: " + r.basicSalary);
        System.out.print("New Basic Salary: ");
        String bs = scanner.nextLine().trim();
        if (!bs.isEmpty()) {
            try { r.basicSalary = Double.parseDouble(bs); } catch (Exception ignored) {}
        }

        System.out.println("Current Monthly Salary: " + r.monthlySalary);
        System.out.print("New Monthly Salary: ");
        String ms = scanner.nextLine().trim();
        if (!ms.isEmpty()) {
            try { r.monthlySalary = Double.parseDouble(ms); } catch (Exception ignored) {}
        }

        System.out.println("Current Schedule: " + r.schedule);
        System.out.print("New Schedule (HH:mm:ss): ");
        String sc = scanner.nextLine().trim();
        if (!sc.isEmpty()) r.schedule = sc;

        saveToFile();
        System.out.println("Updated employee " + id + " and saved to " + FILE_NAME + ".");
    }

    private void removeEmployee(Scanner scanner) {
        System.out.print("Enter Staff ID to remove: ");
        String id = scanner.nextLine().trim();
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("Employee not found.");
            return;
        }
        rows.remove(idx);
        saveToFile();
        System.out.println("Removed employee " + id + " and saved to " + FILE_NAME + ".");
    }

    private int findIndexById(String id) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).staffID.equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    private double readDouble(Scanner scanner) {
        while (true) {
            if (scanner.hasNextDouble()) {
                double v = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                return v;
            } else {
                String s = scanner.nextLine();
                try { return Double.parseDouble(s.trim()); }
                catch (Exception e) { System.out.print("Enter a valid number: "); }
            }
        }
    }

    private void loadFromFile() {
        rows.clear();
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            return; // nothing to load yet
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                EmployeeRow r = EmployeeRow.parse(line);
                if (r != null) rows.add(r);
            }
        } catch (IOException e) {
            System.out.println("Failed to read " + FILE_NAME + ": " + e.getMessage());
        } finally {
            if (br != null) try { br.close(); } catch (IOException ignored) {}
        }
    }

    private void saveToFile() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(FILE_NAME));
            for (int i = 0; i < rows.size(); i++) {
                bw.write(rows.get(i).serialize());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write " + FILE_NAME + ": " + e.getMessage());
        } finally {
            if (bw != null) try { bw.close(); } catch (IOException ignored) {}
        }
    }
}


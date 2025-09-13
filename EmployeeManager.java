

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManager {

    private static Scanner scanner = new Scanner(System.in);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

    private static ArrayList<Employee> houseKeeping = new ArrayList<>();
    private static ArrayList<Employee> deskStaff = new ArrayList<>();
    private static ArrayList<Employee> manager = new ArrayList<>();
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static int staffType = 0;
    private static boolean validType = false;
    private static String staffID;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private static LocalTime schedule;
    private static double salary;
    private static String time;

    public EmployeeManager() {
    };

    public static ArrayList<Employee> getHouseKeeping() {
        return houseKeeping;
    }

    public static ArrayList<Employee> getDeskStaff() {
        return deskStaff;
    }

    public static ArrayList<Employee> getManager() {
        return manager;
    }

    public static void initializeEmployeeList() {
        houseKeeping.add(new HouseKeeping("H001", 3500.0, LocalTime.of(10, 0)));
        houseKeeping.add(new HouseKeeping("H002", 3000.0, LocalTime.of(15, 0)));
        deskStaff.add(new DeskStaff("D001", 4000.0, LocalTime.of(13, 0)));
        deskStaff.add(new DeskStaff("D002", 3500.0, LocalTime.of(10, 0)));
        manager.add(new Manager("M001", 6500.0, LocalTime.of(10, 0)));
    }

    public static void addNewStaff() {
        employeeList.clear();
        do {
            try {
                System.out.print("1.Housekeeping\n"
                        + "2.Desk Staff\n"
                        + "3.Manager\nPlease Enter The Staff Type (1 - 3):");
                staffType = scanner.nextInt();
                scanner.nextLine();
                if (staffType < 1 || staffType > 3) {
                    System.out.println("Please Enter Number 1 - 3");
                    validType = false;
                } else {
                    validType = true;
                }
            } catch (Exception ex) {
                System.out.println("Please Enter A Number !!!");
                validType = false;
                scanner.nextLine();
            }
        } while (!validType);
        switch (staffType) {
            case 1:
                employeeList.clear();
                employeeList.addAll(EmployeeManager.getHouseKeeping());

                for (Employee empl : employeeList) {
                    System.out.println(empl);
                }
                break;
            case 2:
                employeeList.clear();
                employeeList.addAll(EmployeeManager.getDeskStaff());

                for (Employee empl : employeeList) {
                    System.out.println(empl);
                }
                break;
            case 3:
                employeeList.clear();
                employeeList.addAll(EmployeeManager.getManager());

                for (Employee empl : employeeList) {
                    System.out.println(empl);
                }
                break;
        }
        do {
            System.out.print("Enter the Staff ID(Without Duplicate):");
            staffID = scanner.next();
            boolean duplicateFound = houseKeeping.stream().anyMatch(e -> e.getStaffID().equalsIgnoreCase(staffID))
                    || deskStaff.stream().anyMatch(e -> e.getStaffID().equalsIgnoreCase(staffID))
                    || manager.stream().anyMatch(e -> e.getStaffID().equalsIgnoreCase(staffID));

            if (staffType == 1 && !staffID.toUpperCase().startsWith("H")) {
                System.out.println("Housekeeping ID must start with 'H'!");
                validType = false;
                continue;

            } else if (staffType == 2 && !staffID.toUpperCase().startsWith("D")) {
                System.out.println("Desk Staff ID must start with 'D'!");
                validType = false;
                continue;

            } else if (staffType == 3 && !staffID.toUpperCase().startsWith("M")) {
                System.out.println("Manager ID must start with 'M'!");
                validType = false;
                continue;
            }
            for (Employee empl : employeeList) {
                if (empl.getStaffID().equalsIgnoreCase(staffID)) {
                    System.out.println("Duplicated Staff ID");
                    validType = false;
                    duplicateFound = true;
                    continue;
                }
            }
            if (!duplicateFound) {
                do {
                    try {
                        System.out.print("Enter the basic salary(RM):");
                        salary = scanner.nextDouble();
                        scanner.nextLine();
                        if (salary < 0) {
                            System.out.println("Salary cannot be negative !");
                            validType = false;
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid,Please Enter The Number");
                        validType = false;
                    }

                    System.out.print("Enter the Schedule Time (HH:mm): ");
                    time = scanner.nextLine();

                    try {
                        schedule = LocalTime.parse(time, formatter);
                        validType = true;
                    } catch (DateTimeException e) {
                        System.out.println("Invalid time format! Use HH:mm (e.g. 09:00).");
                        validType = false;
                    }
                } while (validType == false);
            }
        } while (!validType);

        switch (staffType) {
            case 1:
                houseKeeping.add(new HouseKeeping(staffID, salary, schedule));
                break;
            case 2:
                deskStaff.add(new DeskStaff(staffID, salary, schedule));
                break;
            case 3:
                manager.add(new Manager(staffID, salary, schedule));
                break;
            default:
                System.out.println("Invalid,Please Try Again");
                break;
        }
    }

    public static void deleteStaff() {

        try {
            employeeList.clear();
            System.out.print("1.Housekeeping\n"
                    + "2.Desk Staff\n"
                    + "3.Manager\nPlease Enter The Staff Type (1 - 3):");
            staffType = scanner.nextInt();
            if (staffType < 1 || staffType > 3) {
                System.out.println("Please Enter Within 1 to 3 !!!");
                return;
            }
            scanner.nextLine();
        } catch (InputMismatchException ex) {
            System.out.println("Please enter the number !!");
            return;
        }

        switch (staffType) {
            case 1:
                employeeList.addAll(EmployeeManager.getHouseKeeping());

                for (Employee empl : employeeList) {
                    System.out.println(empl);
                }
                break;
            case 2:
                employeeList.addAll(EmployeeManager.getDeskStaff());

                for (Employee empl : employeeList) {
                    System.out.println(empl);
                }
                break;
            case 3:
                employeeList.addAll(EmployeeManager.getManager());

                for (Employee empl : employeeList) {
                    System.out.println(empl);
                }
                break;
        }
        if (employeeList.isEmpty()) {
            System.out.println("Current Employee Record Is Empty !!!");
            return;
        }
        boolean valid = false;
        do {
            scanner.nextLine();
            System.out.print("Enter the Staff ID:");
            staffID = scanner.nextLine();

            for (Employee emp1 : employeeList) {
                if (emp1.getStaffID().equalsIgnoreCase(staffID)) {
                    switch (staffType) {
                        case 1:
                            houseKeeping.removeIf(employee -> employee.getStaffID().equalsIgnoreCase(staffID));
                            break;
                        case 2:
                            deskStaff.removeIf(employee -> employee.getStaffID().equalsIgnoreCase(staffID));
                            break;
                        case 3:
                            manager.removeIf(employee -> employee.getStaffID().equalsIgnoreCase(staffID));
                            break;
                        default:
                            System.out.println("Invalid. Please Try Again");
                    }
                    valid = true;
                    System.out.println("Staff " + staffID + " has been deleted");
                    break;
                }
            }
            if (!valid) {
                System.out.println("Staff Not Exist");
            }

        } while (!valid);

    }

    public static void employeeListCheck() {

        employeeList.clear();

        employeeList.addAll(EmployeeManager.getDeskStaff());
        employeeList.addAll(EmployeeManager.getHouseKeeping());
        employeeList.addAll(EmployeeManager.getManager());

        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        while (true) {
            try {
                System.out.print("0.Exit\n" + "1.Update Monthly salary\n" + "Please enter your choice:");
                int selection = scanner.nextInt();
                scanner.nextLine();

                if (selection == 1) {
                    System.out.print("Enter ID that want to update:");
                    String empID = scanner.nextLine();
                    updateSalary(empID);
                    return;

                } else if (selection == 0) {

                    return;
                } else {
                    System.out.print("\033c");
                    System.out.println("Please enter a valid selection(0 or 1)");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number:");
                scanner.nextLine();
            }
        }
    }

    public static void updateEmployeeDetail() {
        employeeList.clear();

        employeeList.addAll(EmployeeManager.getDeskStaff());
        employeeList.addAll(EmployeeManager.getHouseKeeping());
        employeeList.addAll(EmployeeManager.getManager());

        System.out.print("Enter The Employee ID For Update :");
        String empID = scanner.nextLine();
        Employee tempEmp = null;
        for (Employee emp : employeeList) {
            if (emp.getStaffID().equalsIgnoreCase(empID)) {
                System.out.println(emp);
                tempEmp = emp;
                break;
            }
        }

        if (tempEmp == null) {
            System.out.println("Employee ID not found!");
            return;
        }
        do {
            try {
                System.out.print("Enter the basic salary(RM):");
                salary = scanner.nextDouble();
                scanner.nextLine();
                if (salary < 0) {
                    System.out.println("Salary cannot be negative !");
                    validType = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid,Please Enter The Number");
                validType = false;
            }

            System.out.print("Enter the schedule time (HH:mm): ");
            time = scanner.nextLine();
            try {
                schedule = LocalTime.parse(time, formatter);
                validType = true;
            } catch (DateTimeException e) {
                System.out.println("Invalid time format! Use HH:mm (e.g. 09:00).");
                validType = false;
            }
        } while (validType == false);
        System.out.println("Update Sucessful !!!");
        tempEmp.setBasicSalary(salary);
        tempEmp.setSchedule(schedule);

    }
    public static void updateSalary() {
        Scanner sc = scanner;
        System.out.print("Enter employee ID to update salary: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("No ID entered.");
            return;
        }
        updateSalary(id);
    }
    public static void updateSalary(String empID) {
        if (empID == null || empID.trim().isEmpty()) {
            updateSalary();
            return;
        }

        String id = empID.trim();
        boolean found = false;

        for (Employee e : Employee.getEmployeeList()) {
            if (e.getStaffID().equalsIgnoreCase(id)) {
                found = true;
                try {
                    System.out.print("Enter new monthly salary for " + id + ": ");
                    double newSalary = Double.parseDouble(scanner.nextLine());
                    e.setBasicSalary(newSalary);
                    e.setMonthlySalary(newSalary);

                    System.out.println("Salary updated for " + id + " -> RM " + String.format("%.2f", newSalary));
                } catch (Exception ex) {
                    System.out.println("Invalid amount.");
                }
                break;
            }
        }

    if (!found) {
        System.out.println("Employee not found: " + id);
    }
}
    public void showEmployeeMenu() {
    while (true) {
        System.out.println("\n=== EMPLOYEE MENU ===");
        System.out.println("1. List employees");
        System.out.println("2. Add employee");
        System.out.println("3. Update employee detail");
        System.out.println("4. Update salary");
        System.out.println("5. Remove employee");
        System.out.println("6. Back");
        System.out.print("Enter choice: ");
        int choice;
        try { choice = Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { System.out.println("Enter a number."); continue; }

        switch (choice) {
            case 1: employeeListCheck();
            case 2: addNewStaff();
            case 3: updateEmployeeDetail();
            case 4: updateSalary();
            case 5: deleteStaff();
            case 6: { return; }
            default: System.out.println("Invalid choice.");
        }
    }
    }
}

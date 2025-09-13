import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManager {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    private static final ArrayList<Employee> houseKeeping = new ArrayList<>();
    private static final ArrayList<Employee> deskStaff = new ArrayList<>();
    private static final ArrayList<Employee> manager = new ArrayList<>();
    private static final ArrayList<Employee> employeeList = new ArrayList<>();

    private static int staffType = 0;
    private static boolean validType = false;
    private static String staffID;
    private static LocalTime schedule;
    private static double salary;
    private static String time;

    public EmployeeManager() { }

    public static ArrayList<Employee> getHouseKeeping() { return houseKeeping; }
    public static ArrayList<Employee> getDeskStaff() { return deskStaff; }
    public static ArrayList<Employee> getManager() { return manager; }

    public static void initializeEmployeeList() {
        houseKeeping.add(new HouseKeeping("H001", 3500.0, LocalTime.of(10, 0)));
        houseKeeping.add(new HouseKeeping("H002", 3000.0, LocalTime.of(15, 0)));
        deskStaff.add(new DeskStaff("D001", 4000.0, LocalTime.of(13, 0)));
        deskStaff.add(new DeskStaff("D002", 3500.0, LocalTime.of(10, 0)));
        manager.add(new Manager("M001", 6500.0, LocalTime.of(10, 0)));
    }

    // --- CREATE ---
    public static void addNewStaff() {
        employeeList.clear();
        // Choose staff type
        do {
            try {
                System.out.print("1.Housekeeping\n2.Desk Staff\n3.Manager\nPlease Enter The Staff Type (1 - 3): ");
                staffType = Integer.parseInt(scanner.nextLine().trim());
                if (staffType < 1 || staffType > 3) {
                    System.out.println("Please enter number 1 - 3.");
                    validType = false;
                } else {
                    validType = true;
                }
            } catch (Exception ex) {
                System.out.println("Please enter a number!");
                validType = false;
            }
        } while (!validType);

        // Show existing IDs of that type
        switch (staffType) {
            case 1:
                employeeList.addAll(EmployeeManager.getHouseKeeping());
                break;
            case 2:
                employeeList.addAll(EmployeeManager.getDeskStaff());
                break;
            case 3:
                employeeList.addAll(EmployeeManager.getManager());
                break;
            default:
                System.out.println("Invalid type.");
                return;
        }
        for (Employee empl : employeeList) System.out.println(empl);

        // Enter unique ID with correct prefix
        boolean duplicateFound;
        do {
            System.out.print("Enter the Staff ID (no duplicates): ");
            staffID = scanner.nextLine().trim();

            duplicateFound = houseKeeping.stream().anyMatch(e -> e.getStaffID().equalsIgnoreCase(staffID))
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

            if (duplicateFound) {
                System.out.println("Duplicated Staff ID!");
                validType = false;
                continue;
            }
            validType = true;
        } while (!validType);

        // Salary
        do {
            try {
                System.out.print("Enter the basic salary (RM): ");
                salary = Double.parseDouble(scanner.nextLine().trim());
                if (salary < 0) {
                    System.out.println("Salary cannot be negative!");
                    validType = false;
                } else {
                    validType = true;
                }
            } catch (Exception ex) {
                System.out.println("Invalid, please enter a number.");
                validType = false;
            }
        } while (!validType);

        // Schedule
        do {
            System.out.print("Enter the schedule time (HH:mm): ");
            time = scanner.nextLine().trim();
            try {
                schedule = LocalTime.parse(time, formatter);
                validType = true;
            } catch (DateTimeException e) {
                System.out.println("Invalid time format! Use HH:mm (e.g. 09:00).");
                validType = false;
            }
        } while (!validType);

        // Create
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
                System.out.println("Invalid, please try again.");
                break;
        }
        System.out.println("Staff added.");
    }

    // --- READ / LIST ---
    public static void employeeListCheck() {
        employeeList.clear();
        employeeList.addAll(EmployeeManager.getDeskStaff());
        employeeList.addAll(EmployeeManager.getHouseKeeping());
        employeeList.addAll(EmployeeManager.getManager());

        if (employeeList.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }
        for (Employee emp : employeeList) System.out.println(emp);

        // Optional quick action
        while (true) {
            try {
                System.out.print("0.Exit\n1.Update Monthly Salary\nPlease enter your choice: ");
                int selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection == 1) {
                    System.out.print("Enter Employee ID to update salary: ");
                    String id = scanner.nextLine().trim();
                    updateSalary(id);
                } else if (selection == 0) {
                    return;
                } else {
                    System.out.println("Please enter a valid selection (0 or 1).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
            }
        }
    }

    // --- UPDATE (details) ---
    public static void updateEmployeeDetail() {
        employeeList.clear();
        employeeList.addAll(EmployeeManager.getDeskStaff());
        employeeList.addAll(EmployeeManager.getHouseKeeping());
        employeeList.addAll(EmployeeManager.getManager());

        System.out.print("Enter the Employee ID for update: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("No ID entered.");
            return;
        }

        Employee tempEmp = null;
        for (Employee emp : employeeList) {
            if (emp.getStaffID().equalsIgnoreCase(id)) {
                tempEmp = emp; break;
            }
        }
        if (tempEmp == null) {
            System.out.println("Employee ID not found!");
            return;
        }

        // Salary
        do {
            try {
                System.out.print("Enter the basic salary (RM): ");
                salary = Double.parseDouble(scanner.nextLine().trim());
                if (salary < 0) {
                    System.out.println("Salary cannot be negative!");
                    validType = false;
                } else {
                    validType = true;
                }
            } catch (Exception ex) {
                System.out.println("Invalid, please enter a number.");
                validType = false;
            }

            // Schedule
            System.out.print("Enter the schedule time (HH:mm): ");
            time = scanner.nextLine().trim();
            try {
                schedule = LocalTime.parse(time, formatter);
                validType = true;
            } catch (DateTimeException e) {
                System.out.println("Invalid time format! Use HH:mm (e.g. 09:00).");
                validType = false;
            }
        } while (!validType);

        tempEmp.setBasicSalary(salary);
        tempEmp.setSchedule(schedule);
        System.out.println("Update successful!");
    }

    // --- UPDATE (salary) ---
    public static void updateSalary() {
        System.out.print("Enter employee ID to update salary: ");
        String id = scanner.nextLine().trim();
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
                    double newSalary = Double.parseDouble(scanner.nextLine().trim());
                    if (newSalary < 0) {
                        System.out.println("Salary cannot be negative!");
                        return;
                    }
                    e.setBasicSalary(newSalary);
                    e.setMonthlySalary(newSalary); // keep simple; subclasses may recalc elsewhere
                    System.out.println("Salary updated for " + id + " -> RM " + String.format("%.2f", newSalary));
                } catch (Exception ex) {
                    System.out.println("Invalid number.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found: " + id);
        }
    }

    // --- DELETE ---
    public static void deleteStaff() {
        try {
            employeeList.clear();
            System.out.print("1.Housekeeping\n2.Desk Staff\n3.Manager\nPlease Enter The Staff Type (1 - 3): ");
            staffType = Integer.parseInt(scanner.nextLine().trim());
            if (staffType < 1 || staffType > 3) {
                System.out.println("Please enter within 1 to 3!");
                return;
            }
        } catch (Exception ex) {
            System.out.println("Please enter a number!");
            return;
        }

        switch (staffType) {
            case 1:
                employeeList.addAll(EmployeeManager.getHouseKeeping());
                break;
            case 2:
                employeeList.addAll(EmployeeManager.getDeskStaff());
                break;
            case 3:
                employeeList.addAll(EmployeeManager.getManager());
                break;
        }

        for (Employee empl : employeeList) System.out.println(empl);
        if (employeeList.isEmpty()) {
            System.out.println("Current employee record is empty!");
            return;
        }

        System.out.print("Enter the Staff ID to delete: ");
        String targetId = scanner.nextLine().trim();
        boolean removed = false;

        switch (staffType) {
            case 1:
                removed = houseKeeping.removeIf(e -> e.getStaffID().equalsIgnoreCase(targetId));
                break;
            case 2:
                removed = deskStaff.removeIf(e -> e.getStaffID().equalsIgnoreCase(targetId));
                break;
            case 3:
                removed = manager.removeIf(e -> e.getStaffID().equalsIgnoreCase(targetId));
                break;
        }

        if (removed) {
            System.out.println("Staff " + targetId + " has been deleted.");
        } else {
            System.out.println("Staff not exist.");
        }
    }

    public void showEmployeeMenu() {
        while (true) {
            System.out.println(
                    "\n+----+-------------------------+\n" +
                    "| NO |        EMPLOYEE         |\n" +
                    "+----+-------------------------+\n" +
                    "| 1. | List Employee           |\n" +
                    "| 2. | Add Employee            |\n" +
                    "| 3. | Update Employee Detail  |\n" +
                    "| 4. | Update Salary           |\n" +
                    "| 5. | Remove Employee         |\n" +
                    "| 0. | Back                    |\n" +
                    "+----+-------------------------+\n"
            );
            System.out.print("Enter choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Enter a number.");
                continue;
            }

            switch (choice) {
                case 1: employeeListCheck();      break; // FIX: prevent fall-through
                case 2: addNewStaff();            break; // FIX
                case 3: updateEmployeeDetail();   break; // FIX
                case 4: updateSalary();           break; // FIX
                case 5: deleteStaff();            break; // FIX
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}


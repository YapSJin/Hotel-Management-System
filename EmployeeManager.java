/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class EmployeeManager {
    private List<Employee> employees;
    
    public EmployeeManager() {
        this.employees = new ArrayList<>();
        // Load employees from database or file in a real application
    }
    
    public void showEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n=== EMPLOYEE MANAGEMENT ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Remove Employee");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    removeEmployee(scanner);
                    break;
                case 5:
                    System.out.println("Returning to admin menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
    
    private void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee position: ");
        String position = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        Employee employee = new Employee(name, position, salary);
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }
    
    private void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }
        
        System.out.println("\n=== ALL EMPLOYEES ===");
        System.out.printf("%-5s %-20s %-15s %-10s\n", "ID", "Name", "Position", "Salary");
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            System.out.printf("%-5d %-20s %-15s $%-10.2f\n", 
                i + 1, emp.getName(), emp.getPosition(), emp.getSalary());
        }
    }
    
    private void updateEmployee(Scanner scanner) {
        viewEmployees();
        if (employees.isEmpty()) return;
        
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (id < 1 || id > employees.size()) {
            System.out.println("Invalid employee ID!");
            return;
        }
        
        Employee emp = employees.get(id - 1);
        
        System.out.print("Enter new name (current: " + emp.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) emp.setName(name);
        
        System.out.print("Enter new position (current: " + emp.getPosition() + "): ");
        String position = scanner.nextLine();
        if (!position.isEmpty()) emp.setPosition(position);
        
        System.out.print("Enter new salary (current: " + emp.getSalary() + "): ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isEmpty()) {
            try {
                double salary = Double.parseDouble(salaryInput);
                emp.setSalary(salary);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format. Salary not updated.");
            }
        }
        
        System.out.println("Employee updated successfully!");
    }
    
    private void removeEmployee(Scanner scanner) {
        viewEmployees();
        if (employees.isEmpty()) return;
        
        System.out.print("Enter employee ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (id < 1 || id > employees.size()) {
            System.out.println("Invalid employee ID!");
            return;
        }
        
        Employee emp = employees.remove(id - 1);
        System.out.println("Employee " + emp.getName() + " removed successfully!");
    }
}
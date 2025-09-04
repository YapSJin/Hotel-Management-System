/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class EmployeeManager {
    
    private static Scanner scanner = new Scanner(System.in);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
    
    private static ArrayList<Employee> houseKeeping = new ArrayList<>();
    private static ArrayList<Employee> deskStaff = new ArrayList<>();
    private static ArrayList<Employee> manager = new ArrayList<>();
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static int staffType;
    private static boolean validType = false;
    private static String staffID;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private static LocalTime schedule;
    private static double salary; 
    private static String time;
    
    public EmployeeManager(){
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
    
    public static void initializeEmployeeList(){
        houseKeeping.add(new HouseKeeping("C001",3500.0, LocalTime.of(15,0)));
        houseKeeping.add(new HouseKeeping("C002",3000.0, LocalTime.of(10,0)));
        deskStaff.add(new DeskStaff("D001",4000.0, LocalTime.of(13,0)));
        deskStaff.add(new DeskStaff("D002",5000.0, LocalTime.of(17,0)));
        manager.add(new Manager("M001",8000.0, LocalTime.of(10, 0)));
        
    }
    public static void addNewStaff(){
        employeeList.clear();
        do {
            try {
                System.out.print("1Houskeeping\n" + "2.Desk Staff\n" + "3.Manager\nPlease Enter The Staff Type (1-3):");
                staffType = scanner.nextInt();
                scanner.nextLine();
                if (staffType <1 || staffType > 3){
                    System.out.println("Please enter a number between 1-3");
                    validType = false;
                }
                else {
                    validType = true;
                }
            }
            catch(Exception ex){
               System.out.print("Please Enter a Number!!!!!!!!!!!!!!!!!!!!!!!!!!!");
               validType = false;
               scanner.nextLine();
            }
        }
        while(!validType);
        switch (staffType){
            case 1:
                employeeList.clear();
                employeeList.addAll(EmployeeManager.getHouseKeeping());
                
                for (Employee emp1 : employeeList){
                    System.out.println(emp1);
                }
                break;
            case 2:
                employeeList.clear();
                employeeList.addAll(EmployeeManager.getDeskStaff());
                
                for (Employee emp1 : employeeList){
                    System.out.print(emp1);
                    
                }
                break;
            case 3: 
                employeeList.clear();
                employeeList.addAll(EmployeeManager.getManager());
                
                for (Employee emp1 : employeeList){
                    System.out.print(emp1);
                }
                break; 
    }
        do{
            System.out.print("enter the staff's ID(without duplicate):");
            staffID = scanner.next();
            
            boolean duplicateFound = houseKeeping.stream().anyMatch(e -> e.getStaffID().equalsIgnoreCase(staffID))
                    || deskStaff.stream().anyMatch(e -> e.getStaffID().equalsIgnoreCase(staffID))
                    || manager.stream().anyMatch(e -> e.getStaffID().equalsIgnoreCase(staffID));
            
            if(staffType == 1 && !staffID.toUpperCase().startsWith("C")){
               System.out.print ("Houskeeping ID must start with 'C'");
               validType = false;
               continue;
            }
            else if(staffType == 2 && !staffID.toUpperCase().startsWith("D")){
                System.out.print ("Dask Staff ID must start with 'D'");
                continue;
            }
            else if(staffType == 3 && !staffID.toUpperCase().startsWith("M")){
                System.out.print ("Manager ID must start with 'M'");
                continue;
            }
             for (Employee empl : employeeList) {
                if (empl.getStaffID().equalsIgnoreCase(staffID)) {
                    System.out.println("Duplicated Staff ID");
                    validType = false;
                    duplicateFound = true;
                    continue;
                    
                }
              if (!duplicateFound){
                  do{
                      try {
                          System.out.print("enter the basic salary(RM):");
                          salary = scanner.nextDouble();
                          scanner.nextLine();
                          if (salary < 0){
                              System.out.println("salary cannot be negative!!!!");
                              validType = false;
                              
                          }
                      }catch (InputMismatchException ex){
                          System.out.println("Invalid, please Enter the Number");
                          validType = false;
                          
                      }
                      System.out.print("enter the schedule time (HH:mm):");
                      time = scanner.nextLine();
                      
                      try {
                          schedule = LocalTime.parse(time, formatter);
                          validType = true;
                          
                      }catch (DateTimeException e){
                          System.out.println("invalid time format! please follow (HH:mm)");
                          validType = false;
                      }
                      
                  }while (validType == false);
                  
              }
                
        }
        
    }while (!validType);
       switch (staffType){
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
               System.out.println("invalid, please try again");
               break;
               
       } 
   
        }
        
    

    
    
    
    

 public static void deleteStaff(){
        try{
            employeeList.clear();
            System.out.print("1.Housekeeping\n"+"2.Desk Staff\n"+
                    "3.Manager\nPlease enter the staff type(1-3):");
            staffType = scanner.nextInt();
            if (staffType <1 || staffType > 3 ){
                System.out.println("Please Enter Within 1 to 3 !!!");
                return;
            }
         scanner.nextLine();
        } catch (InputMismatchException ex) {
            System.out.println("Please enter the number !!");
            return;
        }
 }
    
}
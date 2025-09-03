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
    
    private static ArrayList<Employee> housKeeping = new ArrayList<>();
    private static ArrayList<Employee> deskStaff = new ArrayList<>();
    private static ArrayList<Employee> manager = new ArrayList<>();
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static int staffype;
    private static boolean validType = false;
    private static String staffID;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private static LocalTime schedule;
    private static double salary; 
    private static String time;
    
    public EmployeeManager(){
    };

    public static ArrayList<Employee> getHousKeeping() {
        return housKeeping;
    }

    public static ArrayList<Employee> getDeskStaff() {
        return deskStaff;
    }

    public static ArrayList<Employee> getManager() {
        return manager;
    }
    
    public static void initializeEmployeeList(){
        housKeeping.add(new HouseKeeping("C001",3500.0, LocalTime.of(15,0)));
        housKeeping.add(new HouseKeeping("C002",3000.0, LocalTime.of(10,0)));
        deskStaff.add(new DeskStaff("D001",4000.0, LocalTime.of(13,0)));
        deskStaff.add(new DeskStaff("D001",5000.0, LocalTime.of(17,0)));
        manager.add(new Manager("M001",8000.0, LocalTime.of(10, 0)));
        
    }
    public static void addNewStaff(){
        Employee.clear();
        
    }
    
}


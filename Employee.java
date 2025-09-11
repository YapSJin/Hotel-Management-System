/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Employee{
    
    private String staffID;
    private String role;
    private double basicSalary;
    private double monthlySalary;
    
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    LocalTime timeNow;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
    
    private LocalTime schedule;
    
    protected Employee(String staffID, String role, double BasicSalary, LocalTime schedule){
        
        this.staffID = staffID;
        this.role = role;
        this.basicSalary = basicSalary;
        this.schedule = schedule;
        
        employeeList.add(this);
        
    }
    public abstract double calculateMonthlySalary();

    public String getStaffID() {
        return staffID;
    }

    public String getRole() {
        return role;
    }

   public double getBasicSalary(){
       return basicSalary;
   }
   
   public void setMonthlySalary(double monthlySalary){
       if(monthlySalary < 0){
           System.out.println("Invalid");
           return;
       }
       this.monthlySalary = monthlySalary;
   }
   
   public LocalTime getSchedule(){
       return schedule;
   }
   
   public void setSchedule(LocalTime schedule){
       this.schedule = schedule;
       
   }
   
   public static ArrayList<Employee> getEmployeeList(){
       return employeeList;
   }
   
   public String toString(){
       String border = "+===============+===============+===============+===============+===============+";
       String header = String.format("| %13s | %13s | %13s | %13s | %14s |", 
                                    "Staff ID", "Role", "Basic Salary", "scheduler", "monthly Salary");
       
       String dataRow = String.format("| %13s | %13s | RM%11.2f | %13s | RM%12.2f |",
                                      staffID, role, basicSalary, schedule, monthlySalary);
       
       return border + "\n" + 
              header + "\n" + 
              border + "\n" + 
              dataRow + "\n" + 
              border;
   }
   public void updateSchedular(LocalTime schedule, int hour, int minute, int second){
       this.schedule = schedule.withHour(hour).withMinute(minute).withSecond(second);
   }
}


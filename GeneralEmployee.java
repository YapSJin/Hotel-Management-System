/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.time.LocalTime;

public class GeneralEmployee extends Employee {
    public GeneralEmployee(String id, String role, double salaryPerHour, LocalTime schedule) {
        super(id, role, salaryPerHour, schedule);
    }

    @Override
    public double calculateMonthlySalary() {
        // Example: Work hours = difference between start time and 8 hours shift
        // Here we simply assume fixed 8 hours per day × 30 days
        return getBasicSalary() * 8 * 30; 
    }
}


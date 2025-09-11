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
    public GeneralEmployee(String id, String role, double monthlySalary, LocalTime schedule) {
        super(id, role, monthlySalary, schedule);
        setMonthlySalary(monthlySalary);
    }

    @Override
    public double calculateMonthlySalary() {
        // Directly return monthly salary since it's predefined
        return getBasicSalary();
    }
}


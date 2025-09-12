import java.time.LocalTime;
public class Manager extends Employee{
    private double bonus;

    public Manager(String staffID, double basicSalary, LocalTime schedule){
        super(staffID, "Manager", basicSalary, schedule);

    }
    public void setBonus(double bonues){
        this.bonus = bonus;
    }
    @Override
    public String toString(){
        return "Manager Info:\n" + super.toString() + String.format("\nBonus:RM%.2f", bonus) + "\n"; 
    }
    @Override
    public double calculateMonthlySalary(){
        return getBasicSalary() + bonus;
    }
}
import java.time.LocalTime;
public class DeskStaff extends Employee {
    public static double performanceBonusRate = 0.10;
    private double performanceBonus = 0.0;

    public DeskStaff(String staffID,double basicSalary, LocalTime schedule){
        super(staffID, "Desk Staff",basicSalary, schedule);
        
    }
    public void setPerformanceBonus(double performanceBonus){
        this.performanceBonus = performanceBonus;
    }

    @Override 
    public String toString(){
        return "Desk Staff info:\n" + super.toString() + String.format("\nPerformance Bonus: RM%.2f", performanceBonus) + "\n";
    }
    @Override
    public double calculateMonthlySalary(){
        performanceBonus = getBasicSalary() * performanceBonusRate;
        return getBasicSalary() + performanceBonus;
    }
}

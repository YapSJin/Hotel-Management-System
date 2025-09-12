import java.time.LocalTime;

public class HouseKeeping extends Employee{
    private int otHours;
    private static double otPaid = 8.0;

    public HouseKeeping(String staffID, double basicSalary, LocalTime schedule){
        super(staffID,"House Keeping", basicSalary,  schedule);
    }
    public void setOtHours(int otHours){
        this.otHours = otHours;
    }
    @Override 
    public String toString(){
        return "house Keeping info:\n" + super.toString() + "\nOver time hours:" + 
        otHours + String.format("\nOver time paid:RM%.2f", otHours * otPaid) + "\n";
    }
    @Override
    public double calculateMonthlySalary(){
        return getBasicSalary() + otHours * otPaid;
    }
}

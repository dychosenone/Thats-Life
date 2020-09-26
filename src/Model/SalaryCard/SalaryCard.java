package Model.SalaryCard;

public class SalaryCard {

    private final int SALARY_MULTIPLIER = 10000;
    private final int TAX_MULTIPLIER = 1000;

    private int salary;
    private int taxDue;
    private boolean available;

    public SalaryCard () {

        this.salary = generateSalary();
        this.taxDue = generateTaxDue();
        available = true;

    }

    public int getSalary() {
        return salary;
    }
    public int getTaxDue (){
        return taxDue;
    }
    
    public boolean getAvailability () {
    	return available;
    }
    
    public void takeCard () {
    	available = false;
    }
    
    public void returnCard () {
    	available = true;
    }

    public int generateSalary () {
        return (int) (Math.random() * (10 - 1 + 1) + 1) * SALARY_MULTIPLIER;
    }

    public int generateTaxDue () {
        return (int) (Math.random() * (10 - 1 + 1) + 1) * TAX_MULTIPLIER;
    }

}

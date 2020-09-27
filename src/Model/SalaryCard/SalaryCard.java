package Model.SalaryCard;

public class SalaryCard {

    private final int SALARY_MULTIPLIER = 10000;
    private final int TAX_MULTIPLIER = 1000;

    private int salary;
    private int taxDue;
    private boolean available;

    /**
     * Salary Card sets the salary and tax which are generated by the generateSalary() and generateTaxDue() function
     * The availability is then set to true.
     */
    public SalaryCard () {

        this.salary = generateSalary();
        this.taxDue = generateTaxDue();
        available = true;

    }

    /**
     * This function returns the Salary Amount.
     * @return Salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * This function returns the tax due amount.
     * @return Tax Due
     */
    public int getTaxDue (){
        return taxDue;
    }

    /**
     * This function returns the card availability.
     * @return Card Availability(True for available, false for not available)
     */
    public boolean getAvailability () {
    	return available;
    }

    /**
     * When a card is taken, the availability is set to false.
     */
    public void takeCard () {
    	available = false;
    }

    /**
     * When a card is returned, the availability is set to true.
     */
    public void returnCard () {
    	available = true;
    }

    /**
     * This function generates a random salary value
     * @return Generated Salary
     */
    public int generateSalary () {
        return (int) (Math.random() * (10 - 1 + 1) + 1) * SALARY_MULTIPLIER;
    }

    /**
     * This function generates a random Tax Due Value
     * @return Generated Tax Due Value
     */
    public int generateTaxDue () {
        return (int) (Math.random() * (10 - 1 + 1) + 1) * TAX_MULTIPLIER;
    }

}

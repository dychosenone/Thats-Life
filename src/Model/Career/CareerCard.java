package Model.Career;

public class CareerCard {

    private String careerName;
    private int minRaise;
    private int maxRaise;
    private int payRaise;
    private boolean available;
    private boolean needsCollegeDegree;

    /**
     * When the Career Card is initialized, the name of the career, the minimum and maximum raises, the College Degree and card availability is set.
     * @param name Name of Career
     * @param min Minimum Raises
     * @param max Maximum Raises
     * @param college Career College Degree Requirement
     */
    public CareerCard (String name, int min, int max, boolean college) {
        this.careerName = name;
        this.needsCollegeDegree = college;
        this.minRaise = min;
        this.maxRaise = max;
        available = true;

        this.payRaise = generatePayRaise();
    }

    /**
     * Gets the College Degree Requirement boolean
     * @return True if Needs College Degree, false if not required
     */
    public boolean getNeedDegree () { 
    	return this.needsCollegeDegree; 
    	}

    /**
     * Gets the Career Card name
      * @return Name of Career
     */
    public String getCareerName () {
    	return careerName;
    }

    /**
     * Gets the Pay Raise of the Career Card
     * @return Pay Raise of Career Card
     */
    public int getPayRaise () { 
    	return this.payRaise; 
    }

    /**
     * Gets and returns the card availability
     * @return Card Availability
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
     * This function generates the PayRaise based on the minimum and maximum Pay Raise.
     * @return Pay Raise
     */
    private int generatePayRaise () {
        int raises = (int)(Math.random() * (this.maxRaise - this.minRaise + 1) + this.minRaise);
        return raises;
    }

    /**
     * This function converts careerName : Availability to string
     * @return String CareerName : Available (true or false)
     */
    @Override 
    public String toString() {
    	return careerName +":"+available + minRaise + ":" + maxRaise;
    }
}


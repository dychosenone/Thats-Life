package Model.Career;

public class CareerCard {

    private String careerName;
    private int minRaise;
    private int maxRaise;
    private int payRaise;
    private boolean available;
    private boolean needsCollegeDegree;

    public CareerCard (String name, int min, int max, boolean college) {
        this.careerName = name;
        this.minRaise = min;
        this.maxRaise = max;
        this.needsCollegeDegree = college;
        available = true;

        this.payRaise = generatePayRaise();
    }

    public boolean getNeedDegree () { 
    	return this.needsCollegeDegree; 
    	}
    
    public String getCareerName () {
    	return careerName;
    }
    
    
    public int getPayRaise () { 
    	return this.payRaise; 
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
    
    private int generatePayRaise () {
        return this.minRaise + (int)(Math.random() * this.maxRaise);
    }
    
    @Override 
    public String toString() {
    	return careerName +":"+available;
    }
}


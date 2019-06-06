/**
 * Teller is used to be the teller of the bank in this program. It handles transactions with the customers and is assigned to a line.
 * The tellers are also responsible for making sure that the customers at the drive up window are being helped
 *
 */
public class Teller {
    /**assigns a line to the teller*/
    private Line line;
    /**creates a name for the teller*/
    private String name;
    /**stores how many customers the teller has helped*/
    private int numCustomersHelped;
    /**begins idle time. Starts at 0*/
    private int StartIdleTime;
    /**keeps track of the total idle time the teller has had throughout the entirety of the program*/
    private int totalIdleTime;
    /**stores whether or not the teller is busy*/
    private boolean isBusy;
    /**stores whether or not there is a teller at the drive up*/
    private boolean isAtDriveUp;
    
    /**
     * Creates a teller object, initializes all the instance variables, and takes the following parameters:
     *
     * @param name	the name assigned to the Teller
     */
    public Teller(String name) {
        this.line = new Line();
        this.name = name;
        this.numCustomersHelped = 0;
        this.StartIdleTime = 0;
        this.isBusy = false;
        this.isAtDriveUp = false;
        this.totalIdleTime = 0;
    }
    
    /**
     * return the total idle time of the customer
     * @return int totalIdleTime
     */
    public int getTotalIdleTime() {
        return this.totalIdleTime;
    }
    
    /**
     * returns the next customer in line for the Teller to help
     * @return this.line.getNextCustomer()
     */
    public Event getNextCustomer() {
        return this.line.getNextCustomer();
    }
    
    /**
     * returns the Teller's name
     * @return name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * returns whether of not the Teller is busy
     * @return this.isBusy
     */
    public boolean getIsBusy() {
        return this.isBusy;
    }
    
    /**
     * Begins keeping track of how long the Teller is idle
     * @param clockTime	current time of the clock
     */
    public void setStartIdleTime(int clockTime) {
        this.StartIdleTime = clockTime;
    }
    
    /**
     * stops keeping track of how long the Teller is idle
     * @param clockTime	current time of the clock
     */
    public void endIdleTime(int clockTime) {
        this.totalIdleTime = this.totalIdleTime + (clockTime - this.StartIdleTime);
    }
    
    /**
     * calls setStartIdleTime and sets isBusy to false
     * @param clockTime	current time of the clock
     */
    public void setToIdle(int clockTime) {
        this.isBusy = false;
        setStartIdleTime(clockTime);
    }
    
    /**
     * calls endIdleTime and sets isBusy to true
     * @param clockTime	current time of the clock
     */
    public void setToBusy(int clockTime) {
        this.isBusy = true;
        endIdleTime(clockTime);
    }
    
    /**
     * returns how many customer
     * @param this.numCustomersHelped
     */
    public int getNumCustomersHelped() {
        return this.numCustomersHelped;
    }
    
    /**
     * increments this.numCustomersHelped by 1
     */
    public void incrementNumCustomersHelped() {
        this.numCustomersHelped++;
    }
    
    /**
     * returns the toString of Teller
     * @return returns the teller's name
     */
    public String toString() {
        return this.name;
    }
    
    /**
     * adds a customer to the teller's line
     * @param event		the event being added(Arrival)
     */
    public void addCustomerToTellerLine(Event event) {
        this.line.addCustomer(event);
    }
    
    /**
     * returns the size of the customer's line
     * @return this.line.getSize()
     */
    public int getTellerLineLength() {
        return this.line.getSize();
    }
    
    /**
     * returns whether or not there is a customer at the drive up window
     * @return this.isAtDriveUp
     */
    public boolean getIsAtDriveUp() {
        return this.isAtDriveUp;
    }
    
    /**
     * sets the status of this.isAtDriveUp to the passed in parameter
     * @param b 		a boolean value
     */
    public void setIsAtDriveUp(boolean b) {
        this.isAtDriveUp = b;
    }
}

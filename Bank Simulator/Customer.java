/**
 * Customer stores all of the data that we read in from the txt files. It stores all the information that we need to know about the customer,
 * specifically how long the customer had to wait.
 *
 * @author Noah Beer and Lucy Post
 */
public class Customer {
    /**stores what time the Customer arrives at the bank*/
    private int arrivalTime;
    /**Stores how long the Customer's transaction was*/
    private int transactionDuration;
    /**true if the customer is a walk in, false otherwise*/
    private boolean isWalkIn;
    /**stores the Customer's name*/
    private String customerName;
    /**true if the customer is waiting in line, false otherwise*/
    private boolean isWaiting;
    /**begins when the customer arrives in line*/
    private int startWaitTime;
    /**ends when the customer begins his transaction time*/
    private int endWaitTime;
    
    /**
     * Creates a Customer object, initializes all instance variables, and takes in the following parameters:
     *
     * @param customerName   name of Customer
     * @param arrivalTime	time customer arrives at bank
     * @param transactionDuration	how much time customer's transaction takes
     * @param isWalkIn	whether or not the customer is a walk in
     */
    public Customer(String customerName, int arrivalTime, int transactionDuration, boolean isWalkIn) {
        this.customerName = customerName;
        this.arrivalTime = arrivalTime;
        this.transactionDuration = transactionDuration;
        this.isWalkIn = isWalkIn;
        this.isWaiting = false;
        this.startWaitTime = 0;
        this.endWaitTime = 0;
    }
    
    /**
     * returns the customer's name
     * @return customerName
     */
    public String getCustomerName() {
        return this.customerName;
    }
    
    /**
     * returns the time the Customer arrives at the bank
     * @return arrivalTime
     */
    public int getArrivalTime() {
        return this.arrivalTime;
    }
    
    /**
     * returns how long the transaction was
     * @return transactionDuration
     */
    public int getTransactionDuration() {
        return this.transactionDuration;
    }
    
    /**
     * returns true if the customer is a walk in, false if the customer is a drive up
     * @return isWalkIn
     */
    public boolean isWalkIn() {
        return this.isWalkIn;
    }
    
    /**
     * returns the wait time of the Customer
     * @return this.endWaitTime - this.startWaitTime
     */
    public int getWaitTime() {
        return this.endWaitTime - this.startWaitTime;
    }
    
    /**
     * sets the status of isWaiting to true or false
     * @param isWaiting 		boolean value
     */
    public void setIsWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }
    
    /**
     * begins the wait time
     * @param clockTime		the current time of the clock
     */
    public void setStartWaitTime(int clockTime) {
        this.startWaitTime = clockTime;
    }
    
    /**
     * ends the wait time
     * @param clockTime		the current time of the clock
     */
    public void setEndWaitTime(int clockTime) {
        this.endWaitTime = clockTime;
    }
    
    /**
     * sets the value of isWalkIn to true or false
     */
    public void setIsWalkIn() {
        this.isWalkIn = true;
    }
}

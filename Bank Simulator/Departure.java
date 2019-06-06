/**
 * Departure implements Event and is used to create Departure Events in BankSimulation. Departure calculates what time a Customer
 * completes his/her transaction and departs from the bank.
 *
 * @author Noah Beer and Lucy Post
 */
public class Departure implements Event{
    /**stores what time the Customer departs at*/
    private int departureTime;
    /**stores a teller object*/
    private Teller teller;
    /**stores the Events position in the Priority Queue*/
    private int position;
    
    /**
     * The constructor creates a Departure item and takes in the following parameters:
     *
     * @param departureTime   what time the Customer departs the bank
     * @param teller   a teller object
     * @param position   The Events position in the Priority Queue
     */
    public Departure(int departureTime,Teller teller, int position) {
        this.departureTime = departureTime;
        this.teller = teller;
        this.position = position;
    }
    
    /**
     * Returns departureTime in order to be compared in the Event Priority Queue
     *
     * @return departureTime   what time the Customer depart the bank
     */
    public int getComparableTime() {
        return this.departureTime;
    }
    
    /**
     * returns the current teller
     * @return teller
     */
    public Teller getTeller() {
        return this.teller;
    }
    
    /**
     * Returns 0 signifying its a departure
     * @return 0
     */
    public int getEventType() {
        return 0;
    }
    
    /**
     * returns the event's position in the Priority Queue
     * @return
     */
    public int getCustomerPosition() {
        return this.position;
    }
}

/**
 * Event provides an interface to be implemented by Event and Departure. An Event Priority Queue is used to keep
 * everything in order and working properly with the clock.
 *
 * @author Noah Beer and Lucy Post
 */

public interface Event {
    
    /**
     * abstract method that is implemented in Arrival and Departure that will return a ComparableTime
     */
    public abstract int getComparableTime();
    
    /**
     * abstract method that is implemented in Arrival and Departure that will return the EventType
     */
    public abstract int getEventType();
    
    /**
     * abstract method that is implemented in Arrival and Departure that will return the Customer's position in the priority queue
     */
    public abstract int getCustomerPosition();
    
}

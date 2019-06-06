import java.util.LinkedList;
import java.util.Queue;
/**
 * Line is used to create an Event Queue that keeps track of all of the customers that have arrived at the bank.
 *
 * @author Lucy Post and Noah Beer
 */

public class Line {
    /**creates a line object that is a Queue that stores events*/
    private Queue<Event> line;
    
    /**
     * Creates a line object and initializes the queue
     */
    public Line() {
        this.line = new LinkedList<Event>();
    }
    
    /**
     * adds a Customer to the line
     *
     * @param a   Customer that is being added
     */
    public void addCustomer(Event a) {
        this.line.offer(a);
    }
    
    /**
     * returns the Next Customer in line
     *
     * @return the next Customer
     */
    public Event getNextCustomer() {
        //returns customer and removes
        return this.line.poll();
    }
    
    /**
     * returns the size of the line
     * 
     * @return the size of the line
     */
    public int getSize() {
        return this.line.size(); 
    }
}

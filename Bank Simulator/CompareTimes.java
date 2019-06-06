import java.util.Comparator;
/**
 * CompareTimes implements Comparator and imports java.util.Comparator. We use CompareTimes to compare the times between Arrival and
 * Departure Events in our Priority Queue.
 *
 * @author Noah Beer and Lucy Post
 */

public class CompareTimes implements Comparator<Event>{
    
    /**
     * This compares two events and figures out which event should take place first in our BankSimulation
     *
     * @return compTimes returns which Event should execute first
     */
    public int compare(Event a, Event b) {
        int compTimes;
        compTimes = a.getComparableTime() - b.getComparableTime();
        if(compTimes == 0) {
            compTimes = a.getEventType() - b.getEventType();
            
        }
        return compTimes;
    }
}

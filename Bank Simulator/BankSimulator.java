import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * @author Lucy Post and Noah Beer
 *
 */
/*
public class BankSimulator {
	
	public static void main(String[] args) {
		runBank();
	}
	
	public static void runBank() {
	//FIRST STEP: SET UP BANK BEFORE IT OPENS
		Comparator<Event> comparator = new CompareTimes(); 
		Line driveUp = null;
		int clock = 0; //global clock 
		//priority queue that holds events
		PriorityQueue<Event> eventQueue = new PriorityQueue<Event>(10,comparator);
		PriorityQueue<Event> driveUpQueue = new PriorityQueue<Event> (10, comparator);
		
		//TESTER- IT WORKS
		/*
		eventList.add(new Arrival(420,36));
		eventList.add(new Arrival(320,36));
		eventList.add(new Departure(500));
		while(eventList.size() != 0) {
			System.out.println(eventList.remove().getComparableTime());
		}
		
		
		System.out.println("How many Tellers? 3-5");
		Scanner scan = new Scanner(System.in);
		int amountTellers = scan.nextInt();
		System.out.println("You chose to have " + amountTellers);
		
		//check that the user has entered a valid integer
		while (amountTellers != (int)amountTellers || amountTellers < 0) {
			System.out.println("ERROR: Please enter an amount of tellers between 3-5");
			amountTellers = scan.nextInt();
		}
		
		//create Teller array based on how many Tellers the user enters
		Teller[] tellerArr = new Teller[amountTellers];
		for(int i = 0; i < amountTellers; i++) {
			tellerArr[i] = new Teller("teller" + (i+1));
			System.out.println(tellerArr[i].getName());
		}
		
		//ask if user wants to have a drive-up window
		System.out.println("Would you like to have a drive up window? Enter 1 for yes. Enter 2 for no.");
		int driveUpAnswer = scan.nextInt();
		
		//invalid answer
		while (driveUpAnswer != 1 && driveUpAnswer != 2) {
			System.out.println("ERROR: Please enter 1 for yes or 2 for no.");
			amountTellers = scan.nextInt();
		}
		//if yes, create a driveUp Line object
		if(driveUpAnswer == 1) {
			driveUp = new Line();
		}
		
		//create file scanner to read in lines of data
		// Create a file-selection dialog object
        JFileChooser chooser = new JFileChooser();
        
        try {   
            // Display the dialog, and wait for return value.  If they cancel
            // out of the selection, throw an error -- no file to read
            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
                throw new Error("Input file not selected");
            
            // Grab the selected File info
            File inFile = chooser.getSelectedFile();
            
            // Create a scanner, and attach it to the file.  Loop through
            // line at a time and print the contents to the screen.
            Scanner fileScanner = new Scanner(inFile);
            
            //SECOND STEP: OPEN BANK
            while(fileScanner.hasNext()) {//while there are still customers to arrive
    	   		Customer currCustomer = new Customer(fileScanner.nextInt(),fileScanner.nextInt(),fileScanner.nextBoolean()); //create Customer object
    	   		//System.out.println(currCustomer.getTransactionDuration());
    	   		
    	   		//create arrival event
    	   		//Event currEvent = new Arrival(currCustomer.getArrivalTime(), currCustomer.getTransactionDuration());
    	   		//add arrival event to queue
    	   		if(!currCustomer.isWalkIn() && driveUpAnswer == 1) {	//add to priority driveUpQueue
    	   			driveUpQueue.add(currEvent);
    	   		}
    	   		else {	//add to priority eventQueue
    	   			eventQueue.add(currEvent);
    	   		}
    	   		
    	   		
    	   		//WORLD CLOCK UPDATE (CHECK EVENT QUEUE AND ARRIVAL TIME OF CURRCUSTOMER AND SET)
    	   		clock = eventQueue.peek().getComparableTime();
    	   		
    	   		//SORT CUSTOMERS INTO APPROPRIATE LINE
    	   		if(!currCustomer.isWalkIn() && driveUpAnswer == 1) {	//if they are drive-up AND user wants a drive up window
    	   			driveUp.addCustomer(driveUpQueue.poll());	//add customer event to drive-up queue
    	   		}
    	   		else {	//they are a walk-in customer
    	   			findShortestLine(tellerArr).addCustomerToTellerLine(eventQueue.poll());
    	   		}
    	   		
    	   		
    	   	
    	   		
            	}
        }
        catch (FileNotFoundException e) {
            System.err.println("Data file not found.");
        } catch (Exception e) {
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }
	
      

	}
	
	private static Teller findShortestLine(Teller[] tellerArr) {
		Teller shortest;
		shortest = tellerArr[0]; 
		for(int i = 1; i< tellerArr.length; i++) {
			if(shortest.getTellerLineLength() > tellerArr[i].getTellerLineLength()) {
				shortest = tellerArr[i];
			}
		}
		return shortest;
	}
	
}
*/

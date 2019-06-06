import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
//import java.util.LinkedList;
import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * @author Lucy Post and Noah Beer
 *
 */
public class BankSimulatorV2 {

	private boolean isATellerAtDriveUp;

	public BankSimulatorV2() {
		isATellerAtDriveUp = false;
	}

	public static void main(String[] args) {
		runBank();
	}

	@SuppressWarnings("resource")
	public static void runBank() {
		// FIRST STEP: SET UP BANK BEFORE IT OPENS
		Comparator<Event> comparator = new CompareTimes();
		Line driveUp = null;
		int clock = 0; // global clock
		int i = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		// priority queue that holds events
		PriorityQueue<Event> eventQueue = new PriorityQueue<Event>(10, comparator);

		// TESTER- IT WORKS
		/*
		 * eventList.add(new Arrival(420,36)); eventList.add(new Arrival(320,36));
		 * eventList.add(new Departure(500)); while(eventList.size() != 0) {
		 * System.out.println(eventList.remove().getComparableTime()); }
		 */

		System.out.println("How many Tellers? 3-5");
		Scanner scan = new Scanner(System.in);
		int amountTellers = scan.nextInt();
		System.out.println("You chose to have " + amountTellers);

		// check that the user has entered a valid integer
		while (amountTellers != (int) amountTellers || amountTellers < 0 || amountTellers > 10) {
			System.out.println("ERROR: Please enter an amount of tellers between 0 and 10");
			amountTellers = scan.nextInt();
		}

		// create Teller array based on how many Tellers the user enters
		Teller[] tellerArr = new Teller[amountTellers];
		for (int j = 0; j < amountTellers; j++) {
			tellerArr[j] = new Teller("teller" + (j + 1));
			System.out.println(tellerArr[j].getName());
		}

		// ask if user wants to have a drive-up window
		System.out.println("Would you like to have a drive up window? Enter 1 for yes. Enter 2 for no.");
		int driveUpAnswer = scan.nextInt();

		// invalid answer
		while (driveUpAnswer != 1 && driveUpAnswer != 2) {
			System.out.println("ERROR: Please enter 1 for yes or 2 for no.");
			amountTellers = scan.nextInt();
		}
		// if yes, create a driveUp Line object
		if (driveUpAnswer == 1) {
			driveUp = new Line();
		}

		// create file scanner to read in lines of data
		// Create a file-selection dialog object
		JFileChooser chooser = new JFileChooser();

		try {
			// Display the dialog, and wait for return value. If they cancel
			// out of the selection, throw an error -- no file to read
			if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
				throw new Error("Input file not selected");

			// Grab the selected File info
			File inFile = chooser.getSelectedFile();

			// Create a scanner, and attach it to the file. Loop through
			// line at a time and print the contents to the screen.
			Scanner fileScanner = new Scanner(inFile);

			// SECOND STEP: READ IN ALL EACH LINE AND CREATE CUSTOMER OBJECTS TO PUT IN
			// LINKEDLIST
			while (fileScanner.hasNext()) {// while there are still customers to arrive

				// Create Customer objects and add them to a Customer LinkedList
				Customer currCustomer = new Customer("Customer #" + (i + 1), fileScanner.nextInt(),
						fileScanner.nextInt(), fileScanner.nextBoolean()); // create Customer object
				// System.out.println(currCustomer.getTransactionDuration());
				customerList.add(currCustomer);

				Event arrivalEvent = new Arrival(currCustomer.getArrivalTime(), currCustomer.getTransactionDuration(),
						currCustomer.isWalkIn(), i);
				//System.out.println(arrivalEvent.getEventType());
				eventQueue.offer(arrivalEvent);

				i = i + 1;
			}

		} catch (FileNotFoundException e) {
			System.err.println("Data file not found.");
		} catch (Exception e) {
			System.err.println("A mysterious error occurred.");
			e.printStackTrace(System.err);
		}
		scan.close();

		Event currEvent;
		int indexOfTellerWithShortestLine;
		// WHILE THERE ARE CUSTOMERS LEFT TO SERVE
		while (!eventQueue.isEmpty()) { // NEED TO MAKE SURE THIS IS ALWAYS THE CASE
			currEvent = eventQueue.poll();
			clock = currEvent.getComparableTime();
			System.out.println("--- Time: " + clock + "---");
			// check whether a departure or arrival event
			if (currEvent.getEventType() == 'D') { // currEvent is a Departure event

			} 
			else { // currEvent is Arrival event
				// place event is appropriate queue
				if(((Arrival) currEvent).getIsWalkIn()) {	//if arrival type is a walk-in
					System.out.println(customerList.get(currEvent.getCustomerPosition()).getCustomerName() + " arrived in the bank.");
					//find teller with shortest line
					indexOfTellerWithShortestLine = findShortestLine(tellerArr);
					tellerArr[indexOfTellerWithShortestLine].addCustomerToTellerLine(currEvent);
					//is the teller busy? start wait time
					if(tellerArr[indexOfTellerWithShortestLine].getIsBusy()) {
						customerList.get(currEvent.getCustomerPosition()).setIsWaiting(true);
					}
				}
				
				else {	//customer is arrives at drive-up 
					System.out.println(customerList.get(currEvent.getCustomerPosition()).getCustomerName() + " arrived at the drive-up.");
					driveUp.addCustomer(currEvent);
				}
			}
			
			//Set Tellers up

		}

	}

	private static int findShortestLine(Teller[] tellerArr) {
		int shortest;
		shortest = 0;
		boolean isSameSize = false;
		//Random rand;
		//rand = new Random();
		for (int i = 1; i < tellerArr.length; i++) {
			if (tellerArr[shortest].getTellerLineLength() > tellerArr[i].getTellerLineLength()) {
				shortest = i;
			}
			
			// FIGURE OUT HOW TO RANDOMLY ASSIGN LINE IF LENGTHS ARE EQUAL
		}
		return shortest;
	}

}

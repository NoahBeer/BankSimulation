import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
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
public class BankSimulatorV3 {

	private static boolean isATellerAtDriveUp;

	public BankSimulatorV3() {
		isATellerAtDriveUp = false;
	}

	public static void main(String[] args) {
		LinkedList<Integer> test = new LinkedList<Integer>();
		System.out.println(test.size());
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

				Customer currCustomer;

				currCustomer = new Customer("Customer #" + (i + 1), fileScanner.nextInt(), fileScanner.nextInt(),
						fileScanner.nextBoolean()); // create Customer object

				if (driveUpAnswer == 2) { // if user doesn't want a driveUp, force all customers to be WalkIns
					currCustomer.setIsWalkIn();
				}
				// System.out.println(currCustomer.getTransactionDuration());
				customerList.add(currCustomer);

				Event arrivalEvent = new Arrival(currCustomer.getArrivalTime(), currCustomer.getTransactionDuration(),
						currCustomer.isWalkIn(), i);
				// System.out.println(arrivalEvent.getEventType());
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
		Departure depart;
		Arrival nxtArrivalInLine;
		Teller currTeller;
		// WHILE THERE ARE CUSTOMERS LEFT TO SERVE
		while (!eventQueue.isEmpty()) { // NEED TO MAKE SURE THIS IS ALWAYS THE CASE
			currEvent = eventQueue.poll();
			clock = currEvent.getComparableTime();
			System.out.println("--- Time: " + clock + "---");
			// check whether a departure or arrival event
			if (currEvent.getEventType() == 'D') { // currEvent is a Departure event
				currTeller = ((Departure) currEvent).getTeller();
				currTeller.incrementNumCustomersHelped();
				System.out.println(customerList.get(currEvent.getCustomerPosition()).getCustomerName()
						+ " finished their transaction with " + ((Departure) currEvent).getTeller().getName());
				// if there is someone in the driveUp line and the teller who finished is
				// already there OR there is someone in the driveUp line and there isn't a
				// teller already there
				if ((driveUp.getSize() > 0 && currTeller.getIsAtDriveUp())
						|| (driveUp.getSize() > 0 && !isATellerAtDriveUp)) {
					nxtArrivalInLine = (Arrival) driveUp.getNextCustomer(); // grab next person in line
					// START TRANSACTION BY CREATING DEPARTURE EVENT
					System.out.println(
							customerList.get(((Arrival) nxtArrivalInLine).getCustomerPosition()).getCustomerName()
									+ " is starting their transaction.");
					
					
					depart = new Departure(clock + nxtArrivalInLine.getTransactionTime(), currTeller,
							nxtArrivalInLine.getCustomerPosition()); // create new departure event and assign to teller
					eventQueue.offer(depart);
					isATellerAtDriveUp = true;													
					currTeller.setIsAtDriveUp(true);
					currTeller.setToBusy();
				}
				// if driveUp has no one or (current teller is not at drive up and there is some
				// other teller already there)
				else if (driveUp.getSize() == 0 || (currTeller.getIsAtDriveUp() == false && isATellerAtDriveUp)) {
					nxtArrivalInLine = (Arrival) currTeller.getNextCustomer(); // pulling from the teller's own line
					if (nxtArrivalInLine == null) { // if there is no one in line, set teller to idle
						currTeller.setToIdle();
						// Track idle time
					} else {
						depart = new Departure(clock + nxtArrivalInLine.getTransactionTime(), currTeller,
								nxtArrivalInLine.getCustomerPosition());
						eventQueue.offer(depart);
						System.out.println(
								customerList.get( nxtArrivalInLine.getCustomerPosition()).getCustomerName()
										+ " is starting their transaction.");
					}
					
					//set driveUp
					currTeller.setIsAtDriveUp(false);
				}
			} 
			
			else { // CURREVENT IS AN ARRIVAL EVENT
						// place event is appropriate queue
				if (((Arrival) currEvent).getIsWalkIn()) { // if arrival type is a walk-in
					System.out.println(customerList.get(currEvent.getCustomerPosition()).getCustomerName()
							+ " arrived in the bank.");
					// find teller with shortest line
					indexOfTellerWithShortestLine = findShortestLine(tellerArr);
					//set currTeller variable
					currTeller = tellerArr[indexOfTellerWithShortestLine];
					//if currTeller has no customers in line and the current teller is not already with a customer
					if(currTeller.getTellerLineLength() == 0 &&  !currTeller.getIsBusy()) {
						//create departure event, offer it to event queue, and change teller status
						Departure dep = new Departure(clock + ((Arrival)currEvent).getTransactionTime(), currTeller, ((Arrival)currEvent).getCustomerPosition());
						System.out.println(
								customerList.get(((Arrival) currEvent).getCustomerPosition()).getCustomerName()
										+ " is starting their transaction.");
						eventQueue.offer(dep); 
						currTeller.setToBusy();
					}
					else {	//else there are people waiting in the tellers line and/or the teller is busy
						//offer arrival event to the tellers standing queue
						currTeller.addCustomerToTellerLine(currEvent);
						//start customers wait time
						customerList.get(currEvent.getCustomerPosition()).setStartWaitTime(clock);
						System.out.println(customerList.get(currEvent.getCustomerPosition()).getCustomerName() + " is waiting in line.");
					}
					//tellerArr[indexOfTellerWithShortestLine].addCustomerToTellerLine(currEvent);
					// is the teller busy? start wait time
					/*
					if (tellerArr[indexOfTellerWithShortestLine].getIsBusy()) {
						customerList.get(currEvent.getCustomerPosition()).setIsWaiting(true);
					}
					*/
				}
					
				else { // customer is arrives at drive-up
					System.out.println(customerList.get(currEvent.getCustomerPosition()).getCustomerName()
							+ " arrived at the drive-up.");
					int idleTellerIndex = findIdleTeller(tellerArr);
					if(driveUp.getSize() == 0 && !isATellerAtDriveUp && idleTellerIndex != -1) {
						currTeller = tellerArr[idleTellerIndex];
						Departure dep = new Departure(clock + ((Arrival)currEvent).getTransactionTime(), currTeller, ((Arrival)currEvent).getCustomerPosition());
						System.out.println(
								customerList.get(((Arrival) currEvent).getCustomerPosition()).getCustomerName()
										+ " is starting their transaction.");
						eventQueue.offer(dep);
						currTeller.setToBusy();
						currTeller.setIsAtDriveUp(true);
						isATellerAtDriveUp = true;
					}
					
					else {
						driveUp.addCustomer(currEvent);
						customerList.get(currEvent.getCustomerPosition()).setStartWaitTime(clock);
					}
				}
			}

			// Set Tellers up

		}

	}

	private static int findShortestLine(Teller[] tellerArr) {
		int shortest;
		shortest = 0;
		boolean isSameSize = false;
		// Random rand;
		// rand = new Random();
		for (int i = 1; i < tellerArr.length; i++) {
			if (tellerArr[shortest].getTellerLineLength() > tellerArr[i].getTellerLineLength()) {
				shortest = i;
			}

			// FIGURE OUT HOW TO RANDOMLY ASSIGN LINE IF LENGTHS ARE EQUAL
		}
		return shortest;
	}
	
	private static int findIdleTeller(Teller[] tellerArr) {
		for (int i = 0; i < tellerArr.length; i++) {
			if(!tellerArr[i].getIsBusy()) {
				return i;
			}
		}
		return -1;
	}

}

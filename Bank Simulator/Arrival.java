
public class Arrival implements Event{
	private int arrivalTime;
	private int transactionTime;
	private int position;
	private boolean isWalkIn;
	
	public Arrival(int arrivalTime, int transactionTime, boolean isWalkIn, int position) {
		this.arrivalTime = arrivalTime;
		this.transactionTime = transactionTime; 
		this.position = position;
		this.isWalkIn = isWalkIn;
	}
	
	public int getComparableTime() {
		return this.arrivalTime;
	}
	
	public int getTransactionTime() {
		return this.transactionTime;
	}
	
	public int getCustomerPosition() {
		return this.position;
	}
	
	public boolean getIsWalkIn() {
		return this.isWalkIn;
	}
	
	public int getEventType() {
		return 1;
	}
}

import java.util.Random;
/**
 * Employee class
 */
public class Employee extends Thread {
	int arrivalTime = -1;
	int lunchLength = -1;
	private Random gen = new Random();
	
	public void run(){
		//do stuff
	}
	
	/**
	 * Employees goes to lunch
	 * returns int minutes of how long they were out (30 - 60 min)
	 */
	private int lunch(){
		int lunchDuration = gen.nextInt(30) + 30;
		//wait or do no work for lunch duration
		return lunchDuration;
	}
}
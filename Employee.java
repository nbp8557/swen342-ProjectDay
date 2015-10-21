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
	/*
	 *  anypoint during the day a team member may ask a question 
	 *  of his or her team lead. 
	 *  There is an 50% chance that the team lead can answer the question,
	 *   at which time the lead and the team member return to work.
	 */
	private void askQuestion(){
		//Ask the team lead the question
	}
	
}
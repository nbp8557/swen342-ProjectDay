import java.util.Random;
/**
 * Employee class
 */
public class Employee extends Thread {
	int arrivalTime = -1;
	int lunchLength = -1;
	int teamNumber;
	int memberNumber;

	private Random gen = new Random();
	private TeamLead teamLead = null;
	
	public Employee(TeamLead lead, int teamNum, int memNum){
		teamLead = lead;
		teamNumber = teamNum;
		memberNumber = memNum;
	}
	
	public void run(){
		work();
	}

	public void work(){
		while(Clock.getCurrentTime() < Clock.END_OF_DAY){
			int currentTime = Clock.getCurrentTime();
			if (currentTime >= Clock.BEGIN_LEAVING && currentTime - lunchLength - arrivalTime >= Clock.WORKDAY){
				break;
			} else if(currentTime >= Clock.LUNCH && lunchLength == -1){
				System.out.println(getNameStr() + " went to lunch.");
				lunchLength = lunch();
				sleep(Clock.toRealtime(lunchLength));
			} else {
				boolean hasQuestion = gen.nextInt(1000) == 1;
				if (hasQuestion) {
					System.out.println(Clock.getTimeStr(currentTime) + " " + getNameStr() + " asks a question.");
					askQuestion();
				}
			}
		}
		System.out.println(Clock.getTimeStr(currentTime) + " " + getNameStr() + " went home.");
	}
	
	/**
	 * Employees goes to lunch
	 * returns int minutes of how long they were out (30 - 60 min)
	 */
	private int lunch(){
		int lunchDuration = arrivalTime + Clock.HALF_HOUR;
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
		boolean answered = teamLead.AnswerQuestion(this);
	// 	if(answered){
	// 		System.out.println("Answered!");
	// 	} else {
	// 		//ask manager, probably handled by the teamlead
	// 	}
	}

	private String getNameStr(){
		return "Developer " + String.valueOf(teamNumber) + " - " + String.valueOf(memberNumber);
	}
	
}
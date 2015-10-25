import java.util.Random;
/**
 * Employee class
 */
public class Employee extends Thread {
	private int arrivalTime = -1;
	private int lunchLength = -1;
	int teamNumber;
	int memberNumber;

	private Random gen = new Random();
	private TeamLead teamLead = null;
	private Clock clock;
	
	private int lunch_time, meeting_time, question_time, work_time;
	
	public Employee(TeamLead lead, int teamNum, int memNum, Clock clck){
		teamLead = lead;
		teamNumber = teamNum;
		memberNumber = memNum;
		clock = clck;
	}
	
	
	public void makeWait(){
		int time_start = clock.getCurrentTime();
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		meeting_time+=(clock.getCurrentTime() - time_start);
	}
	//just call work
	public void run(){
		work();
	}

	/**
	 * Loop called in the run method
	 * represents the workday
	 * Starts events at their time
	 */
	public void work(){
		arrivalTime = clock.getCurrentTime();
		System.out.println(Clock.getTimeStr(arrivalTime) + " " + getNameStr() + " arrives at work.");
		while(clock.getCurrentTime() < Clock.END_OF_DAY){ //end loop when the day is over
			int currentTime = clock.getCurrentTime();
			if (currentTime >= Clock.BEGIN_LEAVING && currentTime - lunchLength - arrivalTime >= Clock.WORKDAY){ //if 430 and have worked 8 hrs
				break;
			} else if(currentTime >= Clock.LUNCH && lunchLength == -1){ //if lunchtime and havent been to lunch
				System.out.println(Clock.getTimeStr(currentTime) + " " + getNameStr() + " went to lunch.");
				lunchLength = lunch();
				try{
					sleep(Clock.toRealtime(lunchLength)); //out to lunch
				} catch (InterruptedException e){}
				System.out.println(Clock.getTimeStr(currentTime) + " " + getNameStr() + " returned from lunch.");
				lunch_time = clock.getCurrentTime() - currentTime;
			} else {
				boolean hasQuestion = gen.nextInt(100000) == 1; //do i have a question at this time
				if (hasQuestion) {
					System.out.println(Clock.getTimeStr(currentTime) + " " + getNameStr() + " asks a question.");
					askQuestion();
					question_time += clock.getCurrentTime() - currentTime;
				}
			}
		}
		work_time = 4800 - meeting_time - lunch_time - question_time;
		System.out.println(Clock.getTimeStr(clock.getCurrentTime()) + " " + getNameStr() + " went home.");
	}
	
	/**
	 * Employees goes to lunch
	 * returns int minutes of how long they were out (30 - 60 min)
	 */
	protected int lunch(){
		int lunchDuration = gen.nextInt(arrivalTime) + Clock.HALF_HOUR;
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
		teamLead.ReceiveQuestion(this);
	}

	/**
	 * returns Name String of the Employee as "Developer TN - MN"
	 */
	private String getNameStr(){
		return "Developer " + String.valueOf(teamNumber) + " - " + String.valueOf(memberNumber);
	}
	
}
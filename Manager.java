import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class Manager extends Thread{
	//Will have a collection of team leads 
	ArrayList<TeamLead> TeamLeads;
	//Will have a queue for questions
	ArrayBlockingQueue<TeamLead> Questions;
	Clock clock;
	
	public Manager(Clock inputClock){
		TeamLeads = new ArrayList<TeamLead>();
		Questions = new ArrayBlockingQueue<TeamLead> (1000);
		clock = inputClock;
	}
	
	//used to add Team Leads to the managers collection
	public boolean AddTeamLead(TeamLead leader){
		return TeamLeads.add(leader);		
	}
	
	
	@SuppressWarnings("static-access")
	@ Override
	public void run(){
		//start the time
		clock.startTime();
		
		//The manager has arrived at work 
		this.ArriveAtWork();
		
		while(clock.getCurrentTime() <= Clock.END_OF_DAY){			
			
			if(clock.getCurrentTime() >= Clock.EXEC1 &&  clock.getCurrentTime() < Clock.EXEC1 + Clock.HOUR){
				System.out.println(Clock.getTimeStr(clock.getCurrentTime()) + " Manager is going to first Executive meeting");
				try {
					this.sleep(Clock.toRealtime(clock.HOUR));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			else if(clock.getCurrentTime() >= Clock.LUNCH &&  clock.getCurrentTime() < Clock.LUNCH + Clock.HOUR){
				System.out.println(Clock.getTimeStr(clock.getCurrentTime()) +" Manager is going to Lunch");
				try {
					this.sleep(Clock.toRealtime(Clock.HOUR));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			else if(clock.getCurrentTime() >= Clock.EXEC2 &&  clock.getCurrentTime() < Clock.EXEC2 + Clock.HOUR){
				System.out.println(Clock.getTimeStr(clock.getCurrentTime()) + " Manager is going to the second Executive meeting");
				try {
					this.sleep(Clock.toRealtime(Clock.HOUR));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			else if(clock.getCurrentTime() >= Clock.STANDUP && clock.getCurrentTime() < Clock.STANDUP + Clock.QUARTER_HOUR){
				System.out.println(Clock.getTimeStr(clock.getCurrentTime()) + " Manager is going to the End of Day Standup");
				EndOfDayMeeting();
			}else if(!Questions.isEmpty()){
				System.out.println(Clock.getTimeStr(clock.getCurrentTime()) + " Manager is answering a question");
				AnswerQuestion();
			}
			
		}
		System.out.println("Manager has left work.");		
	}
	
	/*
	 * When he arrives at 8:00 each day, the manager engages in daily
	 *  planning activites and then waits (doing administrivia) until
	 *   all the team leads arrive at his office. When all the leads have 
	 *   arrived, they knock on the manager's door and enter for their daily 
	 *   15 minute standup meeting.
	 *
	 */
	private void ArriveAtWork(){
		//The manager arrives at 8		
		//He then waits until all of the team leads arrive at his office
		while(! TeamLeadsHere()){
			
		}
		//Tell all team leads to wait for during the meeting
		for(TeamLead lead : TeamLeads){
			synchronized(lead){
				try {
					lead.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		// The meeting will last 15 minutes
		try {
			Thread.sleep(Clock.toRealtime(Clock.QUARTER_HOUR));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * At 4:00 every day leaders and members of all teams start assembling
	 *  in the conference room for a project status update. It is expected
	 *  that members will finish arriving by 4:15, allowing 15 minutes
	 *  to clean up any work in progress. When all members have gathered, 
	 *  the manager spends 15 minutes discussing the project status.
	 */
	private void EndOfDayMeeting(){

		//Goes to the conference room and waits for everyone to be there
			//Call team lead's endOfDayMeeting
				//when everyone is there sleep 15 minutes
		
		while(! TeamLeadAndTeamsHere()){
			try {
				sleep(Clock.toRealtime(1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Now make the entire company wait for the meeting to conclude
		for(TeamLead lead : TeamLeads){
			//Team lead is now blocked by this meeting
			try {
				lead.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//Now every employee is blocked by this meeting
			for( Employee employee : lead.getDevs()){
				try {
					employee.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//simulate the end of day meeting length
		try {
			sleep(Clock.toRealtime(Clock.QUARTER_HOUR));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Meeting has concluded
		notifyAll();
		//Meeting has concluded
		
		/*for(TeamLead lead : TeamLeads){
			//Team lead is free to do whatever
			lead.notify();
			lead.getD
			//Now every employee is free to do whatever
			for( Employee employee : lead.getDevs()){
				employee.notify();
			}
		}*/
		
	}
	
	//returns true if the entire company is here available
	//otherwise returns false if anyone one is busy
	private boolean TeamLeadAndTeamsHere(){
		for(TeamLead lead : TeamLeads){
			//Team lead is busy
			if(lead.getState() == Thread.State.TIMED_WAITING){
				return false;
			}else{
				for( Employee employee : lead.getDevs()){
					//Team member is busy
					if(employee.getState() == Thread.State.TIMED_WAITING ){
						return false;
					}
				}
			}
		}
		//No one is busy start meeting 
		return true;
	}
	
	//returns true if all Team Leads are available
	//returns false if a single Team Lead is not available
	private boolean TeamLeadsHere(){
		//loop through team leads
		boolean temp;
		for(TeamLead lead : TeamLeads){			
			//the lead thread is running
			temp = lead.isAlive();
			
			if(! temp){
				return false;
			}
		}
		return true;
	}
	
	//Team lead will answer a question
	private void AnswerQuestion(){
		try {
			//simulate the time to answer a question
			this.sleep(Clock.toRealtime(Clock.TEN_MINUTES));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//notify the Team Lead asking the question to stop blocking
		Questions.remove().notify();
	}
	
	//Team lead asks a question which is added to the priority queue
	public synchronized void AskQuestion(TeamLead teamLead){

		//tell the team Lead to wait If the manager is not already busy
		try {
			synchronized(teamLead){
				teamLead.wait();
			}			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Add question to the queue
		Questions.add(teamLead);	
	}
	
}

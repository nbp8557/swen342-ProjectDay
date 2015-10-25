import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;


public class Manager extends Thread{
	//Will have a collection of team leads 
	ArrayList<TeamLead> TeamLeads = new ArrayList<TeamLead>();
	//Will have a queue for questions
	PriorityBlockingQueue<TeamLead> Questions = new PriorityBlockingQueue<TeamLead> ();
	
	public Manager(){
	}
	
	@ Override
	public void run(){
		//The manager has arrived at work 
		this.ArriveAtWork();
		
		while(Clock.getCurrentTime() <= Clock.END_OF_DAY){			
			
			if(Clock.getCurrentTime() >= Clock.EXEC1 &&  Clock.getCurrentTime() < Clock.EXEC1 + Clock.hour){
				System.out.println("Manager is going to first Executive meeting");
				this.sleep(Clock.toRealtime(Clock.HOUR));
			}	
			else if(Clock.getCurrentTime() >= Clock.LUNCH &&  Clock.getCurrentTime() < Clock.LUNCH + Clock.hour){
				System.out.println("Manager is going to Lunch");
				this.sleep(Clock.toRealtime(Clock.HOUR));
			}			
			else if(Clock.getCurrentTime() >= Clock.EXEC2 &&  Clock.getCurrentTime() < Clock.EXEC2 + Clock.hour){
				System.out.println("Manager is going to the second Executive meeting");
				this.sleep(Clock.toRealtime(Clock.HOUR));
			}			
			else if(Clock.getCurrentTime() >= Clock.STANDUP && Clock.getCurrentTime() < Clock.STANDUP + Clock.QUARTER_HOUR){
				System.out.println("Manager is going to the End of Day Standup");
				EndOfDayMeeting();
			}else if(!Questions.isEmpty()){
				System.out.println("Manager is answering a question");
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
				
			//When they have all arrived the team leads and manager will
			//	wait 15 minutes	
			this.sleep(Clock.toRealtime(Clock.QUARTER_HOUR));
	}
	
	/*
	 * At 4:00 every day leaders and members of all teams start assembling
	 *  in the conference room for a project status update. It is expected
	 *  that members will finish arriving by 4:15, allowing 15 minutes
	 *  to clean up any work in progress. When all members have gathered, 
	 *  the manager spends 15 minutes discussing the project status.
	 */
	public void EndOfDayMeeting(){
		//Goes to the conference room and waits for everyone to be there
			//Call team lead's endOfDayMeeting
				//when everyone is there sleep 15 minutes
			this.sleep(Clock.toRealtime(Clock.QUARTER_HOUR));		
	}
	
	//Team lead will answer a question
	private boolean AnswerQuestion(){
		this.sleep(Clock.toRealtime(Clock.TEN_MINUTES));
		Questions.remove().notify();
	}
	
	//Team lead asks a question which is added to the priority queue
	public void AskQuestion(TeamLead teamLead){
		//Add question to the queue
		Questions.add(teamLead);	
	}
	
}

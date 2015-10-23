
public class Manager extends Thread{
	//Will have a collection of team leads 
	//Will have a queue for questions
	//some sort of state "busy" variable that we will lock
	
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
				//wait 15 minutes
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
		
	}
	
	private void AnswerQuestion(){
		//Add question to the queue
		
		// If he does not have a meeting or lunch then answer a question
			//sleep 10 minutes then return 
		//otherwise wait until after meeting	
		
		
	}
	
}

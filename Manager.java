
public class Manager extends Thread{
	//Will have a collection of team leads 
	//Will have a queue for questions
	
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
			//IfBusy check
			//sleep 10 minutes then return 
		//otherwise wait until after meeting	
		
		
	}
	
	
	/*
	 * The manager has two daily executive meetings, each lasting one hour,
	 *  one from 10:00 to 11:00 and one from 2:00 to 3:00. In addition, 
	 *  the manager eats lunch for one hour starting as close to from 12:00 
	 *  as possible to 1:00. If in the middle of answering a question when 
	 *  a meeting or lunch begins, the manager finishes answering the question 
	 *  and then goes to the meeting or lunch. Any other teams with questions 
	 *  simply wait for the manager to return.
	 */
	private boolean IfBusy(){
		//if the manager's state busy is true
			//then return true;
		//otherwise 
			//set the manager's busy state to true
			//and return false
		return false;
	}
	
	
}

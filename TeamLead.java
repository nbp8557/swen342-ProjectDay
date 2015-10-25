import java.util.concurrent.PriorityBlockingQueue;


public class TeamLead extends Employee{
	public String name;
	//Will have a collection of developers 
	public Employee[] teamMembers;
	private static TeamLead temp;
	private Clock clock;
	private PriorityBlockingQueue<Employee> questions = new PriorityBlockingQueue<Employee>();
	
	
	public TeamLead(String name){
		super(temp);

		this.name = name;
	}
	
	//used to add Team Leads to the managers collection
	public boolean AddEmployee(Employee dev){
		return teamMembers.add(dev);		
	}
	
	/*
	 * After the meeting, the team leads wait for all the members 
	 * of their team to arrive. When all members of a team are present,
	 *  the team lead waits for the one-and-only team conference room 
	 *  to become available, enters the room with all team members, 
	 *  and holds a holds a team-based standup meeting for 15 minutes.
	 */
	public void MorningStandup(){
		//Wait for all developers to arrive
			//check for conference room to be available
			//If its not wait until it is
		
			//otherwise have a 15 minute meeting
				//sleep 15
		
		ConferenceRoom conf = new ConferenceRoom();
		
		while(true){
			if (IsTeamIn()){
				if (conf.isAvailable()){
					try {
						System.out.println(name+"'s team meets in the conference room.");
						sleep(clock.toSimulatedMin(1500));
						System.out.println(name+"'s team leaves the conference room.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
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
			//Call employee's endOfDayMeeting
				//when everyone is there sleep 15 minutes
		
		
	}
	
	/*
	 * If the team lead is the one with the question, or if the 
	 * question is one of the 50% the lead cannot answer, 
	 * the lead and the member asking the question go 
	 * to the project manager's office to have the question answered.
	 *  When let into the manager's office, the team lead asks the question,
	 *   the manager provides the answer, and the team lead (and possibly the 
	 *   member with the question) return to work. Of course, the team may
	 *   have to wait for one or more other teams to have their questions'
	 *   answered first. We will also assume it always takes 10 minutes to ask 
	 *   and answer a question.
	 */
	
	public boolean AnswerQuestion(){
		String q = questions.remove();
		boolean isAnswered = Math.random()>0.5;
		//50% chance that we return true
		if (!isAnswered){
			try {
				sleep(clock.toSimulatedMin(1000)); //go to the managers office
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			return true;
		}
		//If false
			//Wait 10 minutes
			//Go to manager's office
			//wait in line to ask the question
			//after asking the question then return true
		
		return false;
		
	}
	
	
	private boolean IsTeamIn(){
		boolean teamAllHere = true;
		for(Employee dev : teamMembers){
			if(! dev.isAlive()){
				return false;
			}
		}
		return true;
	}
	
	public void ReceiveQuestion(Employee e){
		questions.add(e);
	}
	
	public void run(){
		while(true){
			if(clock.timeOfDay == clock.END_OF_DAY){
				return;
			}
			else if(false){ // check other meeting times
				return;
			}
			else{
				AnswerQuestion();
			}
		}
	}
}

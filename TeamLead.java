import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;


public class TeamLead extends Employee{
	private int lunchTime;
	private int arrivalTime;
	private String name;
	private ArrayList<Employee> teamMembers;
	private static TeamLead temp;
	private Clock clock;
	private Manager manager;
	private PriorityBlockingQueue<Employee> questions = new PriorityBlockingQueue<Employee>();
	private int lunch_time = 0, meeting_time = 0, question_time = 0, work_time = 0;
	
	public TeamLead(String name, int teamNum, Clock clck, Manager man){
		super(temp, teamNum, 1, clck);
		this.manager = man;
		this.name = name;
		this.teamMembers = new ArrayList<Employee>();
	}
	/*
	 * After the meeting, the team leads wait for all the members 
	 * of their team to arrive. When all members of a team are present,
	 *  the team lead waits for the one-and-only team conference room 
	 *  to become available, enters the room with all team members, 
	 *  and holds a holds a team-based standup meeting for 15 minutes.
	 */
	public void TeamMorningStandup(){
		//Wait for all developers to arrive
			//check for conference room to be available
			//If its not wait until it is
		
			//otherwise have a 15 minute meeting
				//sleep 15
		
		ConferenceRoom conf = new ConferenceRoom();
		
		while(true)
		{
			if (IsTeamIn())
			{
				synchronized(conf){
					try
					{
						System.out.println(clock.getTimeStr(clock.getCurrentTime()) + " " + name + "'s team meets in the conference room.");
						sleep(Clock.toRealtime(15));
						for(Employee dev : teamMembers){
							dev.sleep(Clock.toRealtime(15));
						}
						System.out.println(clock.getTimeStr(clock.getCurrentTime())+" "+ name+"'s team leaves the conference room.");
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
		Employee q = questions.remove();
		boolean isAnswered = Math.random()>0.5;
		//50% chance that we return true
		if (!isAnswered){
			try {
				wait();
				q.wait();
				manager.AskQuestion(this);
				q.notify();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			questions.remove().notify();
			return true;
		}
		//If false
			//Wait 10 minutes
			//Go to manager's office
			//wait in line to ask the question
			//after asking the question then return true
		
		return false;
		
	}
	
	public ArrayList<Employee> getDevs(){
		return this.teamMembers;
	}
	
	
	private boolean IsTeamIn(){
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
		while(clock.getCurrentTime() < clock.END_OF_DAY){
			int currentTime = clock.getCurrentTime();
			
			if (currentTime >= Clock.BEGIN_LEAVING && currentTime - lunchTime - arrivalTime >= Clock.WORKDAY){
				break;
			} else if(currentTime >= Clock.LUNCH && lunchTime == -1){
				System.out.println(Clock.getTimeStr(currentTime) + " " + name + " went to lunch.");
				lunchTime = super.lunch();
				lunch_time  = clock.getCurrentTime() - currentTime;
				try{
					sleep(Clock.toRealtime(lunchTime));
				} catch (InterruptedException e){}
			
			}
			else if(currentTime > clock.STANDUP){
				TeamMorningStandup();
				meeting_time += clock.getCurrentTime() - currentTime;
			}
			
			else{
				AnswerQuestion();
				question_time += clock.getCurrentTime() - currentTime;
			}
		}
		System.out.println(Clock.getTimeStr(clock.getCurrentTime()) + " " + name + " went home.");
		work_time = 4800 - question_time - meeting_time - lunch_time;
	}
	public boolean AddEmployee(Employee dev) {
		return this.teamMembers.add(dev);		
	}
}

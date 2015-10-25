import java.util.ArrayList;

public class Main{
	
	public static void main(String[] args) {
		//This will start the application
		
		//allocate all of the employee, manager objects
		Clock theTime = new Clock();
		
		TeamLead teamLead1 = new TeamLead("Team Lead 1-1");
		TeamLead teamLead2 = new TeamLead("Team Lead 2-1");
		TeamLead teamLead3 = new TeamLead("Team Lead 3-1");

		Employee employee4 = new Employee(teamLead1, 1, 2, theTime);
		Employee employee5 = new Employee(teamLead1, 1, 3, theTime);
		Employee employee6 = new Employee(teamLead1, 1, 4, theTime);
		Employee employee7 = new Employee(teamLead2, 2, 2, theTime);
		Employee employee8 = new Employee(teamLead2, 2, 3, theTime);
		Employee employee9 = new Employee(teamLead2, 2, 4, theTime);
		Employee employee10 = new Employee(teamLead2, 3, 2, theTime);
		Employee employee11 = new Employee(teamLead2, 3, 3, theTime);
		Employee employee12 = new Employee(teamLead2, 3, 4, theTime);
		
		Manager manager = new Manager(theTime);
		manager.addTeamLead(teamLead1);
		manager.addTeamLead(teamLead2);
		manager.addTeamLead(teamLead3);
		
	
		
		
		
		//Start manager at 8 am
		
		//Utilize a latch a start the employees all together starting at
		//what we consider 8am
		
		
		// Kick off project status meeting at 4pm
		
		//Wait until after all employees have left the office, then exit the program
		
		
	}
}

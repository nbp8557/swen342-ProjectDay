
public class Main{
	
	public static void main(String[] args) {
		
		//Create the Clock which will be responsible for keeping track of time
		Clock theTime = new Clock();
		
		//Create THE Manager
		Manager Kronos = new Manager(theTime);


		TeamLead teamLead1 = new TeamLead("Team Lead 1-1", 1, theTime, Kronos);

		Employee employee4 = new Employee(teamLead1, 1, 2, theTime);
		Employee employee5 = new Employee(teamLead1, 1, 3, theTime);
		Employee employee6 = new Employee(teamLead1, 1, 4, theTime);

		//		Add employees to Team Lead's team
		teamLead1.AddEmployee(employee4);
		teamLead1.AddEmployee(employee5);
		teamLead1.AddEmployee(employee6);
//
//		//Create the team leads
//		TeamLead teamLead1 = new TeamLead("Team Lead 1-1", 1, theTime, Kronos);
//		TeamLead teamLead2 = new TeamLead("Team Lead 2-1", 2, theTime, Kronos);
//		TeamLead teamLead3 = new TeamLead("Team Lead 3-1", 3, theTime, Kronos);
//
//		//Add team leads to the managers collection
//		Kronos.AddTeamLead(teamLead1);
//		Kronos.AddTeamLead(teamLead2);
//		Kronos.AddTeamLead(teamLead3);
//
//		//Create developers for teams
//
//		Employee employee4 = new Employee(teamLead1, 1, 2, theTime);
//		Employee employee5 = new Employee(teamLead1, 1, 3, theTime);
//		Employee employee6 = new Employee(teamLead1, 1, 4, theTime);
//
//		//Add employees to Team Lead's team
//		teamLead1.AddEmployee(employee4);
//		teamLead1.AddEmployee(employee5);
//		teamLead1.AddEmployee(employee6);
//
//
//		Employee employee7 = new Employee(teamLead2, 2, 2, theTime);
//		Employee employee8 = new Employee(teamLead2, 2, 3, theTime);
//		Employee employee9 = new Employee(teamLead2, 2, 4, theTime);
//
//		//Add employees to Team Lead's team
//		teamLead2.AddEmployee(employee7);
//		teamLead2.AddEmployee(employee8);
//		teamLead2.AddEmployee(employee9);
//
//		Employee employee10 = new Employee(teamLead2, 3, 2, theTime);
//		Employee employee11 = new Employee(teamLead2, 3, 3, theTime);
//		Employee employee12 = new Employee(teamLead2, 3, 4, theTime);
//
//		//Add employees to Team Lead's team
//		teamLead3.AddEmployee(employee10);
//		teamLead3.AddEmployee(employee11);
//		teamLead3.AddEmployee(employee12);

		//start the manager
		Kronos.start();
		
		//start the team leads
		teamLead1.start();
//		teamLead2.start();
//		teamLead3.start();
		
		employee4.start();
		employee5.start();
		employee6.start();
//		employee7.start();
//		employee8.start();
//		employee9.start();
//		employee10.start();
//		employee11.start();
//		employee12.start();
		
	}
}

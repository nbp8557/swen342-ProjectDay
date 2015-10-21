
public class TeamLead extends Employee{
	public String name;
	public Employee[] teamMembers;

	//Will have a collection of developers (in our scenario only 3)
	
	public TeamLead(String name, Employee[] members){
		this.name = name;
		this.teamMembers = members;
	}
}


public class ConferenceRoom{
	private Object lock;
	
	public ConferenceRoom(){
		lock = new Object();
	}
	public Object getConferenceRoom(){
		return this.lock;
	}
	
	public boolean isAvailable(){
		//stubbed for now
		return false;
	}
}

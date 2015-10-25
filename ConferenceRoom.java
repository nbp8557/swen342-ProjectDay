public class ConferenceRoom{
	private static ConferenceRoom conf = null;
	public ConferenceRoom(){}

	public static ConferenceRoom getConferenceRoom(){
		if (conf == null) {
			conf = new ConferenceRoom();
		}
		return conf;
	}
}

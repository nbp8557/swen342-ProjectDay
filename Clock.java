import java.util.Timer;
import java.util.TimerTask;
public class Clock {
	//event times are in minutes elasped from the start of the workday(8)
	public static final int START_OF_DAY = 0;
	public static final int EXEC1 = 120;
	public static final int LUNCH = 240;
	public static final int EXEC2 = 360;
	public static final int STANDUP = 480;
	public static final int BEGIN_LEAVING = 510;
	public static final int END_OF_DAY = 540;

	public static final int WORKDAY = 480; //8 hours
	public static final int HOUR = 60;
	public static final int HALF_HOUR = 30;
	public static final int QUARTER_HOUR = 15;
	public static final int TEN_MINUTES = 10;

	int timeOfDay = START_OF_DAY;
	private Timer timeKeep;

	public Clock(){
		timeKeep = new Timer(true);
		TimerTask task = new TimerTask(){
			public void run(){
				timeOfDay++;
			}
		};
		timeKeep.scheduleAtFixedRate(task, 0, 10);
	}

	public synchronized int getCurrentTime(){
		return timeOfDay;
	}

	public static int toRealtime(int Simulatedmin){
		return Simulatedmin * 10;
	}

	public static int toSimulatedMin(int ms){
		return ms / 10;
	}

	public static String getTimeStr(int min){
		return Integer.toString((min / 60) + 8) + ":" + Integer.toString(min % 60);
	}
}
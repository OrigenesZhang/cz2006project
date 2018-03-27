package iPet;

public class Reminder {

	private int rindex;
	private String username;
	private String petname;
	private String activity;
	private String startDate;  // data type subject to changes
	private String startTime;
	private int frequency;   // frequency: as per x minute
	
	public Reminder(int rindex, String username, String petname, String activity, String start_date, String start_time, int frequency) {
		this.rindex = rindex;
		this.username = username;
		this.petname = petname;
		this.activity = activity;
		this.startDate = start_date;
		this.startTime = start_time;
		this.frequency = frequency;
	}
	
	public int getRindex() {
		return rindex;
	}
	
	public void setRindex(int n) {
		rindex = n;
	}
	
	public String getUserName() {
		return username;
	}
	  
	public void setUserName(String newuser) {
		username = newuser;
	}
	
	public String getPetName() {
		return petname;
	}
	
	public void setPetName(String newpetname) {
		petname = newpetname;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public void setActivity(String newact) {
		activity = newact;
	}
	 
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String newDate) {
		startDate = newDate;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String newTime) {
		startTime = newTime;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int newFreq) {
		frequency = newFreq;
	}
}


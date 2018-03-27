package iPet;

import java.util.ArrayList;

public abstract class HygReminderDAOabs implements Observer{
	
	public void update() {
		this.updateUserInfo();
	}
	
	abstract void updateUserInfo();
	abstract ArrayList<Reminder> getallReminders();
	abstract Reminder getAReminder(int rindex);
	abstract void chooseReminder(int rindex);
	
	abstract void deleteReminder(int rindex);
	abstract void addReminder(Reminder newR);
	
	// Updatexxx() will modify the database
	abstract void updateCurrRindex(int n); 
	abstract void updateCurrRPetname(String petname);
	abstract void updateCurrRActivity(String newact);
	abstract void updateCurrRStartDate(String newDate);
	abstract void updateCurrRStartTime(String newTime);
	abstract void updateCurrRFreq(int newFreq);

}

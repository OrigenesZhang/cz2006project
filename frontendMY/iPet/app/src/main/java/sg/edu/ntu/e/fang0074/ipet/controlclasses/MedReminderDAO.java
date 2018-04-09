package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class MedReminderDAO extends MedReminderDAOabs {
	
	public static ArrayList<Reminder> medreminders = new ArrayList<Reminder>();
	public static Reminder currentReminder = new Reminder(0, "test", "test", "test", "1234", "1234", 10);
	
	private Subject userDAO;
	
	public MedReminderDAO(Subject userDAO) {
		super();
		this.userDAO = userDAO;
		userDAO.register(this);
		//TODO: Load the list of medical reminders for the current user from the database
	}


	@Override
	ArrayList<Reminder> getallReminders() {
		return medreminders;
	}

	@Override
	Reminder getAReminder(int rindex) {
		for(Reminder rem : medreminders) {
			if (rem.getRindex() == rindex) {
				return rem;
			}
		}
		return null;     //TODO: throw a toast here
	}

	// used when a different reminder is selected
	@Override
	void chooseReminder(int rindex) {
		for(Reminder rem: medreminders) {
			if(rem.getRindex() == rindex) {
				currentReminder.setRindex(rindex);
				currentReminder.setUserName(rem.getUserName());
				currentReminder.setPetName(rem.getPetName());
				currentReminder.setActivity(rem.getActivity());
				currentReminder.setStartDate(rem.getStartDate());
				currentReminder.setStartTime(rem.getStartTime());
				currentReminder.setFrequency(rem.getFrequency());
			}
		}
	}

	@Override
	void updateUserInfo() {

		String currusername = UserDAO.currentUser.getUserName();
		
		//compare owner name before update
		if(!currusername.equals(medreminders.get(0).getUserName())) {
			for(Reminder rem : medreminders) {
				rem.setUserName(currusername);
			}
		}
	}

	@Override
	void deleteReminder(int rindex) {
		for(Reminder rem : medreminders) {
			if (rem.getRindex() == rindex) {
				medreminders.remove(rem);
				return;
			}
		}
		System.out.println("Reminder not found"); /* throw a toast here */
		
	}

	@Override
	void addReminder(Reminder newR) {

		//TODO: notify the database
		
		medreminders.add(newR);	
	}

	@Override
	void updateCurrRindex(int n) {

        //TODO: notify the database
		
		currentReminder.setRindex(n);
		
	}

	@Override
	void updateCurrRPetname(String petname) {

        //TODO: notify the database
		
		currentReminder.setPetName(petname);
		
	}

	@Override
	void updateCurrRActivity(String newact) {

        //TODO: notify the database
		
		currentReminder.setActivity(newact);
		
	}

	@Override
	void updateCurrRStartDate(String newDate) {

        //TODO: notify the database
		
		currentReminder.setStartDate(newDate);
	}

	@Override
	void updateCurrRStartTime(String newTime) {

        //TODO: notify the database
		
		currentReminder.setStartTime(newTime);
	}

	@Override
	void updateCurrRFreq(int newFreq) {

        //TODO: notify the database
		
		currentReminder.setFrequency(newFreq);
		
	}

}

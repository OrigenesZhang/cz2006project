package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class HygReminderDAO extends HygReminderDAOabs {
	
	public static ArrayList<Reminder> hygreminders = new ArrayList<Reminder>();
	public static Reminder currentReminder = new Reminder(0, "test", "test", "test", "1234", "1234", 10);;
	
	private Subject userDAO;
	
	public HygReminderDAO(Subject userDAO) {
		super();
		this.userDAO = userDAO;
		userDAO.register(this);
	}


	@Override
	ArrayList<Reminder> getallReminders() {
		return hygreminders;
	}

	@Override
	Reminder getAReminder(int rindex) {
		for(Reminder rem : hygreminders) {
			if (rem.getRindex() == rindex) {
				return rem;
			}
		}
		return null;     //TODO: Throw a toast here
	}

	@Override
	void chooseReminder(int rindex) {
		for(Reminder rem: hygreminders) {
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
		if(!currusername.equals(hygreminders.get(0).getUserName())) {
			for(Reminder rem : hygreminders) {
				rem.setUserName(currusername);
			}
		}
	}

	@Override
	void deleteReminder(int rindex) {
		for(Reminder rem : hygreminders) {
			if (rem.getRindex() == rindex) {
				hygreminders.remove(rem);
				return;
			}
		}
		System.out.println("Reminder not found"); //TODO: Throw a toast here
		
	}

	@Override
	void addReminder(Reminder newR) {

		//TODO: notify the database
		hygreminders.add(newR);	
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

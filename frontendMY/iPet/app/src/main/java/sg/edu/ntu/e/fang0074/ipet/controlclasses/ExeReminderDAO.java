package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class ExeReminderDAO extends ExeReminderDAOabs {
	
	static ArrayList<Reminder> exereminders = new ArrayList<Reminder>();
	static Reminder currentReminder = new Reminder(0, "test", "test", "test", "1234", "1234", 10);;
	
	private Subject userDAO;
	
	public ExeReminderDAO(Subject userDAO) {
		super();
		this.userDAO = userDAO;
		userDAO.register(this);
	}


	@Override
	ArrayList<Reminder> getallReminders() {
		return exereminders;
	}

	@Override
	Reminder getAReminder(int rindex) {
		for(Reminder rem : exereminders) {
			if (rem.getRindex() == rindex) {
				return rem;
			}
		}
		return null;     /* throw a toast here */
	}

	@Override
	void chooseReminder(int rindex) {
		for(Reminder rem: exereminders) {
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
		if(!currusername.equals(exereminders.get(0).getUserName())) {
			for(Reminder rem : exereminders) {
				rem.setUserName(currusername);
			}
		}
	}

	@Override
	void deleteReminder(int rindex) {
		for(Reminder rem : exereminders) {
			if (rem.getRindex() == rindex) {
				exereminders.remove(rem);
				return;
			}
		}
		System.out.println("Reminder not found"); /* throw a toast here */
		
	}

	@Override
	void addReminder(Reminder newR) {
		
		/*Notify database*/
		
		exereminders.add(newR);	
	}

	@Override
	void updateCurrRindex(int n) {
		
		/* notify database*/
		
		currentReminder.setRindex(n);
		//notify();   // Change to reload activity
		
	}

	//used when as subscriber to PetDAO
	@Override
	void updateCurrRPetname(String petname) {

		/* notify datebase */
		
		currentReminder.setPetName(petname);
		//notify();
		
	}

	@Override
	void updateCurrRActivity(String newact) {
		
		/* notify datebase */
		
		currentReminder.setActivity(newact);
		//notify();
		
	}

	@Override
	void updateCurrRStartDate(String newDate) {
		
		/* notify datebase */
		
		currentReminder.setStartDate(newDate);
		//notify();
	}

	@Override
	void updateCurrRStartTime(String newTime) {
		
		/* notify datebase */
		
		currentReminder.setStartTime(newTime);
		//notify();
	}

	@Override
	void updateCurrRFreq(int newFreq) {
		
		/* notify datebase */
		
		currentReminder.setFrequency(newFreq);
		//notify();
		
	}

}

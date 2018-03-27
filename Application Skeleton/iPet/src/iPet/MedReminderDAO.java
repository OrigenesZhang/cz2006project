package iPet;

import java.util.ArrayList;

public class MedReminderDAO extends MedReminderDAOabs {
	
	static ArrayList<Reminder> medreminders = null;
	static Reminder currentReminder = null;
	
	private Subject userDAO;
	
	public MedReminderDAO(Subject userDAO) {
		super();
		this.userDAO = userDAO;
		userDAO.register(this);
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
		return null;     /* throw a toast here */
	}

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
		
		/*NOtify Database*/
		
		medreminders.add(newR);	
	}

	@Override
	void updateCurrRindex(int n) {
		
		/* notify database*/
		
		currentReminder.setRindex(n);
		
	}

	@Override
	void updateCurrRPetname(String petname) {

		/* notify datebase */
		
		currentReminder.setPetName(petname);
		
	}

	@Override
	void updateCurrRActivity(String newact) {
		
		/* notify datebase */
		
		currentReminder.setActivity(newact);
		
	}

	@Override
	void updateCurrRStartDate(String newDate) {
		
		/* notify datebase */
		
		currentReminder.setStartDate(newDate);
	}

	@Override
	void updateCurrRStartTime(String newTime) {
		
		/* notify datebase */
		
		currentReminder.setStartTime(newTime);
	}

	@Override
	void updateCurrRFreq(int newFreq) {
		
		/* notify datebase */
		
		currentReminder.setFrequency(newFreq);
		
	}

}

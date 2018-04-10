package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class MedReminderDAO extends MedReminderDAOabs {
	
	public static ArrayList<Reminder> medremindersbyuser = new ArrayList<Reminder>();
	public static Reminder currentReminder = new Reminder(0, "test", "test", "test", "1234", "1234", 10);
	private Subject userDAO;

	
	public MedReminderDAO(Subject userDAO) {
		super();
		this.userDAO = userDAO;
		userDAO.register(this);
		//TODO: From the database, load all the reminders for the current user into the list medremindersbyuser
	}


	@Override
	ArrayList<Reminder> getallReminders() {
		return medremindersbyuser;
	}

	@Override
	Reminder getAReminder(int rindex) {
		for(Reminder rem : medremindersbyuser) {
			if (rem.getRindex() == rindex) {
				return rem;
			}
		}
		return null;     //TODO: throw a toast here
	}

	// used when a different reminder is selected
	@Override
	void chooseReminder(int rindex) {
		for(Reminder rem: medremindersbyuser) {
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
		if(!currusername.equals(medremindersbyuser.get(0).getUserName())) {
			for(Reminder rem : medremindersbyuser) {
				rem.setUserName(currusername);
			}
		}
	}

	@Override
	void deleteReminder(int rindex) {
		for(Reminder rem : medremindersbyuser) {
			if (rem.getRindex() == rindex) {
				medremindersbyuser.remove(rem);
				return;
			}
		}
		System.out.println("Reminder not found"); /* throw a toast here */
	}

	@Override
	void addReminder(Reminder newR) {
		//TODO: notify the database
		medremindersbyuser.add(newR);
	}

	@Override
	void updateCurrRindex(int n) {
        //TODO: notify the database
        for(Reminder rm: medremindersbyuser){
            if(rm.getRindex()==currentReminder.getRindex()){
                rm.setRindex(n);
            }
        }
		currentReminder.setRindex(n);
	}

	@Override
	void updateCurrRPetname() {
	    String petname = PetDAO.currentPet.getPetName();
        //TODO: notify the database
        for(Reminder rm: medremindersbyuser){
            if(rm.getRindex()==currentReminder.getRindex()){
                rm.setPetName(petname);
            }
        }
		currentReminder.setPetName(petname);
	}

	@Override
	void updateCurrRActivity(String newact) {
        //TODO: notify the database
        for(Reminder rm: medremindersbyuser){
            if(rm.getRindex()==currentReminder.getRindex()){
                rm.setActivity(newact);
            }
        }
		currentReminder.setActivity(newact);
	}

	@Override
	void updateCurrRStartDate(String newDate) {
        //TODO: notify the database
        for(Reminder rm: medremindersbyuser){
            if(rm.getRindex()==currentReminder.getRindex()){
                rm.setStartDate(newDate);
            }
        }
		currentReminder.setStartDate(newDate);
	}

	@Override
	void updateCurrRStartTime(String newTime) {
        //TODO: notify the database
        for(Reminder rm: medremindersbyuser){
            if(rm.getRindex()==currentReminder.getRindex()){
                rm.setStartTime(newTime);
            }
        }
		currentReminder.setStartTime(newTime);
	}

	@Override
	void updateCurrRFreq(int newFreq) {
        //TODO: notify the database
        for(Reminder rm: medremindersbyuser){
            if(rm.getRindex()==currentReminder.getRindex()){
                rm.setFrequency(newFreq);
            }
        }
		currentReminder.setFrequency(newFreq);
	}

}

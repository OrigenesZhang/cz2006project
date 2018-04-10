package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class HygReminderDAO extends HygReminderDAOabs {
	
	public static ArrayList<Reminder> hygremindersbyuser = new ArrayList<Reminder>();
	public static Reminder currentReminder = new Reminder(0, "test", "test", "test", "1234", "1234", 10);;
	
	private Subject userDAO;
	
	public HygReminderDAO(Subject userDAO) {
		super();
		this.userDAO = userDAO;
		userDAO.register(this);
		//TODO: From the database, load all the reminders for the current user into the list hygremindersbyuser
	}


	@Override
	ArrayList<Reminder> getallReminders() {
		return hygremindersbyuser;
	}

	@Override
	Reminder getAReminder(int rindex) {
		for(Reminder rem : hygremindersbyuser) {
			if (rem.getRindex() == rindex) {
				return rem;
			}
		}
		return null;     //TODO: Throw a toast here
	}

	@Override
	void chooseReminder(int rindex) {
		for(Reminder rem: hygremindersbyuser) {
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
		if(!currusername.equals(hygremindersbyuser.get(0).getUserName())) {
			for(Reminder rem : hygremindersbyuser) {
				rem.setUserName(currusername);
			}
		}
		currentReminder.setUserName(currusername);
	}

	@Override
	void deleteReminder(int rindex) {
		for(Reminder rem : hygremindersbyuser) {
			if (rem.getRindex() == rindex) {
				hygremindersbyuser.remove(rem);
				return;
			}
		}
		System.out.println("Reminder not found"); //TODO: Throw a toast here
	}

	@Override
	void addReminder(Reminder newR) {
		//TODO: notify the database
        //TODO: change argument type
		hygremindersbyuser.add(newR);
	}

	@Override
	void updateCurrRindex(int n) {
		//TODO: notify the database
        for(Reminder rm : hygremindersbyuser){
            if(rm.getRindex() == currentReminder.getRindex()){
                rm.setRindex(n);
            }
        }
		currentReminder.setRindex(n);
	}

	@Override
	void updateCurrRPetname() {
		String petname = PetDAO.currentPet.getPetName();
		//TODO: notify the database
        for(Reminder rm: hygremindersbyuser){
            if(rm.getRindex()== currentReminder.getRindex()){
                rm.setPetName(petname);
            }
        }
		currentReminder.setPetName(petname);
	}

	@Override
	void updateCurrRActivity(String newact) {
		//TODO: notify the database
        for(Reminder rm: hygremindersbyuser){
            if(rm.getRindex() == currentReminder.getRindex()){
                rm.setActivity(newact);
            }
        }
		currentReminder.setActivity(newact);
	}

	@Override
	void updateCurrRStartDate(String newDate) {
		//TODO: notify the database
        for(Reminder rm: hygremindersbyuser){
            if(rm.getRindex() == currentReminder.getRindex()){
                rm.setStartDate(newDate);
            }
        }
		currentReminder.setStartDate(newDate);
	}

	@Override
	void updateCurrRStartTime(String newTime) {
		//TODO: notify the database
        for(Reminder rm: hygremindersbyuser){
            if(rm.getRindex() == currentReminder.getRindex()){
                rm.setStartTime(newTime);
            }
        }
		currentReminder.setStartTime(newTime);
	}

	@Override
	void updateCurrRFreq(int newFreq) {
		//TODO: notify the database
        for(Reminder rm: hygremindersbyuser){
            if(rm.getRindex() == currentReminder.getRindex()){
                rm.setFrequency(newFreq);
            }
        }
		currentReminder.setFrequency(newFreq);
	}

}

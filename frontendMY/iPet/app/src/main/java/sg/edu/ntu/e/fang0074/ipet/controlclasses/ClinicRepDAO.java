package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class ClinicRepDAO extends ClinicRepDAOabs{

	public static ClinicRep currentRep = new ClinicRep("test","test","test", "test", 0);
	public static ArrayList<ClinicRep> allreps = new ArrayList<ClinicRep>();
	private Subject clinicDAO;
	
	public ClinicRepDAO(Subject clinicDAO) {
		super();
		
		this.clinicDAO = clinicDAO;
		clinicDAO.register(this);
		
		//TODO: Load into allreps: all the clinic reps from the database
	}
	
	
	@Override
	void updateClinicInfo() {
		int prevID = ClinicDAO.currentClinic.getPrevId();
		
		if(currentRep.getClinicID() == prevID) {
			currentRep.setClinicID(ClinicDAO.currentClinic.getClinicID());
		}	
	}

	@Override
	ClinicRep getCurrentRep() {
		return currentRep;
	}

	@Override
	void getNewRep(String repname) {
		ClinicRep newRep = null; //TODO: get the new rep from the database based on the repname given
		currentRep.setRepName(repname);
		currentRep.setPrevName(newRep.getPrevName());
		currentRep.setRepPassword(newRep.getRepPassword());
		currentRep.setRepPhone(newRep.getRepPhone());
		currentRep.setClinicID(newRep.getClinicID());
		
	    //TODO: handle the case where a rep is not found.
	}

	@Override
	void updateRep(ClinicRep updateRep) {
		
		currentRep.setRepName(updateRep.getRepName());
		currentRep.setPrevName(updateRep.getPrevName());
		currentRep.setRepPassword(updateRep.getRepPassword());
		currentRep.setRepPhone(updateRep.getRepPhone());
		currentRep.setClinicID(updateRep.getClinicID());
	}

	@Override
	void updateCurrentRepName(String newname) {

		//TODO: notify the database

		currentRep.setPrevName(currentRep.getRepName());
		currentRep.setRepName(newname);
		notifyAllObservers();
	}

	@Override
	void updateCurrentRepPwd(String newpwd) {

		//TODO: notify the database
		
		currentRep.setRepPassword(newpwd);
		notifyAllObservers();
		
	}

	@Override
	void updateCurrentRepPhone(String newphone) {

		//TODO: notify the database
		
		currentRep.setRepPhone(newphone);
		notifyAllObservers();
		
	}

	@Override
	void updateCurrentRepClinicID(int newid) {

		//TODO: notify the database
		
		currentRep.setClinicID(newid);
		notifyAllObservers();
		
	}


	@Override
	ArrayList<ClinicRep> getAllReps() {
		return allreps;
	}


	@Override
	public void addRep(String repName, String prevName, String password, String phone, int clinicid) {
		ClinicRep rep = new ClinicRep(repName, prevName, password, phone, clinicid);
		allreps.add(rep);
		//TODO: notify the database
	}


	@Override
	void deleteRep(String repname) {

		//TODO: notify the database
		
		for(ClinicRep rep : allreps) {
			if(rep.getRepName().equals(repname)) {
				allreps.remove(rep);
				return;
			}
		}
		/*Handle unfound rep*/
		
	}
	
	public boolean verify(String name, String pwd) {

		// if repname exists, verify password
		this.getNewRep(name);
		if(pwd.equals(currentRep.getRepPassword())) {
			return true;
		}
		return false;
	}
	
	/* Check if the username entered is valid */
	public boolean checkexist(String name) {
		for(ClinicRep rep : allreps) {
			if(rep.getRepName().equals(name)) {
				return true;
			}
		}		
		return false;
	}



}

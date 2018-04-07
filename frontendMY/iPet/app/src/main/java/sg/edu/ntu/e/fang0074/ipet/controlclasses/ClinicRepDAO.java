package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class ClinicRepDAO extends ClinicRepDAOabs{

	static ClinicRep currentRep = new ClinicRep("test","test","test", "test", 0);
	static ArrayList<ClinicRep> allreps = new ArrayList<ClinicRep>();
	private Subject clinicDAO;
	
	public ClinicRepDAO(Subject clinicDAO) {
		super();
		
		this.clinicDAO = clinicDAO;
		clinicDAO.register(this);
		
		/*load all from rep databse onto rep list*/
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
		ClinicRep newRep = null; /* Get the new rep from ClinicRep database based on the rep name given*/
		currentRep.setRepName(repname);
		currentRep.setPrevName(newRep.getPrevName());
		currentRep.setRepPassword(newRep.getRepPassword());
		currentRep.setRepPhone(newRep.getRepPhone());
		currentRep.setClinicID(newRep.getClinicID());
		
	    /* Handle the case of rep not found */	
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

		/* Notify database */
		currentRep.setPrevName(currentRep.getRepName());
		currentRep.setRepName(newname);
		super.notify();	
	}

	@Override
	void updateCurrentRepPwd(String newpwd) {
		
		/* Notify database */
		
		currentRep.setRepPassword(newpwd);
		super.notify();	
		
	}

	@Override
	void updateCurrentRepPhone(String newphone) {
		
		/* Notify database */
		
		currentRep.setRepPhone(newphone);
		super.notify();	
		
	}

	@Override
	void updateCurrentRepClinicID(int newid) {
		
		/* Notify database */
		
		currentRep.setClinicID(newid);
		super.notify();	
		
	}


	@Override
	ArrayList<ClinicRep> getAllReps() {
		return allreps;
	}


	@Override
	public void addRep(String repName, String prevName, String password, String phone, int clinicid) {
		ClinicRep rep = new ClinicRep(repName, prevName, password, phone, clinicid);
		allreps.add(rep);
		/* Notify database*/
	}


	@Override
	void deleteRep(String repname) {

		/*Notify database*/
		
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

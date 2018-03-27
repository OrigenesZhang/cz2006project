package iPet;

import java.util.ArrayList;

public class ClinicRepDAO extends ClinicRepDAOabs{

	static ClinicRep currentRep = null;
	static ArrayList<ClinicRep> allreps = null;
	private Subject userDAO;
	
	public ClinicRepDAO(Subject userDAO) {
		super();
		
		this.userDAO = userDAO;
		userDAO.register(this);
		
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
	void addRep(ClinicRep rep) {
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

}

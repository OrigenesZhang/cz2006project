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
		ClinicRep newRep; //TODO: get the new rep from the database based on the repname given
        for(ClinicRep rep: allreps){
            if(rep.getRepName().equals(repname)){
                currentRep.setRepName(repname);
                currentRep.setPrevName(rep.getPrevName());
                currentRep.setRepPassword(rep.getRepPassword());
                currentRep.setRepPhone(rep.getRepPhone());
                currentRep.setClinicID(rep.getClinicID());
            }
        }
        return;

	    //TODO: handle the case where a rep is not found.
	}

	/*
	@Override
	void updateRep(ClinicRep updateRep) {
		currentRep.setRepName(updateRep.getRepName());
		currentRep.setPrevName(updateRep.getPrevName());
		currentRep.setRepPassword(updateRep.getRepPassword());
		currentRep.setRepPhone(updateRep.getRepPhone());
		currentRep.setClinicID(updateRep.getClinicID());
	}*/

	@Override
	void updateCurrentRepName(String newname) {
		//TODO: notify the database
        for(ClinicRep rep: allreps){
            if(rep.getRepName().equals(currentRep.getRepName())){
                rep.setPrevName(rep.getRepName());
                rep.setRepName(newname);
            }
        }
		currentRep.setPrevName(currentRep.getRepName());
		currentRep.setRepName(newname);
		notifyAllObservers();
	}

	// Do not notify observers since they should not access the password
	@Override
	void updateCurrentRepPwd(String newpwd) {
		//TODO: notify the database
        for(ClinicRep rep: allreps){
            if(rep.getRepName().equals(currentRep.getRepName())){
                rep.setRepPassword(newpwd);
            }
        }
		currentRep.setRepPassword(newpwd);
	}

	@Override
	void updateCurrentRepPhone(String newphone) {
		//TODO: notify the database
        for(ClinicRep rep: allreps){
            if(rep.getRepName().equals(currentRep.getRepName())){
                rep.setRepPhone(newphone);
            }
        }
		currentRep.setRepPhone(newphone);
		notifyAllObservers();
	}

	/*There is no need to update the observers on this change*/
	@Override
	void updateCurrentRepClinicID(int newid) {
		//TODO: notify the database
        for(ClinicRep rep: allreps){
            if(rep.getRepName().equals(currentRep.getRepName())){
                rep.setClinicID(newid);
            }
        }
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
        //TODO: notify the database
		allreps.add(rep);
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
		//TODO: Handle unfound rep
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

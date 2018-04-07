package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public abstract class ClinicRepDAOabs implements Subject, Observer, Roles{

	static ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public ClinicRepDAOabs() {
		// register subscribers upon init
	}
	
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	public void unregister(Observer deleteObserver) {
		
        // Get the index of the observer to delete
        int observerIndex = observers.indexOf(deleteObserver);
        // Print out message (Have to increment index to match)
        System.out.println("Observer " + (observerIndex+1) + " deleted");
        // Removes observer from the ArrayList
        observers.remove(observerIndex);
	}
	
	// Notify subscribers that new updates are available
	public void notifyAllObservers() {
		for(Observer observer : observers) {
			observer.update();
		}
	}
	
	
	public void update() {
		this.updateClinicInfo();
	}
	
	public String getRoleName() {
		return "ClinicRep";
	}
	
	abstract void updateClinicInfo(); // clinic info used is clinic id only
	abstract ClinicRep getCurrentRep();
	abstract void getNewRep(String repname);
	abstract void updateRep(ClinicRep updateRep);
	abstract void updateCurrentRepName(String newname);
	abstract void updateCurrentRepPwd(String newpwd);
	abstract void updateCurrentRepPhone(String newphone);
	abstract void updateCurrentRepClinicID(int newid);
	
	/* add admin priviledge*/
	abstract ArrayList<ClinicRep> getAllReps();
	abstract void addRep(String repName, String prevName, String password, String phone, int clinicid);
	abstract void deleteRep(String repname);
	
}

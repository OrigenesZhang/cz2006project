package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public abstract class ClinicDAOabs implements Subject{
	
	static ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public ClinicDAOabs() {}
	
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
	
	abstract ArrayList<Clinic> getAllClinics();
	abstract Clinic getAClinic(int clinicid);
	abstract Clinic getCurrentClinic();
	abstract void chooseClinic(int clinicid);
	abstract void updateCurrClinicId(int n);
	abstract void updateCurrClinicName(String newname);
	abstract void updateCurrClinicLocId(String newid);
	
	//abstract void deleteClinic(String petName);
	//abstract void addPet(Pet newpet);
	
	//For clinics, we assume updates are directy performed on the database
	/*
	abstract void updateCurrPetName(String newPetName);
	abstract void updateCurrPetBreed(String newBreed);
	abstract void updateCurrPetAge(int newAge);
	abstract void updateCurrPetLocation(String newLocation);
	abstract void updateCurrPetGender(String newGender);
	abstract void updateCurrPetWeight(int newWeight);
	*/

}

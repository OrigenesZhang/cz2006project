package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public abstract class PetDAOabs implements Subject, Observer{
	
	static ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public PetDAOabs() {
		/*init the observer list */
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
		this.updateOwnerInfo();
	}
	
	abstract ArrayList<Pet> getAllPets();
	abstract Pet getAPet(String petName);
	abstract void chooseCurrentPet(String petName);
	
	abstract void deletePet(String petName);
	abstract void addPet(Pet newpet);
	
	abstract void updateCurrPetName(String newPetName);
	abstract void updateCurrPetBreed(String newBreed);
	abstract void updateCurrPetAge(int newAge);
	abstract void updateCurrPetLocation(String newLocation);
	abstract void updateCurrPetGender(String newGender);
	abstract void updateCurrPetWeight(int newWeight);
	
	
	abstract void updateOwnerInfo();
	
}

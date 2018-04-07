package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class PetDAO extends PetDAOabs {
	
	static ArrayList<Pet> pets = new ArrayList<Pet>(); /*Load all the pets under a particular user*/
	static Pet currentPet = new Pet("test", "test", "test", 6, "test", "F",10);
	
	private Subject userDAO;
	
	public PetDAO(Subject userDAO) {
		super();
		// Subscribe to changes from UserDAO.
		this.userDAO = userDAO;
		userDAO.register(this);
	}
	

	@Override
	ArrayList<Pet> getAllPets() {
		return pets;
	}

	@Override
	Pet getAPet(String petName) {
		for(Pet pet: pets) {
			if(pet.getPetName().equals(petName)) {
				return pet;
			}
		}
		return null; // No corresponding pet found
	}

	@Override
	void deletePet(String petName) {
		for(Pet pet: pets) {
			if(pet.getPetName().equals(petName)) {
				pets.remove(pet);
				return;
			}
		}
		System.out.println("Nothing to remove"); /* throw a toast here*/
	}

	
	@Override
	void addPet(Pet newpet) {
		
		/*Notify database*/
		
		pets.add(newpet);	
	}
	

	@Override
	void updateOwnerInfo() {

		String currownername = UserDAO.currentUser.getUserName();
		
		//compare owner name before update
		if(!currownername.equals(pets.get(0).getOwnerName())) {
			for(Pet pet : pets) {
				pet.setOwnerName(currownername);
			}
		}
	
	}


	@Override
	void chooseCurrentPet(String petName) {
		for(Pet pet: pets) {
			if(pet.getPetName().equals(petName)) {
				currentPet.setPetName(petName);
				currentPet.setBreed(pet.getBreed());
				currentPet.setAge(pet.getAge());
				currentPet.setLocation(pet.getLocation());
				currentPet.setGender(pet.getGender());
				currentPet.setWeight(pet.getWeight());
				return;
			}
		}
		
		System.out.println("Pet not found"); /*throw a toast here*/
		
	}


	@Override
	void updateCurrPetName(String newPetName) {

		/*Notify Pet Database*/
		
		currentPet.setPetName(newPetName);	
		notify();
	}


	@Override
	void updateCurrPetBreed(String newBreed) {
		
		/*Notify Pet Database*/
		
		currentPet.setBreed(newBreed);
		notify();
	}


	@Override
	void updateCurrPetAge(int newAge) {
		
		/*Notify Pet Database*/
		
		currentPet.setAge(newAge);	
		notify();
	}


	@Override
	void updateCurrPetLocation(String newLocation) {
		
		/*Notify Pet Database*/
		
		currentPet.setLocation(newLocation);
		notify();
	}


	@Override
	void updateCurrPetGender(String newGender) {
		
		/*Notify Pet Database*/
		
		currentPet.setGender(newGender);	
		notify();
	}


	@Override
	void updateCurrPetWeight(int newWeight) {
		
		/*Notify Pet Database*/
		
		currentPet.setWeight(newWeight);
		notify();
	}

}

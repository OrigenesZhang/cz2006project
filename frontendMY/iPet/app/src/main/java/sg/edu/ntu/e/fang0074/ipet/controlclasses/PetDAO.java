package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class PetDAO extends PetDAOabs {
	
	 static ArrayList<Pet> pets = new ArrayList<Pet>(); /*Load all the pets under a particular user*/
	 static Pet currentPet = new Pet("test", "test", "test", "6", "test", "F","10");
	
	private Subject userDAO;
	
	public PetDAO(Subject userDAO) {
		super();
		// Subscribe to changes from UserDAO.
		this.userDAO = userDAO;
		userDAO.register(this);
	}
	

	@Override
	public ArrayList<Pet> getAllPets() {
		return pets;
	}

	@Override
	public Pet getAPet(String petName) {
		for(Pet pet: pets) {
			if(pet.getPetName().equals(petName)) {
				return pet;
			}
		}
		return null; // No corresponding pet found
	}

	@Override
	public void deletePet(String petName) {
		for(Pet pet: pets) {
			if(pet.getPetName().equals(petName)) {
				pets.remove(pet);
				return;
			}
		}
		System.out.println("Nothing to remove"); //TODO: throw a toast here
	}

	
	@Override
	public void addPet(Pet newpet) {
		
		//TODO: notify the database
		
		pets.add(newpet);	
	}
	

	@Override
	public void updateOwnerInfo() {

		String currownername = UserDAO.currentUser.getUserName();

		//compare owner name before update
		if(!currownername.equals(pets.get(0).getOwnerName())) {
			for(Pet pet : pets) {
				pet.setOwnerName(currownername);
			}
		}
	
	}

	public Pet getCurrentPet(){
	    return currentPet;
    }

    // used when another pet is chosen
	@Override
	public void chooseCurrentPet(String petName) {
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
	public void updateCurrPetName(String newPetName) {

		//TODO: notify the database
		
		currentPet.setPetName(newPetName);
	}


	@Override
	public void updateCurrPetBreed(String newBreed) {

        //TODO: notify the database
		
		currentPet.setBreed(newBreed);

	}


	@Override
	public void updateCurrPetAge(String newAge) {

        //TODO: notify the database
		
		currentPet.setAge(newAge);	

	}


	@Override
	public void updateCurrPetLocation(String newLocation) {

        //TODO: notify the database
		
		currentPet.setLocation(newLocation);
	}


	@Override
	public void updateCurrPetGender(String newGender) {

        //TODO: notify the database
		
		currentPet.setGender(newGender);
	}


	@Override
	public void updateCurrPetWeight(String newWeight) {

        //TODO: notify the database
		
		currentPet.setWeight(newWeight);
	}

}

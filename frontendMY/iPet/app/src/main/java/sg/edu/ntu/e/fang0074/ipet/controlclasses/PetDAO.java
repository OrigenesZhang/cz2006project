package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class PetDAO extends PetDAOabs {
	
    static ArrayList<Pet> pets = new ArrayList<Pet>(); /*Load all the pets under a particular user*/
    static Pet currentPet = new Pet("test", "test", "test", "6", "test", "F","10");
	private Subject userDAO;
	public static ArrayList<Pet> petsbyowner = new ArrayList<Pet>(); // used to load all the pets of a particular user



	public PetDAO(Subject userDAO) {
		super();
		// Subscribe to changes from UserDAO.
		this.userDAO = userDAO;
		userDAO.register(this);
	}

	void loadpets4owner(String ownername){
	    for(Pet pet: pets){
	        if(pet.getOwnerName().equals(ownername)){
	            petsbyowner.add(pet);
            }
        }
    }

	@Override
	public ArrayList<Pet> getAllPets() {
		return pets;
	}

	public ArrayList<Pet> retPetsbyowner(){
        this.loadpets4owner(UserDAO.currentUser.getUserName());
	    return petsbyowner;
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
	public void addPet(String petName, String ownerName, String breed, String age, String location, String gender, String weight) {
		Pet newpet= new Pet(petName, ownerName, breed, age, location, gender, weight);
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
		for(Pet pet: petsbyowner) {
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
        for(Pet pet : pets){
            if(pet.getPetName().equals(currentPet.getPetName())){
                pet.setPetName(newPetName);
            }
        }
		currentPet.setPetName(newPetName);
        notifyAllObservers();
	}


	@Override
	public void updateCurrPetBreed(String newBreed) {
        //TODO: notify the database
        for(Pet pet : pets){
            if(pet.getPetName().equals(currentPet.getPetName())){
                pet.setBreed(newBreed);
            }
        }
		currentPet.setBreed(newBreed);
        notifyAllObservers();
	}


	@Override
	public void updateCurrPetAge(String newAge) {
        //TODO: notify the database
        for(Pet pet : pets){
            if(pet.getPetName().equals(currentPet.getPetName())){
                pet.setAge(newAge);
            }
        }
		currentPet.setAge(newAge);
        notifyAllObservers();
	}


	@Override
	public void updateCurrPetLocation(String newLocation) {
        //TODO: notify the database
        for(Pet pet : pets){
            if(pet.getPetName().equals(currentPet.getPetName())){
                pet.setLocation(newLocation);
            }
        }
		currentPet.setLocation(newLocation);
	}


	@Override
	public void updateCurrPetGender(String newGender) {
        //TODO: notify the database
        for(Pet pet : pets){
            if(pet.getPetName().equals(currentPet.getPetName())){
                pet.setGender(newGender);
            }
        }
		currentPet.setGender(newGender);
	}


	@Override
	public void updateCurrPetWeight(String newWeight) {
        //TODO: notify the database
        for(Pet pet : pets){
            if(pet.getPetName().equals(currentPet.getPetName())){
                pet.setWeight(newWeight);
            }
        }
		currentPet.setWeight(newWeight);
	}

}

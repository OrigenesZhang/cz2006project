package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public class Pet {
	
	private String petName;
	private String ownerName;
	private String breed;
	private String age;
	private String location;
	private String gender;
	private String weight;
	
	public Pet(String petName, String ownerName, String breed, String age, String location, String gender, String weight) {
		this.petName = petName;
		this.ownerName = ownerName;
		this.breed = breed;
		this.age = age;
		this.location = location;
		this.gender = gender;
		this.weight = weight;
	}
	
	public String getPetName() {
		return petName;
	}
	
	public void setPetName(String newPetName) {
		petName = newPetName;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerName(String newOwner) {
		ownerName = newOwner;
	}
	
	public String getBreed() {
		return breed;
	}

	public void setBreed(String newBreed) {
		breed = newBreed;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String newAge) {
		age = newAge;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String newLocation) {
		location = newLocation;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String newGender) {
		gender = newGender;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public void setWeight(String newWeight) {
		weight = newWeight;
	}
}

package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class UserDAO extends UserDAOabs {
	
	static User currentUser = new User("test", "test", "11111111", null) ;
	static ArrayList<User> allusers = new ArrayList<User>();
	
	public UserDAO() {
		super();
		
		/* load the user database into the allusers list*/
	}

	@Override
	User getCurrentUser() {	
		return currentUser;
	}

	//Change attributes of the current user
	@Override
	void updateUser(User updateUser) {
		currentUser.setPrevName(currentUser.getUserName());
		currentUser.setUserName(updateUser.getUserName());
		currentUser.setPassword(updateUser.getPassword());
		currentUser.setPhone(updateUser.getPhone());
		
		/* Update the User DataBase */
		
	}
	
	public void updateUserName(String newUserName) {
		currentUser.setPrevName(currentUser.getUserName());
		currentUser.setUserName(newUserName);
		/* Notify database */
		notify();
	}
	
	public void updateUserPassword(String newPassword) {
		currentUser.setPassword(newPassword);
		/* Notify database*/
	}
	
	public void updatePhone(String newPhone) {
		currentUser.setPhone(newPhone);
		/* Notify database*/
		notify();
	}

	
	//select another user
	@Override
	void getNewUser(String newUsername) {	
		for(User newuser : allusers){
			System.out.println(newuser.getUserName());
			if(newuser.getUserName().equals(newUsername)){
				currentUser.setUserName(newuser.getUserName());
				currentUser.setPassword(newuser.getPassword());
				currentUser.setPhone(newuser.getPhone());
			}
		}

	}
	
	public String getCurrUserName() {
		return currentUser.getUserName();
	}
	
	public String getCurrUserPwd() {
		return currentUser.getPassword();
	}
	
	public String getCurrUserPhone() {
		return currentUser.getPhone();
	}

	@Override
	ArrayList<User> getAllUsers() {
		return allusers;
	}

	@Override
	public void addUser(String username, String pwd, String phone) {

		User newuser = new User(username, pwd, phone, "");
		/* Notify database*/
		allusers.add(newuser);
		
	}

	@Override
	void deleteUser(String username) {

		/*Notify database*/
		for(User user : allusers) {
			if(user.getUserName().equals(username)) {
				allusers.remove(user);
				return;
			}
		}
		/* Handle user not found*/
	}
	
	public boolean verify(String name, String pwd) {
		// if username exists, verify password
		this.getNewUser(name);
		if(!currentUser.getUserName().equals(null)){
			if(pwd.equals(currentUser.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	/* Check if the username entered is valid */
	public boolean checkexist(String name) {
		for(User user : allusers) {
			if(user.getUserName().equals(name)) {
				return true;
			}
		}		
		return false;
	}
	
}

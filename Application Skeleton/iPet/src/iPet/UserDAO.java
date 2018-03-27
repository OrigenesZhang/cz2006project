package iPet;

import java.util.ArrayList;

public class UserDAO extends UserDAOabs {
	
	static User currentUser = null;
	static ArrayList<User> allusers = null;
	
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
		User newUser = null;  /*get the new user from the database;*/
		
		currentUser.setUserName(newUser.getUserName());
		currentUser.setPassword(newUser.getPassword());
		currentUser.setPhone(newUser.getPhone());	
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
	void addUser(User newuser) {
		
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
	
}

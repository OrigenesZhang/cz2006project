package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class UserDAO extends UserDAOabs {
	
	public static User currentUser = new User("test", "test", "11111111", null) ;
	public static ArrayList<User> allusers = new ArrayList<User>();
	
	public UserDAO() {
		super();
		//TODO: load the user database into the allusers list
	}

	@Override
	User getCurrentUser() {	
		return currentUser;
	}

	/*Change attributes of the current user*/
	@Override
	void updateUser(User updateUser) {

        //TODO: Update the User DataBase

		currentUser.setPrevName(currentUser.getUserName());
		currentUser.setUserName(updateUser.getUserName());
		currentUser.setPassword(updateUser.getPassword());
		currentUser.setPhone(updateUser.getPhone());
		notifyAllObservers();
	}
	
	public void updateUserName(String newUserName) {

        //TODO: Notify database

        currentUser.setPrevName(currentUser.getUserName());
		currentUser.setUserName(newUserName);
		notifyAllObservers();
	}
	
	public void updateUserPassword(String newPassword) {

        //TODO: Notify database

        currentUser.setPassword(newPassword);
    }
	
	public void updatePhone(String newPhone) {

        //TODO: Notify database

        currentUser.setPhone(newPhone);
        notifyAllObservers();
	}

	
	//select another user as the current user, used for logout and new log in
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

        //TODO: Notify database

        User newuser = new User(username, pwd, phone, "");
        allusers.add(newuser);
		
	}

	@Override
	void deleteUser(String username) {

        //TODO: Notify database

        for(User user : allusers) {
			if(user.getUserName().equals(username)) {
				allusers.remove(user);
				return;
			}
		}

        //TODO: Handle User Not Found

    }

    /*Verify username and password*/
	public boolean verify(String name, String pwd) {
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

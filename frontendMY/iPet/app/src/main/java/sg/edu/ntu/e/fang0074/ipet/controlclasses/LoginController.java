package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import sg.edu.ntu.e.fang0074.ipet.LogIn;

public class LoginController {
	static Roles role;
	private Subject clinicDAO;
	
	public LoginController(Subject clinicDAO) {
		this.clinicDAO = clinicDAO;
	}

	// Check the username and the password
    // Check user existence before verifying username and password
	public boolean verify(String usernameInput, String pwd) {
		role = new UserDAO();
		if(role.checkexist(usernameInput)) {
			if(role.verify(usernameInput, pwd)) {
				return true;
			}
		}
		
		role = LogIn.repDAO;
		if(role.checkexist(usernameInput)) {
			if(role.verify(usernameInput, pwd)) {
				return true;
			}
		}
		
		return false;
	}
	
	
}

package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public class LoginController {
	static Roles role;
	private Subject clinicDAO;
	
	public LoginController(Subject clinicDAO) {
		this.clinicDAO = clinicDAO;
	} // pass in userDAO as well?
	
	public boolean verify(String usernameInput, String pwd) {
		role = new UserDAO();
		if(role.checkexist(usernameInput)) {
			if(role.verify(usernameInput, pwd)) {
				return true;
			}
		}
		
		role = new ClinicRepDAO(clinicDAO);
		if(role.checkexist(usernameInput)) {
			if(role.verify(usernameInput, pwd)) {
				return true;
			}
		}
		
		return false;
	}
	
	
}

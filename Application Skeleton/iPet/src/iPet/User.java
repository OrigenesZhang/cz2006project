package iPet;

public class User {
	
	private String userName = null;
	private String password = null;
	private String phone = null;
	private String prevName = null;
	
	public User(String userName, String password, String phone, String prevName) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.prevName = prevName;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String newName) {
		userName = newName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String newPhone) {
		phone = newPhone;
	}
	
	public String getPrevName(){
		return prevName;
	}
	
	public void setPrevName(String prev) {
		prevName = prev;
	}
	
}

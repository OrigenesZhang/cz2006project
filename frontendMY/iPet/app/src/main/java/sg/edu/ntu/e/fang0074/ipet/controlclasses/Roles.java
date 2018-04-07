package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public interface Roles {
	
	public String getRoleName();
	public boolean checkexist(String name);
	public boolean verify(String name, String pwd); // First check existence, then verify password.

}

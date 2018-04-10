package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public class ClinicRep {
	
	private String repName;
	private String prevName;
	private String password;
	private String phone;
	private int clinicid;
	
	public ClinicRep(String repName, String prevName, String password, String phone, int clinicid) {
		this.repName = repName;
		this.prevName = prevName;
		this.password = password;
		this.phone = phone;
		this.clinicid = clinicid;	
	}
	
	public String getRepName() {
		return repName;
	}
	
	public void setRepName(String newname) {
		repName = newname;
	}
	
	public String getPrevName() {
		return prevName;
	}
	
	public String getRepPassword() {
		return password;
	}
	
	public void setPrevName(String prev) {
		prevName = prev;
	}
	
	public void setRepPassword(String newpwd) {
		password = newpwd;
	}
	
	public String getRepPhone() {
		return phone;
	}
	
	public void setRepPhone(String newphone) {
		phone = newphone;
	}
	
	public int getClinicID() {
		return clinicid;
	}
	
	public void setClinicID(int id) {
		clinicid = id;
	}

}

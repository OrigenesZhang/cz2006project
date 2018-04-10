package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public class Clinic {

	private int clinicId;
	private int prevClinicID;
	private String clinicName;
	private String gmapLocationID;
	
	public Clinic(int id, int previd, String clinicname, String locationID){
		this.clinicId = id;
		this.prevClinicID = previd;
		this.clinicName = clinicname;
		this.gmapLocationID = locationID;
	}
	
	public int getClinicID() {
		return clinicId;
	}
	
	public void setClinicID(int n) {
		clinicId = n;
	}
	
	public int getPrevId() {
		return prevClinicID;
	}
	
	public void setPrevID(int n) {
		prevClinicID = n;
	}
	
	public String getClinicName() {
		return clinicName;
	}
	
	public void setClinicName(String newClinicName) {
		clinicName = newClinicName;
	}
	
	public String getLocationID() {
		return gmapLocationID;
	}
	
	public void setLocationID(String newLocId) {
		gmapLocationID = newLocId;	
	}
}

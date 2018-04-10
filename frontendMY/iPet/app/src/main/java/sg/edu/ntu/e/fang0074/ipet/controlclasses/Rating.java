package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public class Rating {
	
	private int rateID;
	private String username;
	private int clinicID;
	private double score;
	private String comment;
	private String date; // subject to changes
	
	public Rating(int rateid, String username, int clinicID, double score, String comment, String date) {
		this.rateID = rateid;
		this.username = username;
		this.clinicID = clinicID;
		this.score = score;
		this.comment = comment;
		this.date = date;	
	}
	
	public int getRateID() {
		return rateID;
	}
	
	public void setRateID(int id) {
		rateID = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String newname) {
		username = newname;
	}
	
	public int getClinicID() {
		return clinicID;
	}
	
	public void setClinicID(int newid) {
		clinicID = newid;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double newscore) {
		score = newscore;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String newComment) {
		comment = newComment;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String newDate) {
		date = newDate;
	}
}

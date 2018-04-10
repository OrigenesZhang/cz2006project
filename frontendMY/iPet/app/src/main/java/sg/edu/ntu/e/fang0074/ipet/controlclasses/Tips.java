package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public class Tips {
	private String repName;
	private String date; // data type subject to changes
	private String content;
	
	public Tips(String repName, String date, String content) {
		this.repName = repName;
		this.date = date;
		this.content = content;
	}
	
	public String getRepName() {
		return repName;
	}
	
	public void setRepName(String newName) {
		repName = newName;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String newDate) {
		date = newDate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String newcontent) {
		content = newcontent;
	}
}

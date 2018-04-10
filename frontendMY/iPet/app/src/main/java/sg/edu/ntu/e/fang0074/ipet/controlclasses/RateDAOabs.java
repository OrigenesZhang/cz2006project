package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public abstract class RateDAOabs implements Observer {
	
	public void update() {
		this.updateUserInfo();
		this.updateClinicInfo();
	}

	abstract void updateUserInfo();
	abstract void updateClinicInfo();
	
	// Delete and Edit Rating is not enabled
	abstract ArrayList<Rating> getRatingsForAClinic();
	abstract void addRating(double score, String comment, String date);
		
}

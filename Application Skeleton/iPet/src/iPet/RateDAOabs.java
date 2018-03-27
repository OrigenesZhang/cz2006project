package iPet;

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
	abstract void addRating(Rating r);
		
}

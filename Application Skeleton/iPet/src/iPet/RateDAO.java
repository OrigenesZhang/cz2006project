package iPet;

import java.util.ArrayList;

public class RateDAO extends RateDAOabs{
	
	static ArrayList<Rating> ratingByClinic = null;
	
	private Subject userDAO;
	private Subject clinicDAO;
	
	public RateDAO(Subject userDAO, Subject clinicDAO) {
		super();
		this.userDAO = userDAO;
		this.clinicDAO = clinicDAO;
		userDAO.register(this);
		clinicDAO.register(this);

		//TODO /* Load relevant rating onto the ratingByClinic list */
	}

	@Override
	void updateUserInfo() {
		String initialuser = UserDAO.currentUser.getPrevName();
		ArrayList<Rating> ratingByUser = null; /* load from Rating databse based on the old username retrieved above*/
		
		for(Rating rating:ratingByUser) {
			if(rating.getUsername().equals(initialuser)) {
				rating.setUsername(UserDAO.currentUser.getUserName());
			}
		}
	}

	// the only clinic info to be updated is clinicID
	@Override
	void updateClinicInfo() {
		int prevID = ClinicDAO.currentClinic.getPrevId();
		if(ratingByClinic.get(0).getClinicID() == prevID) {
			for(Rating rating : ratingByClinic) {
				rating.setClinicID(ClinicDAO.currentClinic.getClinicID());
			}
		}
		else {
			System.out.println("No need to update clinic ID");
		}

	}

	@Override
	ArrayList<Rating> getRatingsForAClinic() {
		return ratingByClinic;
	}

	@Override
	void addRating(Rating r) {
		if(r.getClinicID() == ratingByClinic.get(0).getClinicID())
		{
			ratingByClinic.add(r);
		}
		/* notify database*/
		
	}
		
}

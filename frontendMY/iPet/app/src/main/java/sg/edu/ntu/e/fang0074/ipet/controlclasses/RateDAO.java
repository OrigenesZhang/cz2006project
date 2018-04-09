package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class RateDAO extends RateDAOabs{

    // All the ratings for a clinic
	public static ArrayList<Rating> ratingByClinic = new ArrayList<Rating>();
	
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
		ArrayList<Rating> ratingByUser = new ArrayList<Rating>(); //TODO: load from Rating databse based on the old username retrieved above
		
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

	// Add new ratings
	@Override
	public void addRating( float score, String comment, String date) {
	    //Update the rating for the current clinic
	    int size = ratingByClinic.size();
	    int cid = ClinicDAO.currentClinic.getClinicID();
	    String username = UserDAO.currentUser.getUserName();
	    Rating r = new Rating(size, username, cid, score, comment, date);
	    ratingByClinic.add(r);

	    //TODO: notify database
	}
		
}

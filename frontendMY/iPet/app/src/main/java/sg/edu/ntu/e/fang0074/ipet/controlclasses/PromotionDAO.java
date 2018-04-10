package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class PromotionDAO extends PromotionDAOabs {
	
	public static ArrayList<Promotion> promotions = new ArrayList<Promotion>();
	public static Promotion currentpromo = new Promotion("test", "test", "test");
	private Subject clinicRepDAO;

	
	public PromotionDAO(Subject clinicRepDAO) {
		super();
		this.clinicRepDAO = clinicRepDAO;
		clinicRepDAO.register(this);
		// TODO: Load the promotion list from the database
	}

	@Override
	void updateClinicRepInfo() {
		// Since the current rep has been updated by the time this method is invoked,
        // the current name of the rep here would be the previous name of the rep in ClinicRep class
		String oldRep = ClinicRepDAO.currentRep.getPrevName();
		for(Promotion promo: promotions) {
			if(promo.getRepName().equals(oldRep)) {
				promo.setRepName(ClinicRepDAO.currentRep.getRepName());
			}
		}
	}

	@Override
	ArrayList<Promotion> getallPromotion() {
		return promotions;
	}

	@Override
	Promotion getAPromotion(String repName, String date) {
		for(Promotion promo: promotions) {
			if((promo.getRepName().equals(repName)) && (promo.getDate().equals(date))) {			
				return promo;		
			}
		}
		return null;
		//TODO: Use a toast to handle the case where no promotion is found
		
	}

	@Override
	void deletePromotion(String repName, String date) {
		for(Promotion promo: promotions) {
			if((promo.getRepName().equals(repName)) && (promo.getDate().equals(date))) {			
				promotions.remove(promo);		
			}
		}
	}
	

	@Override
	void addPromotion(String repName, String date, String content) {
		Promotion prm = new Promotion(repName, date, content);
	    promotions.add(prm);
	}

	@Override
	Promotion getCurrentPromotion() {
		return currentpromo;
	}

	@Override
	void updateCurrPromoRepName(String newname) {
		//TODO: notify the database
        for(Promotion promo:promotions){
            if(promo.getRepName().equals(currentpromo.getRepName())){
                promo.setRepName(newname);
            }
        }
		currentpromo.setRepName(newname);
	}

	@Override
	void udpateCurrPromoDate(String newdate) {
        //TODO: notify the database
		currentpromo.setDate(newdate);
	}

	// find the promotion sent by the same rep on the same date for update
	@Override
	void updateCurrPromocontent(String newContent) {
        //TODO: notify the database
        for(Promotion promo:promotions){
            if((promo.getRepName().equals(currentpromo.getRepName()))||(promo.getDate().equals(currentpromo.getDate()))){
                promo.setContent(newContent);
            }
        }
		currentpromo.setContent(newContent);
	}

	@Override
	void chooseCurrentPromotion(String repName, String date) {
		for(Promotion promo: promotions) {
			if((promo.getRepName().equals(repName)) && (promo.getDate().equals(date))) {			
				currentpromo.setRepName(repName);
				currentpromo.setDate(date);
				currentpromo.setContent(promo.getContent());
				return;
			}
		}
		//TODO: handle the case where no promotion is found
	}

}

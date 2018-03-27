package iPet;

import java.util.ArrayList;

public class PromotionDAO extends PromotionDAOabs {
	
	static ArrayList<Promotion> promotions = null;
	static Promotion currentpromo = null;
	
	private Subject clinicRepDAO;
	
	public PromotionDAO(Subject clinicRepDAO) {
		super();
		
		this.clinicRepDAO = clinicRepDAO;
		clinicRepDAO.register(this);
		
		/* init the promotion list */
	}

	@Override
	void updateClinicRepInfo() {
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
		/*throw a toast: no promotion found*/
		
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
	void addPromotion(Promotion prm) {
		promotions.add(prm);
	}

	@Override
	Promotion getCurrentPromotion() {
		return currentpromo;
	}

	@Override
	void updateCurrPromoRepName(String newname) {

		/* notify database */
		
		currentpromo.setRepName(newname);
		
	}

	@Override
	void udpateCurrPromoDate(String newdate) {
		
		/* notify database */
		
		currentpromo.setDate(newdate);
		
	}

	@Override
	void updateCurrPromocontent(String newContent) {
		
		/* notify database */
		
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
		/* Handle promo not found*/
		
		
	}

}

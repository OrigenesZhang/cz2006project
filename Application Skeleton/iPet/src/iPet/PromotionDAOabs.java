package iPet;

import java.util.ArrayList;

public abstract class PromotionDAOabs implements Observer{
	
	public PromotionDAOabs() {}
	
	public void update() {
		this.updateClinicRepInfo();
	}
	
	abstract void updateClinicRepInfo();
	
	abstract ArrayList<Promotion> getallPromotion();
	abstract Promotion getAPromotion(String repName, String date);
	
	//admin priviledge
	abstract void deletePromotion(String repName, String date);
	abstract void addPromotion(Promotion prm);
	abstract Promotion getCurrentPromotion();
	abstract void chooseCurrentPromotion(String repName, String date);
	abstract void updateCurrPromoRepName(String newname);
	abstract void udpateCurrPromoDate(String newdate);
	abstract void updateCurrPromocontent(String newContent);
	
	

}

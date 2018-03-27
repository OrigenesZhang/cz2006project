package iPet;

import java.util.ArrayList;

public abstract class TipsDAOabs implements Observer{
	
	public TipsDAOabs() {}
	
	public void update() {
		this.updateClinicRepInfo();
	}
	
	abstract void updateClinicRepInfo();
	
	abstract ArrayList<Tips> getallTips();
	abstract Tips getATip(String repName, String date);
	
	//admin priviledge
	abstract void deleteTip(String repName, String date);
	abstract void addTip(Tips prm);
	abstract Tips getCurrentTip();
	abstract void chooseCurrentTip(String repName, String date);
	abstract void updateCurrTipRepName(String newname);
	abstract void udpateCurrTipDate(String newdate);
	abstract void updateCurrTipcontent(String newContent);
	
	

}

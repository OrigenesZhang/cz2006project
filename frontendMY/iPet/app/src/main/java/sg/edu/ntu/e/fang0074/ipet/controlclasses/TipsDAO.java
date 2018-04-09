package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class TipsDAO extends TipsDAOabs {
	
	public static ArrayList<Tips> alltips = new ArrayList<Tips>();
	public static Tips currenttip = new Tips("test", "test","test");
	
	private Subject clinicRepDAO;
	
	public TipsDAO(Subject clinicRepDAO) {
		super();
		
		this.clinicRepDAO = clinicRepDAO;
		clinicRepDAO.register(this);
		
		//TODO: Load the tips list from database
	}

	@Override
	void updateClinicRepInfo() {
		String oldRep = ClinicRepDAO.currentRep.getPrevName();
		for(Tips tip: alltips) {
			if(tip.getRepName().equals(oldRep)) {
				tip.setRepName(ClinicRepDAO.currentRep.getRepName());
			}
		}	
	}

	@Override
	ArrayList<Tips> getallTips() {
		return alltips;
	}

	@Override
	Tips getATip(String repName, String date) {
		for(Tips tip: alltips) {
			if((tip.getRepName().equals(repName)) && (tip.getDate().equals(date))) {			
				return tip;		
			}
		}
		return null;

		//TODO: handle the case where no tip is found.
		
	}

	@Override
	void deleteTip(String repName, String date) {
		for(Tips tip: alltips) {
			if((tip.getRepName().equals(repName)) && (tip.getDate().equals(date))) {			
				alltips.remove(tip);		
			}
		}
		
	}
	

	@Override
	void addTip(Tips tip) {
		alltips.add(tip);
	}

	@Override
	Tips getCurrentTip() {
		return currenttip;
	}

	// used when the name of the clinic representative is changed
	@Override
	void updateCurrTipRepName(String newname) {

		//TODO: Notify databse
		
		currenttip.setRepName(newname);
		
	}

	@Override
	void udpateCurrTipDate(String newdate) {

		//TODO: Notify databse
		
		currenttip.setDate(newdate);
		
	}

	@Override
	void updateCurrTipcontent(String newContent) {
		
		//TODO: notify database
		
		currenttip.setContent(newContent);
	}

	@Override
	void chooseCurrentTip(String repName, String date) {
		for(Tips tip: alltips) {
			if((currenttip.getRepName().equals(repName)) && (tip.getDate().equals(date))) {			
				currenttip.setRepName(repName);
				currenttip.setDate(date);
				currenttip.setContent(tip.getContent());
				return;
			}
		}

		//TODO: Handle the case where no tip is found
		
		
	}

}

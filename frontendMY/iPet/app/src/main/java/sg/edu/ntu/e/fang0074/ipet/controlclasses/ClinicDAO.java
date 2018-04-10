package sg.edu.ntu.e.fang0074.ipet.controlclasses;

import java.util.ArrayList;

public class ClinicDAO extends ClinicDAOabs{
	
	public static ArrayList<Clinic> clinics = new ArrayList<Clinic>();
	public static Clinic currentClinic = new Clinic(1, 0, "test", "test");
	
	public ClinicDAO() {
		super();
		// TODO: load all the clinics from the database into the clinics list
	}

	@Override
	public ArrayList<Clinic> getAllClinics() {
		return clinics;
	}

	@Override
	Clinic getAClinic(int clinicid) {
		for(Clinic cl : clinics) {
			if(cl.getClinicID() == clinicid) {
				return cl;
			}
		}
		System.out.println("Clinic not found"); //TODO: Throw a toast here
		return null;
	}

	Clinic getCurrentClinic() {
		return currentClinic;
	}
	
	@Override
	public void chooseClinic(String clinicname) {
		for(Clinic cl : clinics) {
			if(cl.getClinicName().equals(clinicname)) {
				currentClinic.setClinicID(cl.getClinicID());
				currentClinic.setPrevID(cl.getPrevId());
				currentClinic.setClinicName(cl.getClinicName());
				currentClinic.setLocationID(cl.getLocationID());
				return;
			}
		}
		System.out.println("Clinic not found"); //TODO: replace with a toast
	}

	@Override
	void updateCurrClinicId(int n) {
        //TODO: notify the database
		currentClinic.setPrevID(currentClinic.getClinicID());
		currentClinic.setClinicID(n);
		notifyAllObservers();
	}

	@Override
	void updateCurrClinicName(String newname) {
        //TODO: notify the database
		currentClinic.setClinicName(newname);
		notifyAllObservers();
	}

	@Override
	void updateCurrClinicLocId(String newid) {
        //TODO: notify the database
		currentClinic.setLocationID(newid);
		notifyAllObservers();
	}

	public void addAClinic(int id, int previd, String clinicname, String locationID){
	    Clinic cn = new Clinic(id, previd,clinicname,locationID);
	    clinics.add(cn);
    }

}

package iPet;

import java.util.ArrayList;

public class ClinicDAO extends ClinicDAOabs{
	
	static ArrayList<Clinic> clinics = null;
	static Clinic currentClinic = null;
	
	public ClinicDAO() {
		super();
	}

	@Override
	ArrayList<Clinic> getAllClinics() {
		return clinics;
	}

	@Override
	Clinic getAClinic(int clinicid) {
		for(Clinic cl : clinics) {
			if(cl.getClinicID() == clinicid) {
				return cl;
			}
		}
		System.out.println("Clinic not found"); /*Throw a toast here*/
		return null;
	}

	Clinic getCurrentClinic() {
		return currentClinic;
	}
	
	@Override
	void chooseClinic(int clinicid) {
		for(Clinic cl : clinics) {
			if(cl.getClinicID() == clinicid) {
				currentClinic.setClinicID(clinicid);
				currentClinic.setPrevID(cl.getPrevId());
				currentClinic.setClinicName(cl.getClinicName());
				currentClinic.setLocationID(cl.getLocationID());
				return;
			}
		}
		System.out.println("Clinic not found"); /*Throw a toast here*/
	}

	@Override
	void updateCurrClinicId(int n) {

		/*notify database*/
		currentClinic.setPrevID(currentClinic.getClinicID());
		currentClinic.setClinicID(n);
		notify();
		
	}

	@Override
	void updateCurrClinicName(String newname) {
		
		/*notify database*/
		
		currentClinic.setClinicName(newname);
		notify();
	}

	@Override
	void updateCurrClinicLocId(String newid) {
		
		/*notify database*/
		
		currentClinic.setLocationID(newid);
		//notify();
	}

}

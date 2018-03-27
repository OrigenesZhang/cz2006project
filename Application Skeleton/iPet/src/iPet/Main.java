package iPet;

public class Main {

	public static void main(String[] args) {
		
		UserDAO userDAO = new UserDAO();
		ClinicDAO clinicDAO = new ClinicDAO();
		
		// Subscriptions
		
		PetDAO petDAO = new PetDAO(userDAO);
		MedReminderDAO medRDAO = new MedReminderDAO(userDAO);
		HygReminderDAO hygRDAO = new HygReminderDAO(userDAO);
		ExeReminderDAO exeRDAO = new ExeReminderDAO(userDAO);
		RateDAO rateDAO = new RateDAO(userDAO, clinicDAO);
		
		ClinicRepDAO repDAO = new ClinicRepDAO(clinicDAO);
		PromotionDAO promoDAO = new PromotionDAO(clinicDAO);
		TipsDAO tipsDAO = new TipsDAO(clinicDAO);
		
		
	}

}

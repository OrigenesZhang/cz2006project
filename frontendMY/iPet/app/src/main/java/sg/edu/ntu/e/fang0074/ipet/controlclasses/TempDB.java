package sg.edu.ntu.e.fang0074.ipet.controlclasses;

public class TempDB {

    UserDAO userdao;
    PetDAO petdao;
    ClinicDAO clinicdao;
    ClinicRepDAO clinicrepdao;
    MedReminderDAO medrm;
    HygReminderDAO hygrm;
    ExeReminderDAO exerm;
    RateDAO ratingdao;
    PromotionDAO promodao;
    TipsDAO tipdao;

    public TempDB(UserDAO userdao, PetDAO petdao, ClinicDAO clinicdao, ClinicRepDAO clinicrepdao, MedReminderDAO medrm, HygReminderDAO hygrm, ExeReminderDAO exerm, RateDAO ratingdao, PromotionDAO promodao, TipsDAO tipdao){
        this.userdao = userdao;
        this.petdao = petdao;
        this.clinicdao = clinicdao;
        this.clinicrepdao = clinicrepdao;
        this.medrm = medrm;
        this.hygrm = hygrm;
        this.exerm = exerm;
        this.ratingdao = ratingdao;
        this.promodao = promodao;
        this.tipdao = tipdao;

    }

    public void initDB(){

        //Add pet owners
        userdao.addUser("Mike", "1111", "24681357");
        userdao.addUser("Sally", "2222", "11112222");
        userdao.addUser("Bob", "3333", "01010101");
        userdao.addUser("Cathy", "555566", "12348765");
        userdao.addUser("Meiyi", "12345", "12345678");

        //Add pets
        petdao.addPet("Doge", "Mike", "abc", "12","Japan", "F", "10");
        petdao.addPet("Catty", "Sally", "acd", "2", "Singapore", "F", "5");
        petdao.addPet("Birdy", "Sally", "asd", "1", "Singapore", "F", "1");
        petdao.addPet("Melon", "Bob", "asw", "3", "Singapore", "M", "12");
        petdao.addPet("Fishy", "Cathy", "gfd", "1", "Singapore", "F", "1");
        petdao.addPet("MiMi", "Meiyi", "wer", "3", "Singapore", "M", "4");

        //Add clinic
        clinicdao.addAClinic(1, -1, "Vets for Pets", "ChIJwRxe_OcP2jERZhhqLdpSQI8");
        clinicdao.addAClinic(2,-1, "Republic Vet Clinic", "ChIJFTM8EnwW2jER_tYif34txlo");
        clinicdao.addAClinic(3, -1, "Island Vet", "ChIJl8jseBwQ2jERFAQiousW7iA");
        clinicdao.addAClinic(4, -1, "My Family Vet", "ChIJF3vojkMQ2jERHlPcNN8c-Zk");
        clinicdao.addAClinic(5, -1, "The Animal Clinic", "ChIJ9RfKtoYa2jEREdAB9L7v4oU");


        //Add Clinic Reps
        clinicrepdao.addRep("Xiao Ming", "", "122333", "54334456", 1);
        clinicrepdao.addRep("Ah Gau", "", "55555", "98769876", 2);
        clinicrepdao.addRep("David", "", "333", "44449999", 4);
        clinicrepdao.addRep("Betty", "", "666666", "81884567", 5);

        //TODO:Add reminder

        //Add rating
        ratingdao.addRating(4.5,"Good vet.", "02/03/2017");
        ratingdao.addRating(3.5,"Nice place for my fish.", "03/05/2016");
        ratingdao.addRating(4.0,"Satisfactory.", "03/05/2018");


        //Add promotions
        promodao.addPromotion("Xiao Ming", "03/02/2017", "Dog Food Sale");
        promodao.addPromotion("David", "03/06/2017", "Cat Food Sale");
        promodao.addPromotion("Betty", "03/02/2018", "Bird Food Sale");

        //Add tips
        tipdao.addTip("Xiao Ming", "03/02/2017", "Dog Food Sale");
        tipdao.addTip("David", "03/06/2017", "Cat Food Sale");
        tipdao.addTip("Betty", "03/02/2018", "Bird Food Sale");

    }


}

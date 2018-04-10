package sg.edu.ntu.e.fang0074.ipet;

/**
 * Created by Meiyi on 15/3/2018.
 */

// Defines a list item on the clinic search page
public class ClinicItem {
    private String name;
    private int photo;
    private String phone;
    private String rating;

    public ClinicItem(String name, int photo, String phone, String rating) {
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }
    public String getPhone(){return phone;}
    public String getRating() {
        return rating;
    }
    public int getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone){this.phone = phone;}
    public void setRating(String rating) {this.rating = rating;}
    public void setPhoto(int photo) {
        this.photo = photo;
    }
}

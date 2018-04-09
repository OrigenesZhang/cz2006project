package sg.edu.ntu.e.fang0074.ipet;

/**
 * Created by user on 22/3/2018.
 */

// Defines a list item on the comment listing section
public class CommentItem {
    private String name;
    private String comment;
    private String date;
    private String rating;

    public CommentItem(String name, String comment, String date, String rating) {
        this.name = name;
        this.comment = comment;
        this.date = date;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getComment(){return comment;}

    public String getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setComment(String comment){this.comment = comment;}
    public void setRating(String rating) {this.rating = rating;}
    public void setDate(String date) {
        this.date = date;
    }
}

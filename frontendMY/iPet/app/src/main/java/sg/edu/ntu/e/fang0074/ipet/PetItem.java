package sg.edu.ntu.e.fang0074.ipet;

/**
 * Created by Meiyi on 22/3/2018.
 */

// defines the content of an item on the pet list
public class PetItem {
    private String name;

    public PetItem(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

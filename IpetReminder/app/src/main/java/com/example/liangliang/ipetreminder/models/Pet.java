package com.example.liangliang.ipetreminder.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * A Pet class which contains specified fields of the pet
 * including name, birthday, isMale and owner.
 */

public class Pet implements Serializable{
    private String name;
    private GregorianCalendar birthday;
    private boolean isMale;
    private String owner;

    // need to edit in the future
    // replace owner string with a class

    /**
     * This method constructs a pet
     * @param name the name of the pet
     * @param birthday the birthday of the pet
     * @param isMale the gender of the pet represented by the boolean isMale
     * @param owner the owner of the pet
     */
    public Pet(String name, GregorianCalendar birthday, boolean isMale, String owner) {
        this.name = name;
        this.birthday = birthday;
        this.isMale = isMale;
        this.owner = owner;     // should be replaced with User class
    }

    /**
     * This method returns an example instance of Pet
     * @return an instance of Pet
     */
    public static Pet getInstance() {
        return new Pet("Wang Cai", new GregorianCalendar(2014, 1, 28), true, "David");

    }

    /**
     * Gets the pet's name.
     * @return the pet name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pet
     * @param name name of the pet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the pet's birthday.
     * @return A string representation of pet's birthday
     */
    public String getBirthday() {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(birthday.getTime()));
    }

    /**
     * Sets the birthday.
     * @param birthday the birthday to set
     */
    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets the pet's gender.
     * @return a boolean representation of whether the pet is mail.
     */
    public boolean isMale() {
        return isMale;
    }

    /**
     * Sets the pet's gender.
     * @param isMale whether the pet is male or not.
     */
    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    /**
     * Gets the owner of the pet
     * @return owner's name
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the pet
     * @param owner the owner of the pet
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }


}

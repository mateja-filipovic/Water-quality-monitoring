package models;

public class User {

    private String name;
    private String lastName;
    private String contactInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public User(String name, String lastName, String contactInfo) {
        this.name = name;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
    }
}

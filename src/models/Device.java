package models;

public class Device {

    private int id;
    private int age;
    private String location;
    private int locationId;

    public Device(){}

    public Device(int id, int age, String location, int locationId) {
        this.id = id;
        this.age = age;
        this.location = location;
        this.locationId = locationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}

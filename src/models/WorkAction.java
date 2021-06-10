package models;

public class WorkAction {

    private int id;
    private String time;
    private String name;
    private int creatorId;
    private int locationId;

    public WorkAction(){}

    public WorkAction(int id, String time, String name, int creatorId, int locationId) {
        this.id = id;
        this.time = time;
        this.name = name;
        this.creatorId = creatorId;
        this.locationId = locationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}

package models;

import java.util.Objects;

public class WorkAction {

    private int id;
    private String time;
    private String name;
    private int creatorId;
    private String location;

    public WorkAction(){}

    public WorkAction(int id, String time, String name, int creatorId, String location) {
        this.id = id;
        this.time = time;
        this.name = name;
        this.creatorId = creatorId;
        this.location = location;
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

    public void setLocation(String location) { this.location = location; }

    public String getLocation() { return location; }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkAction that = (WorkAction) o;
        return id == that.id;
    }

}

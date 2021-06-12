package models;

public class WorkApplication {

    private int id;
    private User user;

    public WorkApplication(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName(){return this.user.getName(); }
    public String getLastName(){ return this.user.getLastName();}
    public String getEmail(){ return this.user.getEmail(); }
}

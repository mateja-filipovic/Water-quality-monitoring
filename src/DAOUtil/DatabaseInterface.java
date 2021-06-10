package DAOUtil;

import models.User;

public interface DatabaseInterface {

    User getUserFromDB(String username, String password, int type);
    void insertAction(); //nastaviti...

}

package DAOUtil;

import models.Device;
import models.User;
import models.WorkAction;
import models.WorkApplication;

import java.util.List;

public interface DatabaseInterface {

    // klase su opisane u paketu models

    // vratiti null ako user ne postoji, ili je pogresan tip
    //1 - admin, 0 - obican
    User getUserFromDB(String username, String password, int type);

    // vratiti sve uredjaje
    List<Device> getAllDevices();

    // vratiti 5 listi parametara, svaka lista sadrzi 7 elemenata za 7 dana
    // redom pH, Turbidity, DO, Ammonia, ORP
    List<List<Integer>> getParams();

    // vraca listu svih radnih akcija
    List<WorkAction> getAllWorkActions();

    // vraca listu prijava za odredjenu akciju
    List<WorkApplication> getWorkApplications(int actionId);

    // dodavanje akcije u bazu
    void createWorkAction(String name, String location, String time, int idAdmin);


}

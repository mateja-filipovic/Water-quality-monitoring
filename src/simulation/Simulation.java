package simulation;

import homeScreen.HomeScreenController;

import java.util.ArrayList;
import java.util.List;

public class Simulation extends Thread {

    private int interval = 0;
    private List<Device> list = new ArrayList<>();
    private int count = 0;
    private HomeScreenController homeScreenController;

    public Simulation(int interval, HomeScreenController homeScreenController) {
        this.interval = interval;
        this.homeScreenController = homeScreenController;
        //this.setDaemon(true);
    }

    public List<Device> getAllDevices() {
        return list;
    }

    public synchronized void addDevice(Device sonda) {
        list.add(sonda);
    }

    public void terminate() {
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            while(!interrupted()) {
                sleep(interval * 1000);
                count++;
                synchronized (this) {
                    for(Device s: list) {
                        s.updateParameters();
                        homeScreenController.updateView();
                        if(count == 2) {
                            s.updateBase();
                        }
                    }
                }
                if(count == 2) {
                    count = 0;
                }
            }
        } catch (InterruptedException e) {}
    }

}
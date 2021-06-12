package simulacija;

import homeScreen.HomeScreenController;
import paramsScreen.ParamsController;

import java.util.ArrayList;
import java.util.List;

public class Simulacija extends Thread {

    private int interval = 0;
    private List<Sonda> sonde = new ArrayList<>();
    private int brojMerenja = 0;
    private HomeScreenController homeScreenController;

    public Simulacija(int interval, HomeScreenController homeScreenController) {
        this.interval = interval;
        this.homeScreenController = homeScreenController;
        //this.setDaemon(true);
    }

    public synchronized void dodajSondu(Sonda sonda) {
        sonde.add(sonda);
    }

    public void zaustavi() {
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            while(!interrupted()) {
                sleep(interval * 1000);
                brojMerenja++;
                synchronized (this) {
                    for(Sonda s: sonde) {
                        s.promeniParametre();
                        homeScreenController.updateView();
                        if(brojMerenja == 2) {
                            s.azurirajBazu();
                        }
                    }
                }
                if(brojMerenja == 2) {
                    brojMerenja = 0;
                }
            }
        } catch (InterruptedException e) {}
    }

}
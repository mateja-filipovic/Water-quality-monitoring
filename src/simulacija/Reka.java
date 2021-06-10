package simulacija;

import java.util.ArrayList;
import java.util.List;

public class Reka extends Thread {

    private int interval = 0;
    private List<Sonda> sonde = new ArrayList<>();
    private int brojMerenja = 0;

    public Reka(int interval) {
        this.interval = interval;
        //this.setDaemon(true);
    }

    public synchronized void dodajSondu(Sonda sonda) {
        sonde.add(sonda);
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
                        if(brojMerenja == 24 * 60 * 2) {
                            s.azurirajBazu();
                        }
                    }
                }
                if(brojMerenja == 24 * 60 * 2) {
                    brojMerenja = 0;
                }
            }
        } catch (InterruptedException e) {}
    }

}
package simulacija;

import java.util.ArrayList;
import java.util.List;

public class Reka extends Thread {

    private int interval = 0;
    private List<Sonda> sonde = new ArrayList<>();

    public Reka(int interval) {
        this.interval = interval;
        this.setDaemon(true);
    }

    public synchronized void dodajSondu(Sonda sonda) {
        sonde.add(sonda);
    }

    @Override
    public void run() {
        try {
            while(!interrupted()) {
                sleep(interval * 1000);
                synchronized (this) {
                    for(Sonda s: sonde) {
                        s.promeniParametre();
                    }
                }
            }
        } catch (InterruptedException e) {}
    }

}
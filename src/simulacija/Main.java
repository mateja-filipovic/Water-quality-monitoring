package simulacija;

import util.SQLMetode;

public class Main {

    public static void main(String[] args) {
        SQLMetode sql = new SQLMetode();
        sql.konektujSe();
        Sonda sonda = new Sonda();
        Reka reka = new Reka(10);
        System.out.println("Radim nesto");
        reka.dodajSondu(sonda);
        reka.start();
        try {
            Thread.sleep(50 * 1000);
            reka.zaustavi();
            reka.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sql.diskonektujSe();
    }

}

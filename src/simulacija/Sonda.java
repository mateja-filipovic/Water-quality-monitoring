package simulacija;

import util.SQLMetode;

public class Sonda {

    private static int sledeciID = 1;
    private int id = sledeciID++;
    private double nh3 = 0.05; //treba da bude ispod 0.2
    private int orp = 400;  //izmedju 300 i 500 je idealno
    private double o2 = 5.5;  //varira izmedju 0mg/L i 18mg/L, fizicki nemoguce da bude izvan granica
    private double zamucenost = 1.8;  //treba da bude ispod 5NTU, idealno je ispod 1NTU
    private double ph = 6.7;    //prosecna vrednost u rekama
    private double sumaNH = 0;
    private int sumaORP = 0;
    private double sumaO = 0;
    private double sumaZam = 0;
    private double sumaPH = 0;
    private SQLMetode sql;

    public Sonda(SQLMetode sql) {
        this.sql = new SQLMetode();
        sql.konektujSe();
    }

    public synchronized void promeniParametre() {
        promeniPH();
        promeniNH();
        promeniO();
        promeniORP();
        promeniZam();
    }

    private void restartuj() {
        sumaNH = 0;
        sumaORP = 0;
        sumaO = 0;
        sumaZam = 0;
        sumaPH = 0;
    }

    public double dohvProsekPH() {
        return sumaPH / (2);
    }

    public double dohvProsekNH() {
        return sumaNH / (2);
    }

    public double dohvProsekO() {
        return sumaO / (2);
    }

    public double dohvProsekZam() { return sumaZam / (2); }

    public int dohvProsekORP() {
        return sumaORP / (2);
    }

    private void promeniPH() {
        double x = Math.random() - 0.5;
        ph += x;
        if (ph > 14) {
            ph = 14;
        } else if (ph < 0) {
            ph = 0;
        }
        sumaPH += ph;
    }

    private void promeniO() {
        double x = Math.random() * 2 - 1;
        o2 += x;
        if (o2 > 18) {
            o2 = 18;
        } else if (o2 < 0) {
            o2 = 0;
        }
        sumaO += o2;
    }

    private void promeniNH() {
        double x = (Math.random() * 2 - 1) / 10;
        nh3 += x;
        if(nh3 < 0) nh3 = 0;
        sumaNH += nh3;
    }

    private void promeniZam() {
        double x = Math.random() - 0.5;
        zamucenost += x;
        if (zamucenost < 0) {
            zamucenost = 0;
        }
        sumaZam += zamucenost;
    }

    private void promeniORP() {
        int x =(int)(Math.random() * 20 - 10);
        orp += x;
        if(orp < 0) orp = 0;
        sumaORP += orp;
    }

    public synchronized void azurirajBazu() {
        sql.unesiVrednosti(id, dohvProsekNH(), dohvProsekO(), dohvProsekORP(), dohvProsekPH(), dohvProsekZam());
        restartuj();
    }

}

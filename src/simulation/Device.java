package simulation;

import util.SQLMethods;

public class Device {

    private static int nextID = 1;
    private int id = nextID++;
    private double nh3 = 0.05; //treba da bude ispod 0.2
    private int orp = 400;  //izmedju 300 i 500 je idealno
    private double o2 = 5.5;  //varira izmedju 0mg/L i 18mg/L, fizicki nemoguce da bude izvan granica
    private double turbidity = 1.8;  //treba da bude ispod 5NTU, idealno je ispod 1NTU
    private double ph = 6.7;    //prosecna vrednost u rekama
    private double sumNH = 0;
    private int sumORP = 0;
    private double sumO = 0;
    private double sumTur = 0;
    private double sumPH = 0;
    private SQLMethods sql;
    private double[] phArray = {6, 6.5, 7, 7.2, 7.3, 6.7};
    private int age = 0;
    private String location;

    public Device(int id, int age, String location) {
        this.id = id;
        this.age = age;
        this.location = location;
        this.sql = new SQLMethods();
        sql.connect();
    }

    public Device() {}

    private void insertIntoArray(double ph) {
        for(int i = 0; i < 5; i++) {
            phArray[i] = phArray[i + 1];
        }
        phArray[5] = ph;
    }

    public double[] getArray() {
        return phArray;
    }

    public synchronized void updateParameters() {
        updatePH();
        updateNH();
        updateO();
        updateORP();
        updateTur();
    }

    private void restart() {
        sumNH = 0;
        sumORP = 0;
        sumO = 0;
        sumTur = 0;
        sumPH = 0;
    }

    public double getAvgPH() {
        return sumPH / (24 * 60 * 2);
    }

    public double getAvgNH() {
        return sumNH / (24 * 60 * 2);
    }

    public double getAvgO() {
        return sumO / (24 * 60 * 2);
    }

    public double getAvgTur() {
        return sumTur / (24 * 60 * 2);
    }

    public int getAvgORP() {
        return sumORP / (24 * 60 * 2);
    }

    private void updatePH() {
        double x = Math.random() - 0.5;
        ph += x;
        if (ph > 14) {
            ph = 14;
        } else if (ph < 0) {
            ph = 0;
        }
        sumPH += ph;
        insertIntoArray(ph);
    }

    private void updateO() {
        double x = Math.random() * 2 - 1;
        o2 += x;
        if (o2 > 18) {
            o2 = 18;
        } else if (o2 < 0) {
            o2 = 0;
        }
        sumO += o2;
    }

    private void updateNH() {
        double x = (Math.random() * 2 - 1) / 10;
        nh3 += x;
        if(nh3 < 0) nh3 = 0;
        sumNH += nh3;
    }

    private void updateTur() {
        double x = Math.random() - 0.5;
        turbidity += x;
        if (turbidity < 0) {
            turbidity = 0;
        }
        sumTur += turbidity;
    }

    private void updateORP() {
        int x =(int)(Math.random() * 20 - 10);
        orp += x;
        if(orp < 0) orp = 0;
        sumORP += orp;
    }

    public synchronized void updateBase() {
        sql.insertParameters(id, getAvgNH(), getAvgO(), getAvgORP(), getAvgPH(), getAvgTur());
        restart();
    }

    public int getId() {
        return id;
    }

    public double getNh3() {
        return nh3;
    }

    public void setNh3(double nh3) {
        this.nh3 = nh3;
    }

    public int getOrp() {
        return orp;
    }

    public void setOrp(int orp) {
        this.orp = orp;
    }

    public double getO2() {
        return o2;
    }

    public void setO2(double o2) {
        this.o2 = o2;
    }

    public double getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(double turbidity) {
        this.turbidity = turbidity;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    @Override
    public String toString() {
        return location;
    }
}

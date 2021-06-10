package simulacija;

public class Main {

    public static void main(String[] args) {
        Sonda sonda = new Sonda();
        Reka reka = new Reka(10);
        System.out.println("Radim nesto");
        reka.dodajSondu(sonda);
        reka.start();
    }

}

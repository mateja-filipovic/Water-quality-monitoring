package util;

public class Main {

    //povezivanje na bazu

    public static void main(String[] args) {
        SQLMetode metode = new SQLMetode();
        metode.konektujSe();
        System.out.println("Ovo je izmedju!");
        metode.diskonektujSe();
    }

}

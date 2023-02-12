/*
 * Operator
 * 1Implentasi rumus luas lingkaran dan volume balok pada bahasa Java
 */

public class TugasTwo {
    public static void main(String[] args) {

        // untuk mengecek apakah implementasi benar

        // mengecek implementasi luas lingkaran
        System.out.println( "luas dari lingkaran " +
                            "jika diketahui jari-jari = 4 cm adalah "+
                            luasLingkaran(4.0) + " cm2");

        // mengecek implementasi volume balok
        System.out.println( "volume balok " +
                            "jika diketahui panjang = 5 cm, " +
                            "lebar = 5 cm dan tinggi = 5 cm adalah " +
                            volumeBalok(5, 5, 5) + " cm3");
    }

    // =======================================
    // implementasi dari rumus luas lingkaran
    // =======================================
    public static double luasLingkaran(Double r) {
        return Math.PI * Math.pow(r, 2);
    }

    // ====================================
    // implementasi dari rumus volume balok
    // ====================================
    public static double volumeBalok(double p, double l, double t) {
        return p * l * t;
    }
}

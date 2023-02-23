public class Lingkaran implements BangunDatar {

    private double rusuk;

    public Lingkaran(double rusuk) {
        this.rusuk = rusuk;
    }

    public static double hitungKeliling(double rusuk) {
        return 2 * Math.PI * rusuk;
    }

    public static double hitungLuas(double rusuk) {
        return Math.PI * Math.pow(rusuk, 2);
    }

    // method dari interface
    @Override
    public double hitungLuas() {
        return Math.PI * Math.pow(rusuk, 2);
    }

    @Override
    public double hitungKeliling() {
        return 2 * Math.PI * rusuk;
    }

    // getter & setter
    public double getRusuk() {
        return rusuk;
    }

    public void setRusuk(double rusuk) {
        this.rusuk = rusuk;
    }
}

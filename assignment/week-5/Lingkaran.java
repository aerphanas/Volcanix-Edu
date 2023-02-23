public class Lingkaran implements BangunDatar {

    private double rusuk;

    public Lingkaran(double rusuk) {
        this.rusuk = rusuk;
    }

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

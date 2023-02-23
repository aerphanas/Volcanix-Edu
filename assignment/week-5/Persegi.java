public class Persegi implements BangunDatar {

    private double sisi;

    public Persegi(double sisi) {
        this.sisi = sisi;
    }

    @Override
    public double hitungLuas() {
        return Math.pow(sisi, 2);
    }

    @Override
    public double hitungKeliling() {
        return 4 * sisi;
    }

    // getter & setter
    public double getSisi() {
        return sisi;
    }

    public void setSisi(double sisi) {
        this.sisi = sisi;
    }
}
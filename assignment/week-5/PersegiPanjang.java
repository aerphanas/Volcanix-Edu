public class PersegiPanjang implements BangunDatar {

    private double panjang, lebar;

    public PersegiPanjang(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public static double hitungLuas(double panjang, double lebar) {
        return lebar * panjang;
    }

    public static double hitungKeliling(double panjang, double lebar) {
        return 2 * (panjang + lebar);
    }

    // method dari interface
    @Override
    public double hitungLuas() {
        return lebar * panjang;
    }

    @Override
    public double hitungKeliling() {
        return 2 * (panjang + lebar);
    }

    // getter & setter
    public double getPanjang() {
        return panjang;
    }

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public double getLebar() {
        return lebar;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }
}
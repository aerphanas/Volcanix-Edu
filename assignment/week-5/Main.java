public class Main {
    
    public static void main(String[] args) {

        Persegi tanahPersegi = new Persegi(5);
        PersegiPanjang tanahPersegiPanjang = new PersegiPanjang(10, 5);
        Segitiga tanahSegitiga = new Segitiga(10, 7);
        Lingkaran tanahLingkaran = new Lingkaran(7);

        // mensetting ke-3 sisi dari segitiga
        // yang nanti akan digunakan untuk menghitung
        // keliling segitiga
        tanahSegitiga.setSisi1(8);
        tanahSegitiga.setSisi2(9);
        tanahSegitiga.setSisi3(10);

        System.out.println("Luas tanah persegi adalah : " + tanahPersegi.hitungLuas());
        System.out.println("Keliling tanah persegi adalah : " + tanahPersegi.hitungKeliling());

        System.out.println("Luas tanah persegi panjang adalah : " + tanahPersegiPanjang.hitungLuas());
        System.out.println("Keliling tanah persegi panjang adalah : " + tanahPersegiPanjang.hitungKeliling());

        System.out.println("Luas tanah segitiga adalah : " + tanahSegitiga.hitungLuas());
        System.out.println("Keliling tanah segitiga adalah : " + tanahSegitiga.hitungKeliling());

        System.out.println("Luas tanah lingkaran adalah : " + tanahLingkaran.hitungLuas());
        System.out.println("Keliling tanah lingkaran adalah : " + tanahLingkaran.hitungKeliling());
    }
}

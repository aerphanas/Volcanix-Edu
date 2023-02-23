public class Main {
    
    public static void main(String[] args) {

        // polimorfisme
        BangunDatar tanah;

        tanah = new Persegi(5);
        System.out.println("Luas tanah persegi adalah : " + tanah.hitungLuas());
        System.out.println("Keliling tanah persegi adalah : " + tanah.hitungKeliling());
        
        tanah = new PersegiPanjang(10, 5);
        System.out.println("Luas tanah persegi panjang adalah : " + tanah.hitungLuas());
        System.out.println("Keliling tanah persegi panjang adalah : " + tanah.hitungKeliling());

        tanah = new Segitiga(10, 7);
        System.out.println("Luas tanah segitiga adalah : " + tanah.hitungLuas());

        tanah = new Segitiga(8, 9, 10);
        System.out.println("Keliling tanah segitiga adalah : " + tanah.hitungKeliling());

        tanah = new Lingkaran(7);
        System.out.println("Luas tanah lingkaran adalah : " + tanah.hitungLuas());
        System.out.println("Keliling tanah lingkaran adalah : " + tanah.hitungKeliling());

    }
}

import java.util.ArrayList;
import java.util.Collections;

/* 2. List
 *    buatlah list berisi:
 *    a. List Nama makanan minimal 10 data
 *    b. List Tahun Piala Dunia minimal 3 data
 *    c. Implement sort pada list nama provinsi minimal 10 data secara descending
 *    d. Implement penghapusan data untuk 5 data yang berbeda dari list bahasa pemograman yang berisikan 12 data
 */

public class TugasTwo {
    public static void main(String[] args) {

        // List Nama makanan minimal 10 data
        ArrayList<String> namaMakanan = new ArrayList<>();
        namaMakanan.add("Sate Ayam");
        namaMakanan.add("Nasi Goreng");
        namaMakanan.add("Bubur Ayam");
        namaMakanan.add("Mie Ayam");
        namaMakanan.add("Nasi Uduk");
        namaMakanan.add("Martabak");
        namaMakanan.add("Bakso");
        namaMakanan.add("Rendang");
        namaMakanan.add("Ikan bakar");
        namaMakanan.add("Tempe goreng");

        // List Tahun Piala Dunia minimal 3 data
        ArrayList<Integer> tahunPialaDunia = new ArrayList<>();
        tahunPialaDunia.add(2010);
        tahunPialaDunia.add(2014);
        tahunPialaDunia.add(2018);

        // Implement sort pada list nama provinsi minimal 10 data secara descending
        ArrayList<String> provinsi = new ArrayList<String>();
        provinsi.add("Jakarta");
        provinsi.add("Maluku");
        provinsi.add("Bali");
        provinsi.add("Riau");
        provinsi.add("Jambi");
        provinsi.add("Banten");
        provinsi.add("Bengkulu");
        provinsi.add("Lampung");
        provinsi.add("Papua");
        provinsi.add("Gorontalo");

        // print sebelum di sortir
        for (String index : provinsi)
            System.out.print(index + " ");
        System.out.println();

        // sort dengan descending atau menurun
        Collections.sort(provinsi, Collections.reverseOrder());

        // mencek apakah subah benar
        for (String index : provinsi)
            System.out.print(index + " ");
        System.out.println();

        // Implement penghapusan data untuk 5 data yang berbeda
        // dari list bahasa pemograman yang berisikan 12 data

        // mengisi list dengan bahasa program
        ArrayList<String> progLang = new ArrayList<String>();
        progLang.add("C");
        progLang.add("Odin");
        progLang.add("C++");
        progLang.add("Crystal");
        progLang.add("D");
        progLang.add("Haskell");
        progLang.add("Java");
        progLang.add("Racket");
        progLang.add("Lisp");
        progLang.add("Elm");
        progLang.add("Nim");
        progLang.add("Zig");

        // print isi dari list
        for (String index : progLang)
            System.out.print(index + " ");
        System.out.println();

        // menghapus anggota list
        progLang.remove("Java");
        progLang.remove("Nim");
        progLang.remove("Zig");
        progLang.remove("Elm");
        progLang.remove("Crystal");

        // print setelah menghapus list
        for (String index : progLang)
            System.out.print(index + " ");
        System.out.println();
    }
}

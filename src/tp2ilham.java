import java.util.InputMismatchException;
import java.util.Scanner;

public class tp2ilham {
    static final Scanner in = new Scanner(System.in);
    static MataKuliah[] arrMmataKuliah;

    public static void main(String[] args) {
        boolean run = true;

        while(run) {
            int menu = showMenu();

            switch (menu){
                case 1:
                    pendataanMatkul();
                    break;
                case 2:
                    perhitunganIPS();
                    break;
                case 3:
                    updateGrade();
                    break;
                case 4:
                    run = false;
                    break;
            }
        }

        System.out.println("\n-------------------------Terima kasih-------------------------");
        System.out.println("-------------------------Salam Coding-------------------------");
    }

    private static int showMenu() {
        System.out.println("Pendataan dan Perhitungan IPS (Indeks Prestasi Semester)\n" +
                "1. Pendataan Mata Kuliah\n" +
                "2. Perhitungan IPS\n" +
                "3. Update Grade\n" +
                "4. Keluar");

        int menu = 0;

        while (menu < 1 || menu > 4) {
            try{
                System.out.print("Masukkan menu: ");
                menu = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Mohon maaf anda salah memasukkan menu");
            }
            in.nextLine();
        }

        return menu;
    }

    private static void pendataanMatkul() {
        if (arrMmataKuliah != null) {
            arrMmataKuliah = null;
        }

        System.out.println("");

        int jumlah, sks;
        String kode, nama, grade;

        System.out.print("Masukkan jumlah matkul \t: ");
        jumlah = in.nextInt();

        arrMmataKuliah = new MataKuliah[jumlah];

        for (int i = 0; i < jumlah; i++) {
            System.out.print("Masukkan kode matkul \t: ");
            kode = in.next();

            System.out.print("Masukkan nama matkul \t: ");
            nama = in.next();

            System.out.print("Masukkan jumlah sks \t: ");
            sks = in.nextInt();

            System.out.print("Masukkan jumlah grade \t: ");
            grade = in.next();

            System.out.println("");

            MataKuliah matkul= new MataKuliah();
            matkul.setGrade(grade);
            matkul.setSks(sks);
            matkul.setKode(kode);
            matkul.setNama(nama);

            arrMmataKuliah[i] = matkul;
        }

        System.out.println("Sukses memasukkan data");
    }

    private static void perhitunganIPS() {
        if (arrMmataKuliah == null) {
            System.out.println("Mohon maaf data kosong \n");
            return;
        }

        int totalSks = 0;
        float ipsMatkul = 0;
        float totalIps;
        int sks, gradeInt;
        String kode, nama, grade;

        System.out.println("Mata kuliah yang anda ambil adalah: ");
        for (int i = 0; i < arrMmataKuliah.length; i++) {
            nama = arrMmataKuliah[i].getNama();
            kode = arrMmataKuliah[i].getKode();
            grade = arrMmataKuliah[i].getGrade().toUpperCase();
            sks = arrMmataKuliah[i].getSks();

            System.out.println(kode + "\t" + nama + "\t" + grade + "\t" + sks);

            switch (grade){
                case "A":
                    gradeInt = 4;
                    break;
                case "B":
                    gradeInt = 3;
                    break;
                case "C":
                    gradeInt = 2;
                    break;
                case "D":
                    gradeInt = 1;
                    break;
                case "E":
                    gradeInt = 0;
                    break;
                default:
                    gradeInt = 0;
            }

            totalSks += sks;

            ipsMatkul += (sks * gradeInt);
        }

        totalIps = ipsMatkul / totalSks;

        System.out.println("Nilai IPS Anda adalah: " + totalIps);
        System.out.println("");
    }

    private static void updateGrade() {
        if (arrMmataKuliah == null) {
            System.out.println("Mohon maaf data kosong \n");
            return;
        }

        String kode;
        String grade = "";

        System.out.print("Masukkan kode mata kuliah: ");
        kode = in.next();

        for (int i = 0; i < arrMmataKuliah.length; i++) {
            String getKode = arrMmataKuliah[i].getKode();

            if (getKode.equals(kode)) {
                System.out.print("Masukkan grade baru: ");
                grade = in.next();

                arrMmataKuliah[i].setGrade(grade);
            }
        }

        perhitunganIPS();
    }
}

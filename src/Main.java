import java.util.List;
import java.util.Scanner;

import dao.TambahDao;
import models.Tambah;
import services.add.AddService;
import services.add.AddServiceImplementation;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    static TambahDao tambahDao = new TambahDao();
    static AddService addService = new AddServiceImplementation(tambahDao);

    private static void tambahPesanan(){
        System.out.print("Silahkan Input Nomor Pesanan Makanan: ");
        Integer makanan = scanner.nextInt();
        System.out.print("Silahkan Input Banyaknya Pesanan: ");
        Integer banyak = scanner.nextInt();
        addService.createAdd(new Tambah(makanan, banyak));
    }
    
    private static void lihatSemuaPesanan(){
        List<Tambah> hasil = addService.getAllAdd();
        for (int i = 0; i < hasil.size(); i++) {
            System.out.println((i+1) + ". " + hasil.get(i) + hasil.get(i).getBanyak() + hasil.get(i).getMakanan());
        }
    }

    public static void main(String[] args) throws Exception {
        boolean ulang = true;
        String pilih1, pilih2;
        try {
            while (ulang) {
                Menu.menuUtama();
                pilih1 = scanner.nextLine();
                switch (pilih1) {
                    case "1":
                        Menu.menuLengkap();
                        break;
                    case "2":
                        Menu.menuPesan();
                        pilih2 = scanner.nextLine();
                        switch (pilih2) {
                            case "1":
                                Menu.menuMakanan();
                                tambahPesanan();
                                lihatSemuaPesanan();
                                break;
                            case "2":
                                Menu.menuMinuman();
                                break;
                            case "3":
                                Menu.menuPaket();
                                break;
                            default:
                                
                                break;
                        }

                        break;
                    case "3":
                        
                        break;
                    default:
                        
                        break;
                }
                while (true) {
                    System.out.print("Ingin Melakukan Pemesanan / Pembayaran? (y/n): ");
                    String again = scanner.nextLine();
                    if ("y".equalsIgnoreCase(again)) {
                        break;
                    } else if ("n".equalsIgnoreCase(again)) {
                        ulang = false;
                        break;
                    } else {
                        //menuTidakAda();
                    }
                }
            }
            System.out.println("Program Selesai");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dao.MenuDao;
import dao.PesananDao;
import services.menu.MenuService;
import services.menu.MenuServiceImplementation;
import services.pembayaran.PembayaranService;
import services.pembayaran.PembayaranServiceImplementation;
import services.pemesanan.PemesananService;
import services.pemesanan.PemesananServiceImplementation;
import models.Menu;
import models.Pesanan;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static MenuDao menuDao = new MenuDao();
    static MenuService menuService = new MenuServiceImplementation(menuDao);

    static PesananDao orderDao = new PesananDao();
    static PemesananService orderService = new PemesananServiceImplementation(orderDao);

    static PembayaranService paymentService = new PembayaranServiceImplementation();

    private static void lihatDaftarMenu() {
        daftarMenu("Makanan");
        daftarMenu("Minuman");
        daftarMenu("Paket");
    }

    private static List<Menu> filterMenu(String type) {
        List<Menu> menus = menuService.getAllMenus();
        List<Menu> filteredMenus;
        List<String> menuType = Arrays.asList(type);

        filteredMenus = menus.stream().filter(menu -> menuType.contains(menu.getJenis())).collect(Collectors.toList());

        return filteredMenus;
    }

    private static void daftarMenu(String type) {
        System.out.println("\n=== MENU " + type.toUpperCase() + " ===");
        
        List<Menu> filteredMenus = filterMenu(type);

        for (int i = 0; i < filteredMenus.size(); i++) {
            System.out.println((i + 1) + ". " + filteredMenus.get(i));
        }
    }

    private static Boolean pengulangan(String menu) {
        Boolean pengulangan = false;
        String pilihan;

        System.out.print("Apakah anda ingin " + menu + " (yes / no): ");
        pilihan = sc.nextLine();

        if ("yes".equalsIgnoreCase(pilihan)) {
            pengulangan = true;
        } else if ("no".equalsIgnoreCase(pilihan)) {
            pengulangan = false;
        } else {
            DaftarOutput.salahInput();
        }
        return pengulangan;
    }

    private static void menuTambah(){
        Menu[] menus = {
            new Menu("HOKA HEMAT 1,2,3,4", "Makanan", 27000.),
            new Menu("HOKKAIDO MISO RAMEN", "Makanan", 35000.),
            new Menu("TEMPURA DON", "Makanan", 47000.),
            new Menu("SPICY RAMEN", "Makanan", 38000.),
            new Menu("PREMIUM SET BEEF TERIYAKI", "Makanan", 64000.),
            new Menu("FAVORITE SET CHICKEN TERIYAKI", "Makanan", 53000.),

            new Menu("ES MERAH DELIMA", "Minuman",20000.),
            new Menu("COLD OCHA", "Minuman",10000.),
            new Menu("LEMON TEA", "Minuman",12000.),
            new Menu("MILO", "Minuman",11000.),
            new Menu("COCA COLA", "Minuman",12000.),
            new Menu("AQUA", "Minuman",9000.),

            new Menu("PAKET A & C", "Paket",55000.),
            new Menu("PAKET B & D", "Paket",49000.),
            new Menu("BENTO SPECIAL", "Paket",58000.),
            new Menu("KIDZU BENTO", "Paket",41000.),
            new Menu("VALUE SET 1-4", "Paket",34000.),
            new Menu("PAKET FRIED CHICKEN", "Paket",56000.),
        };
        
        for (Menu menu : menus) {
            menuService.createMenu(menu);
        }        
        System.out.flush();
    }
    
    private static void menuPesan() {
        Integer pilihanSubMenu;

        do {
            DaftarOutput.menuPesan();
            pilihanSubMenu = sc.nextInt();
            sc.nextLine();
            switch (pilihanSubMenu) {
                case 1: 
                    tambahPesanan();
                    break;
                case 2: 
                    ubahPesanan();
                    break;
                case 3: 
                    hapusPesanan();
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia...");
            }

        } while (pilihanSubMenu > 4 || pilihanSubMenu < 1);
    }
    
    private static Double hargaSebelumPPN() {
        Double hargaSebelumPajak = 0.;

        for (int i = 1; i <= orderService.getNumberOfOrders(); i++) {
            hargaSebelumPajak += orderService.getOrderById(i).getHargaAwal();
        }

        return hargaSebelumPajak;
    }

    private static Double tambahPPN() {
        Double ppn = 0.;

        for (int i = 1; i <= orderService.getNumberOfOrders(); i++) {
            ppn += orderService.getOrderById(i).getPajak();
        }

        return ppn;
    }
    
    private static void tampilkanPesanan() {
        if (orderService.getNumberOfOrders() > 0) { 
            System.out.println("\n=== PESANAN ANDA ===");
            
            for (int i = 1; i <= orderService.getNumberOfOrders(); i++) {
                System.out.println(i + ". " + orderService.getOrderById(i));
            }

            System.out.println("Total harga : Rp " + hargaSebelumPPN());
            System.out.println("Total harga setelah PPN (11%) : Rp " + (hargaSebelumPPN() + tambahPPN()));
        } else {
            System.out.println("Belum ada pesanan");
        }
    }

    private static void pesanMenu(String type) {
        Integer idMenu, jumlah;
        daftarMenu(type);

        System.out.print("Masukan nomor index pesanan : ");
        idMenu = sc.nextInt();
        sc.nextLine();
        System.out.print("Masukan jumlah pesanan : ");
        jumlah = sc.nextInt();
        sc.nextLine();

        if (pengulangan("tambah pesanan")) {
            List<Menu> filteredMenus = filterMenu(type);

            Pesanan order = new Pesanan(filteredMenus.get(idMenu - 1), jumlah);
            orderService.createOrder(order);
            tampilkanPesanan();
        }
    }

    private static void tambahPesanan() {
        Integer pilihanSubMenu;

        do {
            DaftarOutput.menuPesan();
            pilihanSubMenu = sc.nextInt();
            sc.nextLine();

            switch (pilihanSubMenu) {
                case 1: 
                    pesanMenu("Makanan");
                    break;
                case 2: 
                    pesanMenu("Minuman");
                    break;
                case 3: 
                    pesanMenu("Paket");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia...");
            }

        } while (pilihanSubMenu > 3 || pilihanSubMenu < 1);
    }

    private static void ubahPesanan() {
        Integer id, idMenu, jumlah;

        tampilkanPesanan();
        System.out.print("Masukan id pesanan yang ingin diubah : ");
        id = sc.nextInt();
        sc.nextLine();

        System.out.println("\nMenu yang terdaftar : ");
        for (int i = 1; i <= menuService.getNumberOfMenus(); i++) {
            System.out.println((i) + ". " + menuService.getMenuById(i));
        }

        System.out.print("\nMasukan id menu : ");
        idMenu = sc.nextInt();
        sc.nextLine();

        System.out.print("Masukan jumlah pesanan : ");
        jumlah = sc.nextInt();
        sc.nextLine();

        if (pengulangan("mengubah pesanan")) {
            Pesanan order = new Pesanan(menuService.getMenuById(idMenu), jumlah);
            orderService.updateOrder(id, order);

            tampilkanPesanan();
        }
    }

    private static void hapusPesanan() {
        Integer id;

        if (orderService.getNumberOfOrders() > 0) {
            tampilkanPesanan();
            System.out.print("Masukan id pesanan yang ingin dihapus : ");
            id = sc.nextInt();
            sc.nextLine();

            if (pengulangan("hapus pesanan")) {
                orderService.deleteOrder(id);

                tampilkanPesanan();
            } 
        } else {
            System.out.println("Tidak bisa hapus pesanan karena belum ada data...");
        }
    }

    private static void strukPembayaran(Double totalBayar) {
        System.out.println("\n=== BUKTI PEMBAYARAN ===");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM uuuu HH:mm:ss");
        System.out.println("Tanggal : " + LocalDateTime.now().format(formatter));

        tampilkanPesanan();

        System.out.println("Biaya PPN (11%) : Rp " + tambahPPN());
        System.out.println("Total harga : Rp " + (hargaSebelumPPN() + tambahPPN()));
        System.out.println("Uang Tunai : Rp " + totalBayar);
        System.out.println("Kembalian : Rp " + (totalBayar - (hargaSebelumPPN() + tambahPPN())));
        System.out.println("Terima Kasih..");
    }

    private static void pembayaran() {
        Double totalBayar;
        
        tampilkanPesanan();
        
        do {
                System.out.print("Masukan Uang : Rp ");
                totalBayar = sc.nextDouble();        
                sc.nextLine();

                if (paymentService.bayar((hargaSebelumPPN() + tambahPPN()), totalBayar) != true) {
                    System.out.println("Uang Kurang...");
                } else {
                    strukPembayaran(totalBayar);
                }

            } while (paymentService.bayar((hargaSebelumPPN() + tambahPPN()), totalBayar) != true);      
    }


    public static void main(String[] args) {
        menuTambah();
        try {
            boolean ulang = true;
            while (ulang) {
                try {
                    boolean ulang2 = true;
                    while (ulang2) {
                        DaftarOutput.menuUtama();
                        int pilihanMenu = sc.nextInt();
                        sc.nextLine();

                        switch (pilihanMenu) {
                            case 1:
                                lihatDaftarMenu();
                                break;
                            case 2:
                                menuPesan();
                                break;
                            case 3:
                                pembayaran();
                                break;
                            case 4:
                                System.out.println("Terima kasih..");
                                ulang = false;
                                break;
                            default:
                                DaftarOutput.salahInput();
                        }
                    }

                    while (ulang) {
                        System.out.print("\nApakah mau lihat menu, pesan lagi, atau bayar ? (yes / no)");
                        String again = sc.nextLine();

                        if ("yes".equalsIgnoreCase(again)) {
                            break;
                        } else if ("no".equalsIgnoreCase(again)) {
                            ulang = false;
                            System.out.println("Terima kasih..");
                            break;
                        } else {
                            DaftarOutput.salahInput();
                        }
                    }
                } catch (Exception exception) {
                    System.out.print(exception.getMessage());
                }
            }
        } finally {
            sc.close();
        }
    }
}
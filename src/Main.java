import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dao.MenuDao;
import dao.OrderDao;
import services.menu.MenuService;
import services.menu.MenuServiceImplementation;
import services.order.OrderService;
import services.order.OrderServiceImplementation;
import services.payment.PaymentService;
import services.payment.PaymentServiceImplementation;
import models.Menu;
import models.Order;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static MenuDao menuDao = new MenuDao();
    static MenuService menuService = new MenuServiceImplementation(menuDao);

    static OrderDao orderDao = new OrderDao();
    static OrderService orderService = new OrderServiceImplementation(orderDao);

    static PaymentService paymentService = new PaymentServiceImplementation();

    public static void main(String[] args) {
        menuTambah();
        try {
            Boolean ulang = true;
            Integer pilihanMenu;
            
            while (ulang) {
                try {
                    do {
                        System.out.print("""
                        ==== HUKA HUKA BENTO ====
                        1. Daftar Menu
                        2. Pesan
                        3. Pembayaran
                        4. Exit
                        Pilihan :  """);
                        pilihanMenu = sc.nextInt();
                        sc.nextLine();

                        switch (pilihanMenu) {
                            case 1:
                                lihatDaftarMenu();
                                break;
                            case 2:
                                manajemenPesanan();
                                break;
                            case 3:
                                pembayaran();
                                break;
                            // case 4:
                            //     manajemenMenu();
                            //     break;
                            case 4: 
                                System.out.println("Anda telah keluar dari sistem...");
                                ulang = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak tersedia...\n");
                        }
                    } while (pilihanMenu > 4 || pilihanMenu < 1);

                    while (ulang) {
                        System.out.print("\nIngin melakukan pemesanan, pembayaran, atau manajemen menu? (y|n) ");
                        String again = sc.nextLine();
                            
                        if ("y".equalsIgnoreCase(again)) {
                            break;
                        } else if ("n".equalsIgnoreCase(again)) {
                            ulang = false;
                            System.out.println("Anda telah keluar");
                            break;
                        } else {
                            System.out.println("Ketik y atau n saja");
                            continue;
                        }
                    }
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Pesan error : data yang diinputkan tidak sesuai dengan tipe datanya");
                    ulang = false;
                } catch (Exception exception) {
                    System.out.print(exception.getMessage());
                }
            }
        } finally {
            sc.close();
        }
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

    private static Boolean konfirmasi(String menu) {
        Boolean konfirmasi = false;
        String pilihan;

        System.out.print("Apakah anda ingin " + menu + " (y|n): ");
        pilihan = sc.nextLine();

        if ("y".equalsIgnoreCase(pilihan)) {
            konfirmasi = true;
        } else if ("n".equalsIgnoreCase(pilihan)) {
            konfirmasi = false;
        } else {
            System.out.println("Ketik y atau n saja");
        }
        
        return konfirmasi;
    }

    private static List<Menu> filterMenus(String type) {
        List<Menu> menus = menuService.getAllMenus();
        List<Menu> filteredMenus;
        List<String> menuType = Arrays.asList(type);

        filteredMenus = menus.stream().filter(menu -> menuType.contains(menu.getType())).collect(Collectors.toList());

        return filteredMenus;
    }

    private static void printDataDariList(String type) {
        System.out.println("\n====== MENU " + type.toUpperCase() + " ======");
        
        List<Menu> filteredMenus = filterMenus(type);

        for (int i = 0; i < filteredMenus.size(); i++) {
            System.out.println((i + 1) + ". " + filteredMenus.get(i));
        }
    }

    private static void lihatDaftarMenu() {
        printDataDariList("Makanan");
        printDataDariList("Minuman");
        printDataDariList("Paket");
    }
    
    private static void manajemenPesanan() {
        Integer pilihanSubMenu;

        do {
            System.out.print("""
            
            ====== MANAJEMEN PESANAN ======
            1. Tambah Pesanan
            2. Ubah Pesanan
            3. Hapus Pesanan
            4. Batalkan Semua Pesanan
            Pilihan :  """);
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
                case 4: 
                    batalkanSemuaPesanan();
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia...");
            }

        } while (pilihanSubMenu > 4 || pilihanSubMenu < 1);
    }
    
    private static Double getHargaSebelumPPN() {
        Double hargaSebelumPajak = 0.;

        for (int i = 1; i <= orderService.getNumberOfOrders(); i++) {
            hargaSebelumPajak += orderService.getOrderById(i).getPriceBeforeTax();
        }

        return hargaSebelumPajak;
    }

    private static Double getPPN() {
        Double ppn = 0.;

        for (int i = 1; i <= orderService.getNumberOfOrders(); i++) {
            ppn += orderService.getOrderById(i).getTax();
        }

        return ppn;
    }
    
    private static void printListPesanan() {
        if (orderService.getNumberOfOrders() > 0) { 
            System.out.println("\n====== PESANAN ANDA ======");
            
            for (int i = 1; i <= orderService.getNumberOfOrders(); i++) {
                System.out.println(i + ". " + orderService.getOrderById(i));
            }

            System.out.println("Total harga : Rp " + getHargaSebelumPPN());
            System.out.println("Total harga setelah PPN (11%) : Rp " + (getHargaSebelumPPN() + getPPN()));
        } else {
            System.out.println("Belum ada pesanan");
        }
    }

    private static void pesanMenu(String type) {
        Integer idMenu, jumlah;
        printDataDariList(type);

        System.out.print("Masukan nomor " + type.toLowerCase() + " yang ingin dipesan : ");
        idMenu = sc.nextInt();
        sc.nextLine();
        System.out.print("Masukan jumlah " + type.toLowerCase() + " yang ingin dipesan : ");
        jumlah = sc.nextInt();
        sc.nextLine();

        if (konfirmasi("menambah pesanan")) {
            List<Menu> filteredMenus = filterMenus(type);
        
            if (idMenu < 1 || idMenu > filteredMenus.size()) {
                //throw new OrderException("Menu tidak tersedia...\n\n");
            }

            Order order = new Order(filteredMenus.get(idMenu - 1), jumlah);
            orderService.createOrder(order);
            printListPesanan();
        }
    }

    private static void tambahPesanan() {
        Integer pilihanSubMenu;

        do {
            System.out.print("""
            
            ====== PESAN MENU  ======
            1. Menu Makanan
            2. Menu Minuman
            3. Menu Paket
            Pilihan :  """);
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

        if (orderService.getNumberOfOrders() > 0) {
            printListPesanan();
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

            if (konfirmasi("mengubah pesanan")) {
                Order order = new Order(menuService.getMenuById(idMenu), jumlah);
                orderService.updateOrder(id, order);

                printListPesanan();
            }
        } else {
            System.out.println("Tidak bisa ubah pesanan karena belum ada data...");
        }
    }

    private static void hapusPesanan() {
        Integer id;

        if (orderService.getNumberOfOrders() > 0) {
            printListPesanan();
            System.out.print("Masukan id pesanan yang ingin dihapus : ");
            id = sc.nextInt();
            sc.nextLine();

            if (konfirmasi("menghapus pesanan")) {
                orderService.deleteOrder(id);

                printListPesanan();
            } 
        } else {
            System.out.println("Tidak bisa hapus pesanan karena belum ada data...");
        }
    }

    private static void batalkanSemuaPesanan() {
        if (konfirmasi("membatalkan semua pesanan")) {
            orderService.deleteAllOrders();
        }
    }

    private static void printStruk(Double uangCustomer) {
        System.out.println("\n====== BUKTI PEMBAYARAN ======");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM uuuu HH:mm:ss");
        System.out.println("Tanggal : " + LocalDateTime.now().format(formatter));

        printListPesanan();

        System.out.println("Biaya PPN (11%) : Rp " + getPPN());
        System.out.println("Total harga : Rp " + (getHargaSebelumPPN() + getPPN()));
        System.out.println("Uang Tunai : Rp " + uangCustomer);
        System.out.println("Kembalian : Rp " + (uangCustomer - (getHargaSebelumPPN() + getPPN())));
        System.out.println("Terima Kasih. Silahkan datang kembali~");

        orderService.deleteAllOrders();
    }

    private static void pembayaran() {
        Double uangCustomer;
        
        printListPesanan();
        
        if (orderService.getNumberOfOrders() > 0) {
            do {
                System.out.print("Masukan Nominal Uang Tunai : Rp ");
                uangCustomer = sc.nextDouble();        
                sc.nextLine();

                if (paymentService.bayar((getHargaSebelumPPN() + getPPN()), uangCustomer) != true) {
                    System.out.println("Uang yang diberikan kurang...");
                } else {
                    printStruk(uangCustomer);
                }

            } while (paymentService.bayar((getHargaSebelumPPN() + getPPN()), uangCustomer) != true);
        }         
    }

    private static void manajemenMenu() {
        Integer id, pilihanSubMenu;
        String nama, tipe;
        Double harga;
        Menu menu;

        do {
            System.out.print("""
            
            ====== Manajemen Menu  ======
            1. Tambah Menu
            2. Edit Menu
            3. Hapus Menu
            Pilihan :  """);
            pilihanSubMenu = sc.nextInt();
            sc.nextLine();

            switch (pilihanSubMenu) {
                case 1: 
                    System.out.println("\n====== TAMBAH MENU ======");
                    System.out.print("Masukan nama menu : ");
                    nama = sc.nextLine();
                    System.out.print("Masukan tipe menu (Makanan/Minuman/Paket) : ");
                    tipe = sc.nextLine();
                    System.out.print("Masukan harga menu : ");
                    harga = sc.nextDouble();
                    sc.nextLine();

                    if (konfirmasi("menambah menu")) {
                        menu = new Menu(nama, tipe, harga);
                        menuService.createMenu(menu);
                    }

                    break;
                case 2: 
                    System.out.println("\n====== EDIT MENU ======");
                    System.out.println("Menu yang terdaftar : ");
                    
                    for (int i = 1; i <= menuService.getNumberOfMenus(); i++) {
                        System.out.println((i) + ". " + menuService.getMenuById(i));
                    }

                    System.out.print("Masukan id menu yang ingin diubah : ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Masukan nama menu [boleh dikosongkan]: ");
                    nama = sc.nextLine();
                    System.out.print("Masukan tipe menu (Makanan/Minuman/Paket) [boleh dikosongkan]: ");
                    tipe = sc.nextLine();
                    System.out.print("Masukan harga menu : ");
                    harga = sc.nextDouble();
                    sc.nextLine();
                    
                    if (konfirmasi("mengedit menu")) {
                        menu = new Menu(nama, tipe, harga);
                        menuService.updateMenu(id, menu);
                    }
                    
                    break;
                case 3: 
                    System.out.println("\n====== HAPUS MENU ======");
                    System.out.print("Menu yang terdaftar : ");
                    
                    for (int i = 1; i <= menuService.getNumberOfMenus(); i++) {
                        System.out.println((i) + ". " + menuService.getMenuById(i));
                    }

                    System.out.print("Masukan id menu yang ingin dihapus : ");
                    id = sc.nextInt();
                    sc.nextLine();

                    if (konfirmasi("menghapus menu")) {
                        menuService.deleteMenu(id);
                    }

                    break;
                default:
                    System.out.println("Pilihan tidak tersedia...");
            }

        } while (pilihanSubMenu > 3 || pilihanSubMenu < 1);
    }
}

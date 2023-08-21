public class DaftarMenu {

    public static void menuUtama() {
        System.out.println("");
        System.out.println("""
                1. Daftar Menu
                2. Masukkan Pesanan
                3. Pembayaran
                """);
        System.out.print("Pilihan : ");
    }
    public static void menuPesan(){
        System.out.println("""
                ==== PESANAN ====
                1. Makanan
                2. Minuman
                3. Paket
                """);
        System.out.print("Pilihan : ");
    }

    public static void menuPaket(){
        System.out.println("""
                ==== MENU PAKET ====
                 55
                 49
                 58
                 1 - 4 41
                4 34
                 56
                """);
    }

    public static void menuLengkap(){
        System.out.println("""
                ==== MENU MAKANAN ====
                HOKA HEMAT 1 - 4 27,5
                HOKKAIDO MISO RAMEN 35
                TEMPURA DON 47
                SPICY RAMEN 38
                PREMIUM SET BEEF TERIYAKI 64
                FAVORITE SET CHICKEN TERIYAKI 53

                ==== MENU MINUMAN ====
                ES MERAH DELIMA 20
                COLD OCHA 10
                LEMON TEA 12
                MILO 11.5
                COCA COLA 12
                AQUA 9

                ==== MENU PAKET ====
                PAKET A & C 55
                PAKET B & D 49
                BENTO SPECIAL 58
                KIDZU BENTO 1 - 4 41
                VALUE SET 1-4 34
                PAKET FRIED CHICKEN 56
                """);
    }
}
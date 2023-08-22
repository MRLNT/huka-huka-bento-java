package services.pembayaran;

public interface PembayaranService {
    Boolean bayar(Double totalHarga, Double custMoney);
}

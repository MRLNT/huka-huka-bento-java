package services.pembayaran;

public class PembayaranServiceImplementation implements PembayaranService {

    @Override
    public Boolean bayar(Double totalHarga, Double totalBayar) {
        Boolean statusBayar;

        if (totalHarga > totalBayar) {
            statusBayar = false;
        } else {
            statusBayar = true;
        }

        return statusBayar;
    }
    
}

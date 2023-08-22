package services.pembayaran;

public interface PaymentService {
    Boolean bayar(Double orderPriceTotal, Double custMoney);
}

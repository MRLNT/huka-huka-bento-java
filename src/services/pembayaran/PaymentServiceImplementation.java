package services.pembayaran;

public class PaymentServiceImplementation implements PaymentService {

    @Override
    public Boolean bayar(Double orderPriceTotal, Double custMoney) {
        Boolean paymentStatus;

        if (orderPriceTotal > custMoney) {
            paymentStatus = false;
        } else {
            paymentStatus = true;
        }

        return paymentStatus;
    }
    
}

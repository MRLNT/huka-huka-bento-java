package services.payment;

public class PaymentServiceImplementation implements PaymentService {

    @Override
    public Boolean bayar(Double orderPriceTotal, Double custMoney) {
        Boolean paymentStatus;
        
        if (custMoney < 0) {
            //throw new PaymentException("Uang yang diinputkan tidak boleh kurang dari 0...\n\n");
        } 

        if (orderPriceTotal > custMoney) {
            paymentStatus = false;
        } else {
            paymentStatus = true;
        }

        return paymentStatus;
    }
    
}

package services.payment;

public interface PaymentService {
    Boolean bayar(Double orderPriceTotal, Double custMoney);
}

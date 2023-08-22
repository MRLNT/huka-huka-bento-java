package services.pemesanan;

import java.util.List;

import models.Pesanan;

public interface OrderService {
    void createOrder(Pesanan order);
    List<Pesanan> getAllOrders();
    Pesanan getOrderById(Integer id);
    Integer getNumberOfOrders();
    void updateOrder(Integer id, Pesanan order);
    void deleteOrder(Integer id);
}

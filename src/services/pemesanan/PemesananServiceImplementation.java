package services.pemesanan;

import java.util.List;

import dao.PesananDao;
import models.Pesanan;

public class PemesananServiceImplementation implements PemesananService{
    PesananDao orderDao;
    
    public PemesananServiceImplementation() {
    }

    public PemesananServiceImplementation(PesananDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void createOrder(Pesanan order) {
        this.orderDao.save(order);
    }

    @Override
    public List<Pesanan> getAllOrders() {
        return this.orderDao.findAll();
    }

    @Override
    public Pesanan getOrderById(Integer id) {
        return this.orderDao.findById(id);
    }

    @Override
    public Integer getNumberOfOrders() {
        return orderDao.getSize();
    }

    @Override
    public void updateOrder(Integer id, Pesanan order) {
        this.orderDao.update(id, order);   
    }

    @Override
    public void deleteOrder(Integer id) {
        this.orderDao.delete(id);
    }


}

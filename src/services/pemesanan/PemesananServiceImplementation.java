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
        checkId(id);
        return this.orderDao.findById(id);
    }

    @Override
    public Integer getNumberOfOrders() {
        return orderDao.getSize();
    }

    @Override
    public void updateOrder(Integer id, Pesanan order) {
        checkId(id);
        
        this.orderDao.update(id, order);   
    }

    @Override
    public void deleteOrder(Integer id) {
        checkId(id);
        this.orderDao.delete(id);
    }

    private void checkId(Integer id) {
        if (id > this.getNumberOfOrders() || id < 1) {
            throw new IllegalArgumentException("Pesanan belum ada");
        }
    }
}

package services.order;

import java.util.List;

import dao.OrderDao;
import models.Order;

public class OrderServiceImplementation implements OrderService{
    OrderDao orderDao;
    
    public OrderServiceImplementation() {
    }

    public OrderServiceImplementation(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void createOrder(Order order) {
        checkInputError(order);
        this.orderDao.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderDao.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        checkId(id);
        return this.orderDao.findById(id);
    }

    @Override
    public Integer getNumberOfOrders() {
        return orderDao.getSize();
    }

    @Override
    public void updateOrder(Integer id, Order order) {
        checkId(id);
        checkUpdateData(id, order);
        
        this.orderDao.update(id, order);   
    }

    @Override
    public void deleteOrder(Integer id) {
        checkId(id);
        this.orderDao.delete(id);
    }

    @Override
    public void deleteAllOrders() {
        this.orderDao.deleteAll();
    }

    private void checkId(Integer id) {
        if (id > this.getNumberOfOrders() || id < 1) {
            //throw new OrderException("Order tidak tersedia...\n\n");
        }
    }

    private void checkInputError(Order order) {
        String errMess = "";

        if (order.getMenu() == null && order.getQuantity() == null) {
            errMess = errMess + "Menu, dan jumlah tidak boleh kosong...";
        } else {
            if (order.getMenu() == null) {
                errMess = errMess + "Menu tidak boleh kosong. ";
            }

            if (order.getQuantity() == null) {
                errMess = errMess + "Jumlah tidak boleh kosong. ";
            } else {
                if (order.getQuantity() < 1) {
                    errMess = errMess + "Jumlah minimal 1. ";
                }
            }
        }

        if (errMess != "") {
            //throw new OrderException(errMess + "\n");
        }
    }

    private void checkUpdateData(Integer id, Order order) {
        String errMess = "";

        if (order.getMenu() == null && order.getQuantity() == null) {
            System.out.println("Tidak ada data yang diubah");
        } else {
            if (order.getMenu() == null) {
                order.setMenu(this.getOrderById(id).getMenu());
            }

            if (order.getQuantity() == null) {
                order.setQuantity(this.getOrderById(id).getQuantity());
            } else {
                if (order.getQuantity() < 1) {
                    errMess = errMess + "Jumlah minimal 1. ";
                }
            }
        }

        if (errMess != "") {
            //throw new OrderException(errMess + "\n");
        }
    }
}

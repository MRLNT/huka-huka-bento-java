package services.add;

import java.util.List;

import dao.TambahDao;
import models.Tambah;

public class AddServiceImplementation implements AddService{
    TambahDao tambahDao;
    
    public AddServiceImplementation(TambahDao tambahDao){
        this.tambahDao = tambahDao;
    }

    @Override
    public void createAdd(Tambah tambah) {
        tambahDao.save(tambah);
    }

    @Override
    public List<Tambah> getAllAdd() {
        return tambahDao.findAll();
    }

    @Override
    public void deleteAdd(Integer id) {
        tambahDao.delete(id);
        System.out.println("Pesanan telah dihapus");
    }

    @Override
    public Tambah getAddById(Integer id) {
        //IndexOutOfBoundsException: Index 2 out of bounds for length 2
        // validasi id yang diinput misalnya <0 / 0>
        //throw exception errornya
        return tambahDao.findById(id);
    }
    
}
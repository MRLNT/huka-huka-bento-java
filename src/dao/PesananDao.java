package dao;

import java.util.ArrayList;
import java.util.List;
import models.Pesanan;
import services.BaseDao;

public class PesananDao implements BaseDao<Pesanan,Integer>{
    List<Pesanan> pesanan = new ArrayList<>();

    @Override
    public void save(Pesanan data) {
        this.pesanan.add(data);
    }

    @Override
    public List<Pesanan> findAll() {
        return this.pesanan;
    }

    @Override
    public Pesanan findById(Integer id) {
        return this.pesanan.get(id - 1);
    }

    @Override
    public Integer getSize() {
        return this.pesanan.size();
    }

    @Override
    public void update(Integer id, Pesanan data) {
        this.pesanan.set(id - 1, data);
    }

    @Override
    public void delete(Integer id) {
        this.pesanan.remove(id - 1);
    }
}

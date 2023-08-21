package dao;

import java.util.ArrayList;
import java.util.List;

import models.Tambah;
import services.BaseDao;

public class TambahDao implements BaseDao<Tambah, Integer> {
    List<Tambah> adds = new ArrayList<>();

    @Override
    public void save(Tambah data) {
        adds.add(data);
    }

    @Override
    public List<Tambah> findAll() {
        return this.adds;
    }

    @Override
    public void delete(Integer id) {
        adds.remove(id - 1);
    }

    @Override
    public Tambah findById(Integer id) {
        return this.adds.get(id - 1);
    }

    @Override
    public void update(Integer id, Tambah data) {
        adds.set(id - 1, data);
    } 
    
}
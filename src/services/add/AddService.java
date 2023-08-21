package services.add;

import java.util.List;

import models.Tambah;

public interface AddService {
    //CRUD
    void createAdd(Tambah tambah);
    List<Tambah> getAllAdd();
    Tambah getAddById(Integer id);
    void deleteAdd(Integer id);
}

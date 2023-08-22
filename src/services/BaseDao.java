package services;

import java.util.List;

public interface BaseDao<E, ID> {
    void save(E data);
    List<E> findAll();
    E findById(ID id);
    void delete(ID id);
    Integer getSize();
    void update(ID id, E data);
}
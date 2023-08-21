package services;

import java.util.List;

public interface BaseDao<E, ID> {
    void save(E data);
    List<E> findAll();
    E findById(ID id);
    void update(ID id, E data);
    void delete(ID id);
}

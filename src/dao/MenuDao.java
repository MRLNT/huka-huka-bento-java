package dao;

import java.util.ArrayList;
import java.util.List;

import services.BaseDao;
import models.Menu;

public class MenuDao implements BaseDao<Menu, Integer> {
    List<Menu> menu = new ArrayList<>();

    @Override
    public void update(Integer id, Menu data) {
        this.menu.set(id - 1, data);
    }

    @Override
    public List<Menu> findAll() {
        return this.menu;
    }

    @Override
    public Integer getSize() {
        return this.menu.size();
    }

    @Override
    public void save(Menu data) {
        this.menu.add(data);
    }

    @Override
    public Menu findById(Integer id) {
        return this.menu.get(id - 1);
    }

    @Override
    public void delete(Integer id) {
        this.menu.remove(id - 1);
    }
}
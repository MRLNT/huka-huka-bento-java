package services.menu;

import java.util.List;

import dao.MenuDao;
import models.Menu;

public class MenuServiceImplementation implements MenuService {
    MenuDao menuDao;

    public MenuServiceImplementation() {
    }

    public MenuServiceImplementation(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public void createMenu(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuDao.findAll();
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public Integer getNumberOfMenus() {
        return menuDao.getSize();
    }

    @Override
    public void updateMenu(Integer id, Menu menu) {
        menuDao.update(id, menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuDao.delete(id);
    }
}

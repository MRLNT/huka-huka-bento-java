package services.menu;

import java.util.List;

import models.Menu;

public interface MenuService {
    void createMenu(Menu menu);
    List<Menu> getAllMenus();
    Menu getMenuById(Integer id);
    Integer getNumberOfMenus();
    void updateMenu(Integer id, Menu menu);
    void deleteMenu(Integer id);
    void deleteAllMenus();
}

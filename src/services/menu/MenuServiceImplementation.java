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
        checkInputError(menu);
        menuDao.save(menu);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuDao.findAll();
    }

    @Override
    public Menu getMenuById(Integer id) {
        checkId(id);
        return menuDao.findById(id);
    }

    @Override
    public Integer getNumberOfMenus() {
        return menuDao.getSize();
    }

    @Override
    public void updateMenu(Integer id, Menu menu) {
        checkId(id);
        checkUpdateData(id, menu);
        menuDao.update(id, menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        checkId(id);
        menuDao.delete(id);
    }
    
    @Override
    public void deleteAllMenus() {
        menuDao.deleteAll();
    }
    
    private void checkId(Integer id) {
        if (id > this.getNumberOfMenus() || id < 1) {
            //throw new MenuException("Menu tidak tersedia...\n\n");
        }
    }

    private void checkInputError(Menu menu) {
        String errMess = "";

        if (menu.getName() == "" && menu.getPrice() == null && menu.getType() == "") {
            errMess = errMess + "Nama, harga, dan tipe menu tidak boleh kosong...";
        } else {
            if (menu.getName() == "") {
                errMess = errMess + "Nama menu tidak boleh kosong. ";
            }

            if (menu.getPrice() == null) {
                errMess = errMess + "Harga menu tidak boleh kosong. ";
            } else {
                if (menu.getPrice() < 0) {
                    errMess = errMess + "Harga menu tidak boleh kurang dari 0. ";
                }
            }

            if (menu.getType() == "") {
                errMess = errMess + "Tipe menu tidak boleh kosong. ";
            }
        }

        if (errMess != "") {
            //throw new MenuException(errMess + "\n");
        }
    }

    private void checkUpdateData(Integer id, Menu menu) {
        String errMess = "";

        if (menu.getName() == "" && menu.getPrice() == null && menu.getType() == "") {
            System.out.println("Tidak ada data yang diubah");
        } else {
            if (menu.getName() == "") {
                menu.setName(this.getMenuById(id).getName());
            }

            if (menu.getPrice() == null) {
                menu.setPrice(this.getMenuById(id).getPrice());
            } else {
                if (menu.getPrice() < 0) {
                    errMess = errMess + "Harga menu tidak boleh kurang dari 0";
                }
            }

            if (menu.getType() == "") {
                menu.setType(this.getMenuById(id).getType());
            }
        }

        if (errMess != "") {
            //throw new MenuException(errMess + "\n");
        }
    }
}

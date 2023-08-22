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
    
    private void checkId(Integer id) {
        if (id > this.getNumberOfMenus() || id < 1) {
            throw new IllegalArgumentException("Menu belum ada");
        }
    }

    private void checkUpdateData(Integer id, Menu menu) {
        String errMess = "";

        if (menu.getNama() == "" && menu.getHarga() == null && menu.getJenis() == "") {
            System.out.println("Tidak ada data yang diubah");
        } else {
            if (menu.getNama() == "") {
                menu.setNama(this.getMenuById(id).getNama());
            }

            if (menu.getHarga() == null) {
                menu.setHarga(this.getMenuById(id).getHarga());
            } else {
                if (menu.getHarga() < 0) {
                    errMess = errMess + "Harga menu tidak boleh kurang dari 0";
                }
            }

            if (menu.getJenis() == "") {
                menu.setJenis(this.getMenuById(id).getJenis());
            }
        }

        if (errMess != "") {
            //throw new MenuException(errMess + "\n");
        }
    }
}

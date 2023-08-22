package models;

public class Pesanan {    
    private Menu menu;
    private Integer jumlah;
    private Double hargaAwal;
    private Double pajak;

    public Pesanan() {

    }

    public Pesanan(Menu menu, Integer jumlah) {
        this.setMenu(menu);
        this.setJumlah(jumlah);
        this.setHargaAwal(hargaAwal);
        this.setPajak(pajak);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHargaAwal() {
        return hargaAwal;
    }

    public void setHargaAwal(Double price) {
        this.hargaAwal = this.jumlah * this.menu.getHarga();
    }

    public Double getPajak() {
        return pajak;
    }

    public void setPajak(Double pajak) {
        this.pajak = (0.11 * this.hargaAwal);
    }

    @Override
    public String toString() {
        return "\t" + menu.getNama() + "\n\t" + jumlah + " x Rp " + menu.getHarga() + " = Rp " + hargaAwal;
    }    
}

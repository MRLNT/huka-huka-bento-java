package models;

public class Menu {
    private String nama;
    private String jenis;
    private Double harga;
    
    public Menu() {
    }
    
    public Menu(String nama, String jenis, Double harga) {
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return nama + " - Rp " + harga;
    }
}

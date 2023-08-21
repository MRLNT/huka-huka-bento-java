package models;

public class Tambah {
    private Integer makanan;
    private Integer banyak;
    
    public Tambah() {
    }

    public Tambah(Integer makanan, Integer banyak) {
        this.makanan = makanan;
        this.banyak = banyak;
    }

    public Integer getMakanan() {
        return makanan;
    }
    public void setMakanan(Integer makanan) {
        this.makanan = makanan;
    }
    public Integer getBanyak() {
        return banyak;
    }
    public void setBanyak(Integer banyak) {
        this.banyak = banyak;
    }

    @Override
    public String toString() {
        return "nama makanan: " + makanan + "jumlah" + banyak;
    }   
}
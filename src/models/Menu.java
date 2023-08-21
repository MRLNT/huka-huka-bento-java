package models;

public class Menu {
    private String name;
    private String type;
    private Double price;

    public Menu() {
    }
    
    public Menu(String name, String type, Double price) {
        this.setName(name);
        this.setType(type);
        this.setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - Rp " + price;
    }
}

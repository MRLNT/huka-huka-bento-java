package models;

public class Order {    
    private Menu menu;
    private Integer quantity;
    private Double priceBeforeTax;
    private Double tax;

    public Order() {

    }

    public Order(Menu menu, Integer quantity) {
        this.setMenu(menu);
        this.setQuantity(quantity);
        this.setPriceBeforeTax(priceBeforeTax);
        this.setTax(tax);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceBeforeTax() {
        return priceBeforeTax;
    }

    public void setPriceBeforeTax(Double price) {
        if (this.quantity == null || this.menu == null) {
            //throw new OrderException("Harga tidak dapat dihitung karena tidak ada data jumlah atau menu...\n");
        }

        this.priceBeforeTax = this.quantity * this.menu.getPrice();
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        if (this.priceBeforeTax == null) {
            //throw new OrderException("PPN tidak dapat dihitung karena tidak ada data harga sebelum pajak...\n");
        }

        this.tax = (0.11 * this.priceBeforeTax);
    }

    @Override
    public String toString() {
        return "\t" + menu.getName() + "\n\t" + quantity + " x Rp " + menu.getPrice() + " = Rp " + priceBeforeTax;
    }    
}

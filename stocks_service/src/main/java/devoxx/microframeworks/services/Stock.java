package devoxx.microframeworks.services;

public class Stock {
    private int stock;
    private double price;
    private Category category;


    @Override
    public String toString() {
        return String.format("Stock{stock=%d, price=%s, category=%s}", stock, price, category);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

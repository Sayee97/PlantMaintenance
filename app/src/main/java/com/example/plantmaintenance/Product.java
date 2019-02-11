package com.example.plantmaintenance;

public class Product {
    String product_name;
    String supplier;
    String quantity;
    String unit_cost;

    public String getProduct_name() {
        return product_name;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnit_cost() {
        return unit_cost;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setUnit_cost(String unit_cost) {
        this.unit_cost = unit_cost;
    }
    @Override
    public String toString() {
        return this.unit_cost + this.product_name+this.supplier+this.quantity;
    }
}

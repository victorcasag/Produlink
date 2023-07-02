package BigHouse.Produlink.LibraryProdulink.SaleProduct;

import BigHouse.Produlink.LibraryProdulink.Product.ModelProduct;
import BigHouse.Produlink.LibraryProdulink.Sale.ModelSale;

import java.time.LocalDate;

public class ModelSaleProduct {
    public Long id;
    public ModelSale sale;
    public int quantity;
    public double discount;
    public double total;
    public LocalDate dateCreation;
    public ModelProduct product;

    public ModelProduct getProduct() {
        return product;
    }

    public void setProduct(ModelProduct product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelSale getSale() {
        return sale;
    }

    public void setSale(ModelSale sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}

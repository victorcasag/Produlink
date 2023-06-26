package BigHouse.Produlink.LibraryProdulink.Sale;

import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.SaleProduct.ModelSaleProduct;

import java.time.LocalDate;

public class ModelSale {
    public Long id;
    public Long idClient;
    public ModelSaleProduct saleProduct;
    public LocalDate dateCreation;
    public double totalValue;
    public double discount;
    public String condition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public ModelSaleProduct getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(ModelSaleProduct saleProduct) {
        this.saleProduct = saleProduct;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}

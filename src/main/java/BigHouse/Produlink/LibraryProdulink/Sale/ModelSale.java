package BigHouse.Produlink.LibraryProdulink.Sale;

import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.Login.ModelLogin;
import BigHouse.Produlink.LibraryProdulink.SaleProduct.ModelSaleProduct;

import java.time.LocalDate;

public class ModelSale {
    public Long id;
    public ModelClient client;
    public ModelSaleProduct saleProduct;
    public ModelLogin login;
    public LocalDate dateCreation;
    public double totalValue;
    public double discount;
    public String condition;

    public ModelLogin getLogin() {
        return login;
    }

    public void setLogin(ModelLogin login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelClient getClient() {
        return client;
    }

    public void setClient(ModelClient client) {
        this.client = client;
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

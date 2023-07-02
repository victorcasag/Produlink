package BigHouse.Produlink.LibraryProdulink.Product;

import BigHouse.Produlink.LibraryProdulink.Category.ModelCategory;
import BigHouse.Produlink.LibraryProdulink.Vendor.ModelVendor;

import java.util.Date;

public class ModelProduct {
    public Long id;
    public String name;
    public double price;
    public String description;
    public double cost;
    public Long stock;
    public String unit;
    public Date dateOfPurchase;
    public Date dateLastUpdate;
    public double salePrice;
    public double profitMargin;
    public String urlPhoto;
    public long barCode;
    public ModelCategory category;
    public ModelVendor vendor;
    public String measure;

    public Date getDateLastUpdate() {
        return dateLastUpdate;
    }

    public ModelVendor getVendor() {
        return vendor;
    }

    public void setVendor(ModelVendor vendor) {
        this.vendor = vendor;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public void setCategory(ModelCategory category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date GetDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(Date dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public long getBarCode() {
        return barCode;
    }

    public void setBarCode(long barCode) {
        this.barCode = barCode;
    }
}

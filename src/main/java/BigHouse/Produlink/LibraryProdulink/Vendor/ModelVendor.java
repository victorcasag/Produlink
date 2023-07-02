package BigHouse.Produlink.LibraryProdulink.Vendor;

import BigHouse.Produlink.LibraryProdulink.Address.ModelAddress;

public class ModelVendor {
    public Long id;
    public Long cnpj;
    public String corporateName;
    public String email;
    public Long imei;
    public String phone;
    public String tradingName;
    public ModelAddress address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public ModelAddress getAddress() {
        return address;
    }

    public void setAddress(ModelAddress address) {
        this.address = address;
    }
}

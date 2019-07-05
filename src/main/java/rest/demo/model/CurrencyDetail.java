package rest.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_DETAIL")
public class CurrencyDetail {

    @Id
    @Column(name="CURRENCY_CODE")
    private String currencyCode;

    @Column(name="CURRENCY_NAME")
    private String currencyName;

    public CurrencyDetail() {
    }

    public CurrencyDetail(String currencyCode, String currencyName) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}

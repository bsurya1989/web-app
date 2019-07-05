
package rest.demo.client.model;

import java.util.List;

public class Example {

    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private List<CurrencyDetail> currencies = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<CurrencyDetail> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDetail> currencies) {
        this.currencies = currencies;
    }
}

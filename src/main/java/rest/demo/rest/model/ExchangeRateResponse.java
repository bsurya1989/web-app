package rest.demo.rest.model;

public class ExchangeRateResponse extends ResponseMessage {
    private String fromCurrency;
    private double sourceAmount;
    private String toCurrency;
    private double convertedAmount;

    public ExchangeRateResponse(String fromCurrency, double sourceAmount, String toCurrency, double convertedAmount) {
        this.fromCurrency = fromCurrency;
        this.sourceAmount = sourceAmount;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
    }

    public ExchangeRateResponse(int statusCode, String statusMessage, String responseMessage, String fromCurrency, double sourceAmount, String toCurrency, double convertedAmount) {
        super(statusCode, statusMessage, responseMessage);
        this.fromCurrency = fromCurrency;
        this.sourceAmount = sourceAmount;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}

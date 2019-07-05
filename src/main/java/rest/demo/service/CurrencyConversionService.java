package rest.demo.service;

import rest.demo.client.ExchangeRatesClient;
import rest.demo.client.RestCountriesClient;
import rest.demo.dao.CurrencyConversionDAO;
import rest.demo.dao.CurrencyDetailDAO;
import rest.demo.mapper.CurrencyMapper;
import rest.demo.model.CurrencyConversion;
import rest.demo.model.CurrencyDetail;
import rest.demo.rest.model.Currency;
import rest.demo.rest.model.ExchangeRateRequest;
import rest.demo.rest.model.ExchangeRateResponse;
import rest.demo.rest.model.GetCurrenciesResponse;
import rest.demo.rest.model.ResponseMessage;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class CurrencyConversionService {
    private static final Logger LOGGER = Logger.getLogger(CurrencyConversionService.class.getName());
    private static final int EXCHANGE_RATE_OLDER_THAN_SECONDS = 900;

    @Inject
    CurrencyConversionDAO currencyConversionDAO;

    @Inject
    ExchangeRatesClient exchangeRatesClient;

    @Inject
    CurrencyDetailDAO currencyDetailDAO;

    @Inject
    RestCountriesClient restCountriesClient;

    @Transactional
    public ExchangeRateResponse convertCurrency(ExchangeRateRequest exchangeRateRequest) throws Exception {
        System.out.println("Inside Service:convertCurrency");
        String pKey = exchangeRateRequest.getFromCurrency().toUpperCase() + "-" + exchangeRateRequest.getToCurrency().toUpperCase();
        CurrencyConversion currencyConversion = currencyConversionDAO.find(pKey);
        if (mustFetchRate(currencyConversion)) {
            currencyConversion = fetchCurrencyConversion(exchangeRateRequest.getFromCurrency().toUpperCase(), exchangeRateRequest.getToCurrency().toUpperCase());
        }
        double convertedAmount = calculateConvertedAmount(exchangeRateRequest, currencyConversion);
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse(200, "Success", "Success",
                exchangeRateRequest.getFromCurrency().toUpperCase(), exchangeRateRequest.getSourceAmount(),
                exchangeRateRequest.getToCurrency().toUpperCase(), convertedAmount);
        return exchangeRateResponse;
    }

    private double calculateConvertedAmount(ExchangeRateRequest exchangeRateRequest, CurrencyConversion currencyConversion) {
        return exchangeRateRequest.getSourceAmount() * currencyConversion.getConversionRate();
    }

    private boolean mustFetchRate(CurrencyConversion currencyConversion) {
        return currencyConversion == null || exchangeRateIsOld(currencyConversion.getUpdatedDate(), EXCHANGE_RATE_OLDER_THAN_SECONDS);
    }

    private CurrencyConversion fetchCurrencyConversion(String fromCurrency, String toCurrency) throws Exception {
        double currencyRate = exchangeRatesClient.webserviceCall(fromCurrency, toCurrency);
        return saveExchangeRate(fromCurrency, toCurrency, currencyRate);
    }

    private CurrencyConversion saveExchangeRate(String fromCurrency, String toCurrency, double currencyRate) {
        String pKey = fromCurrency.toUpperCase() + "-" + toCurrency.toUpperCase();
        return currencyConversionDAO.update(new CurrencyConversion(pKey, currencyRate, new Timestamp(new Date().getTime())));
    }

    private boolean exchangeRateIsOld(Timestamp date, int numberOfSeconds) {
        long milliseconds = System.currentTimeMillis() - date.getTime();
        int seconds = (int) milliseconds / 1000;
        System.out.println("Number of seconds old = " + seconds);
        return (seconds >= numberOfSeconds);
    }


    public GetCurrenciesResponse findAllCurrencies() {
        List<CurrencyDetail> currencyDetails = this.currencyDetailDAO.findAll();
        GetCurrenciesResponse getCurrenciesResponse = new GetCurrenciesResponse();
        getCurrenciesResponse.setCurrencies(CurrencyMapper.mapResponse(currencyDetails));
        return getCurrenciesResponse;
    }

    @Transactional
    public ResponseMessage saveCurrencyDetails() throws Exception{
        List<Currency> currencyList = restCountriesClient.webserviceCall();
        List<CurrencyDetail> currencyDetailList = CurrencyMapper.mapRequest(currencyList);
        for (CurrencyDetail currencyDetail : currencyDetailList) {
            System.out.println(currencyDetail.getCurrencyCode() + "<===>" + currencyDetail.getCurrencyName());
            currencyDetailDAO.update(currencyDetail);
        }
        return new ResponseMessage(200, "Success", "Successfully Saved");
    }
}



package rest.demo.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import rest.demo.client.model.CurrencyDetail;
import rest.demo.client.model.Example;
import rest.demo.client.model.ExchangeRate;

import java.util.List;

public class MyAppCurrency {
    public static void main(String... args) {
        IRestCountries iRestCountries = Feign.builder()
                .decoder(new GsonDecoder())
                .target(IRestCountries.class, "https://restcountries.eu");

        List<Example> currencies = iRestCountries.findAll();
        System.out.println(currencies.size());
        for (Example country : currencies) {
            for (CurrencyDetail curr : country.getCurrencies()) {
                if (curr.getCode() == null || curr.getName() == null) {
                    break;
                }
                System.out.println(curr.getCode() + "===" + curr.getName());
                break;
            }
        }


        IExchangeRates iExchangeRates = Feign.builder()
                .decoder(new GsonDecoder())
                .target(IExchangeRates.class, "https://api.exchangeratesapi.io");
        ExchangeRate test = iExchangeRates.findAll("ZAR");
        System.out.println(test.getRates().getINR());

    }
}

package rest.demo.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import rest.demo.client.model.CurrencyDetail;
import rest.demo.client.model.Example;
import rest.demo.rest.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class RestCountriesClient {
    private static final String REST_COUNTRIES_URL = "http://restcountries.eu";

    public List<Currency> webserviceCall() throws Exception {
        List<Currency> currenciesList = new ArrayList<>();
        IRestCountries github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(IRestCountries.class, REST_COUNTRIES_URL);

        // Fetch and print a list of the currencies to this library.
        List<Example> currencies = github.findAll();
        System.out.println(currencies.size());
        for (Example country : currencies) {
            for (CurrencyDetail curr : country.getCurrencies()) {
                if (curr.getCode() == null || curr.getName() == null) break;
                currenciesList.add(new Currency(curr.getCode(),curr.getName()));
                break;
            }
        }
        return currenciesList;
    }

}

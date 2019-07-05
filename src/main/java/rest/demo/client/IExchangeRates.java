package rest.demo.client;

import feign.Param;
import feign.RequestLine;
import rest.demo.client.model.ExchangeRate;

public interface IExchangeRates {

    @RequestLine("GET /latest?base={baseCurrency}")
    ExchangeRate findAll(@Param("baseCurrency") String baseCurrency);
}

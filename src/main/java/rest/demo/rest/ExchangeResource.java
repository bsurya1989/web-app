package rest.demo.rest;

import rest.demo.rest.model.ExchangeRateRequest;
import rest.demo.rest.model.ExchangeRateResponse;
import rest.demo.service.CurrencyConversionService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/exchange-rate")
public class ExchangeResource {

    private static final Logger LOGGER = Logger.getLogger(ExchangeResource.class.getName());

    @Inject
    CurrencyConversionService currencyConversionService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ExchangeRateResponse convertCurrency(@NotNull ExchangeRateRequest exchangeRateRequest)  {
        try {
            return currencyConversionService.convertCurrency(exchangeRateRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return new ExchangeRateResponse(500, "Failed", e.getMessage(), null, 0, null, 0);
        }
    }

}


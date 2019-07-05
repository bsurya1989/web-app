package rest.demo.rest;

import rest.demo.rest.model.GetCurrenciesResponse;
import rest.demo.rest.model.ResponseMessage;
import rest.demo.service.CurrencyConversionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/currencies")
public class CurrencyResource {

    @Inject
    CurrencyConversionService currencyConversionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetCurrenciesResponse listCurrencies() {
        return currencyConversionService.findAllCurrencies();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage saveCurrencies() {
        try {
            return currencyConversionService.saveCurrencyDetails();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(500, "Failed", e.getMessage());
        }
    }
}

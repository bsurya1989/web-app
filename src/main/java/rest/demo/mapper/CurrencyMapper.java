package rest.demo.mapper;

import rest.demo.model.CurrencyDetail;
import rest.demo.rest.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyMapper {
    public static List<Currency> mapResponse(List<CurrencyDetail> currencyDetails) {
        List<Currency> currencyList = new ArrayList<>();
        for (CurrencyDetail curr : currencyDetails) {
            currencyList.add(new Currency(curr.getCurrencyCode(), curr.getCurrencyName()));
        }
        return currencyList;
    }

    public static List<CurrencyDetail> mapRequest(List<Currency> currencyList) {
        List<CurrencyDetail> currencyDetailList = new ArrayList<>();
        for (Currency curr: currencyList) {
            currencyDetailList.add(new CurrencyDetail(curr.getCurrencyCode(), curr.getCurrencyName()));
        }
        return currencyDetailList;
    }
}

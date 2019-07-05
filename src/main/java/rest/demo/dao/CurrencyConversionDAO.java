package rest.demo.dao;

import rest.demo.model.CurrencyConversion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

public class CurrencyConversionDAO {
    private static final Logger LOGGER = Logger.getLogger(CurrencyConversionDAO.class.getName());

    @PersistenceContext(name = "demo")
    EntityManager entityManager;

    public CurrencyConversion find(String pKey) {
        CurrencyConversion currencyConversion = entityManager.find(CurrencyConversion.class, pKey);
        return currencyConversion;
    }

    public CurrencyConversion update(CurrencyConversion currencyConversion) {
        entityManager.merge(currencyConversion);
        return currencyConversion;
    }

    public CurrencyConversion save(CurrencyConversion currencyConversion) {
        entityManager.persist(currencyConversion);
        return currencyConversion;
    }
}


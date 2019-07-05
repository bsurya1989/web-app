package rest.demo.dao;

import rest.demo.model.CurrencyDetail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

public class CurrencyDetailDAO {
    private static final Logger LOGGER = Logger.getLogger(CurrencyDetailDAO.class.getName());

    @PersistenceContext(name = "demo")
    EntityManager entityManager;

    public CurrencyDetail find(String pKey) {
        CurrencyDetail currency = entityManager.find(CurrencyDetail.class, pKey);
        return currency;
    }

    public List<CurrencyDetail> findAll() {
        List<CurrencyDetail> listCurrencies = entityManager.createNativeQuery("SELECT e.* FROM CURRENCY_DETAIL e", CurrencyDetail.class).getResultList();
        return listCurrencies;
    }

    public CurrencyDetail update(CurrencyDetail currencyDetail) {
        entityManager.merge(currencyDetail);
        return currencyDetail;
    }
}

package rest.demo.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import rest.demo.client.model.ExchangeRate;
import rest.demo.client.model.Rates;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class ExchangeRatesClient {
    private static final String EXCHANGE_RATES_URL = "https://api.exchangeratesapi.io";

    public double webserviceCall(String fromCurrency, String toCurrency) throws Exception {
        trustCertificates();
        IExchangeRates iExchangeRates = Feign.builder()
                .decoder(new GsonDecoder())
                .target(IExchangeRates.class, EXCHANGE_RATES_URL);
        ExchangeRate test = iExchangeRates.findAll(fromCurrency);
        System.out.println(test.getRates().getINR());
        return fetchExchangeRate(toCurrency, test.getRates());
    }

    private double fetchExchangeRate(String toCurrency, Rates rates) {
        if (toCurrency.equalsIgnoreCase("INR")) {
            return rates.getINR();
        }
        if (toCurrency.equalsIgnoreCase("USD")) {
            return rates.getUSD();
        }
        return 0;
    }

    public void trustCertificates() throws Exception {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}

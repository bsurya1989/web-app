package rest.demo.client;

import feign.RequestLine;
import rest.demo.client.model.Example;

import java.util.List;

public interface IRestCountries {

    @RequestLine("GET /rest/v2/all")
    List<Example> findAll();
}

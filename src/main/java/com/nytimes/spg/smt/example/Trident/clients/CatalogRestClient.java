package com.nytimes.spg.smt.example.Trident.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nytimes.spg.smt.example.Trident.Product;
import com.nytimes.spg.smt.example.Trident.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class CatalogRestClient {
    private String server = "http://localhost:8090";
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public CatalogRestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public String get(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public List<Product> getProducList(List<Integer> productIds) {
        String productList = "";
        for(int i = 0;i <  productIds.size(); i++) {
            productList += productIds.get(i).toString();
            if ( i != (productIds.size() -1)) {
                productList += ",";
            }
        }
        String uri = "/products?ids="+productList;
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = responseEntity.getBody();
        List<Product> products = null;
        try {
            products = List.of(mapper.readValue(jsonStr, Product[].class));
        } catch(Exception e) {
            log.error("failure trying to read products");
        }
        return products;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}

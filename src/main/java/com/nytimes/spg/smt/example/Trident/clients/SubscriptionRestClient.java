package com.nytimes.spg.smt.example.Trident.clients;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nytimes.spg.smt.example.Trident.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class SubscriptionRestClient {
    private String server = "http://localhost:8082";
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public SubscriptionRestClient() {
            this.rest = new RestTemplate();
            this.headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "*/*");
    }


    public List<Subscription> getSubscriptions() {
        String subscriptionsURI = "/subscriptions";
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + subscriptionsURI, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = responseEntity.getBody();
        List<Subscription> subscriptions = null;
        try {
            subscriptions = List.of(mapper.readValue(jsonStr, Subscription[].class));
        } catch(Exception e) {
            log.error("failure trying to read subscriptions");
        }

        return subscriptions;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}

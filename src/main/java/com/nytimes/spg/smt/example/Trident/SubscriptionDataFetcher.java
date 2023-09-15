package com.nytimes.spg.smt.example.Trident;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.nytimes.spg.smt.example.Trident.clients.CatalogRestClient;
import com.nytimes.spg.smt.example.Trident.clients.SubscriptionRestClient;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DgsComponent

@Slf4j
public class SubscriptionDataFetcher {
    SubscriptionRestClient subscriptionClient;
    CatalogRestClient catalogRestClient;

    public SubscriptionDataFetcher() {
        subscriptionClient = new SubscriptionRestClient();
    }

    @DgsQuery
    public List<Subscription> subscriptions(@InputArgument String titleFilter) {
        String result = subscriptionClient.get("/subscriptions");
        ObjectMapper mapper = new ObjectMapper();
        List<Subscription> subscriptions = null;
        try {
            subscriptions = List.of(mapper.readValue(result, Subscription[].class));
            for(Subscription aSub : subscriptions) {
                int productId = aSub.getProducts().get(0);
            }
        }catch (Exception e) {
            log.info("Something went horribley wrong");
        }
        return subscriptions;

    }

}

package com.nytimes.spg.smt.example.Trident;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.*;
import com.nytimes.spg.smt.example.Trident.clients.CatalogRestClient;
import com.nytimes.spg.smt.example.Trident.clients.SubscriptionRestClient;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DgsComponent

@Slf4j
public class SubscriptionDataFetcher {
    SubscriptionRestClient subscriptionClient;

    CatalogService catalogService;

    public SubscriptionDataFetcher( CatalogService catalogService) {
        this.subscriptionClient = new SubscriptionRestClient();
        this.catalogService = catalogService;
    }

    @DgsQuery
    public List<Subscription> subscriptions(@InputArgument String titleFilter) {
        List<Subscription> subscriptions = subscriptionClient.getSubscriptions();
        return subscriptions;
    }

    @DgsData(parentType ="Subscription", field="products")
    public List<Product> products(DgsDataFetchingEnvironment dfe) {
       Subscription subscription = dfe.getSource();
       List<Integer> productIds = subscription.getProducts();
       List<Product> products = catalogService.getProductsForId(productIds);
       return products;
    }
}

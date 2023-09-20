package com.nytimes.spg.smt.example.Trident;

import com.nytimes.spg.smt.example.Trident.clients.CatalogRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private CatalogRestClient catalogRestClient;

    public CatalogService() {
        catalogRestClient = new CatalogRestClient();
    }


    public List<Product> getProductsForId(List<Integer> productIds) {
        List<Product> products = catalogRestClient.getProducList(productIds);
        return products;
    }
}

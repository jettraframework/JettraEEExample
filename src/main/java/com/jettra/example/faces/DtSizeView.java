package com.jettra.example.faces;

import com.jettra.example.model.Product;
import com.jettra.example.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Backing bean para PrimeFaces Showcase: DataTable - Size
 */
@Named("dtSizeView")
@ApplicationScoped
public class DtSizeView implements Serializable {

    private List<Product> products;

    @Inject
    private ProductService service;

    public DtSizeView() {
    }

    @PostConstruct
    public void init() {
        if (service == null) {
            service = new ProductService();
            service.init();
        }
        products = service.getProducts(5);
    }

    public List<Product> getProducts() {
        if (products == null && service != null) products = service.getProducts(5);
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

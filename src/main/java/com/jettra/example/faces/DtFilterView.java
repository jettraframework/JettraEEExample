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
 * Backing bean para PrimeFaces Showcase: DataTable - Filter
 */
@Named("dtFilterView")
@ApplicationScoped
public class DtFilterView implements Serializable {

    private List<Product> products;
    private List<Product> filteredProducts;

    @Inject
    private ProductService service;

    public DtFilterView() {
    }

    @PostConstruct
    public void init() {
        if (service == null) {
            service = new ProductService();
            service.init();
        }
        products = service.getProducts();
    }

    public List<Product> getProducts() {
        if (products == null && service != null) products = service.getProducts();
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<Product> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }
}

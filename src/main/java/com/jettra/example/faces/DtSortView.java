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
 * Backing bean para PrimeFaces Showcase: DataTable - Sort
 */
@Named("dtSortView")
@ApplicationScoped
public class DtSortView implements Serializable {

    private List<Product> products1;
    private List<Product> products2;

    @Inject
    private ProductService service;

    public DtSortView() {
    }

    @PostConstruct
    public void init() {
        if (service == null) {
            service = new ProductService();
            service.init();
        }
        products1 = service.getProducts(10);
        products2 = service.getProducts(10);
    }

    public List<Product> getProducts1() {
        if (products1 == null && service != null) products1 = service.getProducts(10);
        return products1;
    }

    public void setProducts1(List<Product> products1) {
        this.products1 = products1;
    }

    public List<Product> getProducts2() {
        if (products2 == null && service != null) products2 = service.getProducts(10);
        return products2;
    }

    public void setProducts2(List<Product> products2) {
        this.products2 = products2;
    }
}

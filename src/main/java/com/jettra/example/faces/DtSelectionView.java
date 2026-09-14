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
 * Backing bean para PrimeFaces Showcase: DataTable - Selection
 */
@Named("dtSelectionView")
@ApplicationScoped
public class DtSelectionView implements Serializable {

    private List<Product> products;
    private Product selectedProduct;
    private List<Product> selectedProducts;

    @Inject
    private ProductService service;

    public DtSelectionView() {
    }

    @PostConstruct
    public void init() {
        if (service == null) {
            service = new ProductService();
            service.init();
        }
        products = service.getProducts(10);
    }

    public List<Product> getProducts() {
        if (products == null && service != null) products = service.getProducts(10);
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
}

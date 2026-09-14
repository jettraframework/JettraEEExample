package com.jettra.example.faces;

import com.jettra.example.model.InventoryStatus;
import com.jettra.example.model.Product;
import com.jettra.example.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean para PrimeFaces Showcase: DataTable - CRUD
 */
@Named("dtCrudView")
@ApplicationScoped
public class DtCrudView implements Serializable {

    private List<Product> products;
    private Product selectedProduct;
    private List<Product> selectedProducts;

    @Inject
    private ProductService service;

    public DtCrudView() {
    }

    @PostConstruct
    public void init() {
        if (service == null) {
            service = new ProductService();
            service.init();
        }
        products = new ArrayList<>(service.getProducts(8));
        selectedProducts = new ArrayList<>();
        selectedProduct = new Product();
    }

    public List<Product> getProducts() {
        if (products == null && service != null) products = new ArrayList<>(service.getProducts(8));
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

    public void openNew() {
        this.selectedProduct = new Product();
        this.selectedProduct.setInventoryStatus(InventoryStatus.INSTOCK);
        this.selectedProduct.setRating(5);
    }

    public void saveProduct() {
        if (this.selectedProduct != null) {
            if (this.selectedProduct.getCode() == null || this.selectedProduct.getCode().isBlank()) {
                this.selectedProduct.setCode("prod_" + System.currentTimeMillis() % 100000);
            }
            if (!this.products.contains(this.selectedProduct)) {
                this.products.add(0, this.selectedProduct);
            }
        }
    }

    public void deleteProduct() {
        if (this.selectedProduct != null) {
            this.products.remove(this.selectedProduct);
            this.selectedProduct = null;
        }
    }

    public void deleteSelectedProducts() {
        if (this.selectedProducts != null) {
            this.products.removeAll(this.selectedProducts);
            this.selectedProducts.clear();
        }
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }
}

package com.jettra.example.service;

import com.jettra.example.model.InventoryStatus;
import com.jettra.example.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio CDI que suministra los productos oficiales del Showcase de PrimeFaces DataTable.
 */
@ApplicationScoped
public class ProductService implements Serializable {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1000, "f230fh0g3", "Bamboo Watch", "Product Description", "bamboo-watch.jpg", 65.0, "Accessories", 24, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1001, "nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72.0, "Accessories", 61, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1002, "zz21cz3c1", "Blue Band", "Product Description", "blue-band.jpg", 79.0, "Fitness", 2, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1003, "txap44747", "Blue T-Shirt", "Product Description", "blue-t-shirt.jpg", 29.0, "Clothing", 25, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1004, "cm230f032", "Gaming Set", "Product Description", "gaming-set.jpg", 299.0, "Electronics", 63, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1005, "al31hz846", "Golden Watch", "Product Description", "golden-watch.jpg", 199.0, "Accessories", 0, InventoryStatus.OUTOFSTOCK, 4));
        products.add(new Product(1006, "gw22q0123", "Green Earbuds", "Product Description", "green-earbuds.jpg", 89.0, "Electronics", 23, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1007, "tr344vg55", "Green T-Shirt", "Product Description", "green-t-shirt.jpg", 49.0, "Clothing", 74, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1008, "pxip23077", "Grey T-Shirt", "Product Description", "grey-t-shirt.jpg", 49.0, "Clothing", 0, InventoryStatus.OUTOFSTOCK, 3));
        products.add(new Product(1009, "fv54203ab", "Headphones", "Product Description", "headphones.jpg", 120.0, "Electronics", 8, InventoryStatus.LOWSTOCK, 4));
        products.add(new Product(1010, "h456wer53", "Galaxy Earrings", "Product Description", "galaxy-earrings.jpg", 34.0, "Accessories", 23, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1011, "av2231fwg", "Game Controller", "Product Description", "game-controller.jpg", 99.0, "Electronics", 2, InventoryStatus.LOWSTOCK, 4));
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> getProducts(int size) {
        if (size > products.size()) {
            return new ArrayList<>(products);
        }
        return new ArrayList<>(products.subList(0, size));
    }
}

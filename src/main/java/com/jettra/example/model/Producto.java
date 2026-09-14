package com.jettra.example.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Modelo de Producto con restricciones de validación Jakarta Validation y JettraRules.
 */
public class Producto {

    private String id;

    @NotBlank(message = "El nombre del producto no puede estar en blanco")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @Positive(message = "El precio debe ser un número positivo")
    private double precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    public Producto() {}

    public Producto(String id, String nombre, String categoria, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return nombre + " ($" + precio + ")";
    }
}

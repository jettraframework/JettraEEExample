package com.jettra.example.faces;

import com.jettra.example.model.Producto;
import com.jettra.example.service.ProductoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Managed Bean de Jakarta Faces para administración de productos en JettraEEExample.
 */
@Named("productoBean")
@ApplicationScoped
public class ProductoBean implements Serializable {

    @Inject
    private ProductoService productoService;

    private String nombre = "";
    private String categoria = "General";
    private double precio = 0.0;
    private int stock = 0;
    private String mensaje = "";

    public String guardar() {
        if (nombre == null || nombre.trim().isEmpty()) {
            this.mensaje = "⚠️ Error: El nombre del producto es obligatorio.";
            return "";
        }
        if (precio <= 0) {
            this.mensaje = "⚠️ Error: El precio debe ser mayor a 0.";
            return "";
        }

        Producto p = new Producto(null, nombre.trim(), categoria.trim(), precio, stock);
        productoService.guardar(p);

        this.mensaje = "✅ Producto '" + nombre + "' registrado con éxito.";
        this.nombre = "";
        this.categoria = "General";
        this.precio = 0.0;
        this.stock = 0;
        return "";
    }

    public String eliminar(String id) {
        boolean ok = productoService.eliminar(id);
        if (ok) {
            this.mensaje = "🗑️ Producto eliminado.";
        }
        return "";
    }

    public List<Producto> getProductos() {
        return productoService.listarTodos();
    }

    public int getTotalProductos() {
        return productoService.listarTodos().size();
    }

    public String getMoneda() {
        return productoService.getMoneda();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}

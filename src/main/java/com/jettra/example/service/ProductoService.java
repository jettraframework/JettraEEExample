package com.jettra.example.service;

import com.jettra.example.model.Producto;
import io.jettra.ee.core.IO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Servicio de Negocio CDI para Gestión de Productos en JettraEEExample.
 */
@ApplicationScoped
public class ProductoService {

    @Inject
    @ConfigProperty(name = "catalogo.moneda", defaultValue = "USD")
    private String moneda;

    @Inject
    @ConfigProperty(name = "catalogo.iva.porcentaje", defaultValue = "7.0")
    private double porcentajeIva;

    private final List<Producto> productos = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void initData() {
        productos.add(new Producto("P1", "Laptop Dell XPS 15", "Computación", 1850.00, 12));
        productos.add(new Producto("P2", "Teclado Mecánico RGB", "Accesorios", 95.50, 45));
        productos.add(new Producto("P3", "Monitor 4K UHD 27\"", "Pantallas", 420.00, 18));
        productos.add(new Producto("P4", "Mouse Ergonómico Inalámbrico", "Accesorios", 45.00, 30));
        IO.info("ProductoService: Inicializado catálogo de productos (" + productos.size() + " ítems en " + moneda + ").");
    }

    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }

    public Optional<Producto> buscarPorId(String id) {
        return productos.stream().filter(p -> p.getId().equalsIgnoreCase(id)).findFirst();
    }

    public Producto guardar(Producto nuevo) {
        if (nuevo.getId() == null || nuevo.getId().isBlank()) {
            nuevo.setId("P" + (productos.size() + 1));
        }
        productos.removeIf(p -> p.getId().equalsIgnoreCase(nuevo.getId()));
        productos.add(nuevo);
        IO.success("Producto guardado exitosamente: " + nuevo.getNombre());
        return nuevo;
    }

    public boolean eliminar(String id) {
        return productos.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    public double calcularPrecioConIva(double precioBase) {
        return precioBase * (1.0 + (porcentajeIva / 100.0));
    }

    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "syncBackup")
    public String sincronizarConInventarioCentral() {
        IO.info("Sincronizando inventario central...");
        return "Sincronizado OK (" + productos.size() + " productos procesados)";
    }

    public String syncBackup() {
        IO.warn("Inventario central no disponible. Se activó modo respaldo offline.");
        return "Modo Respaldo / Sincronización diferida activada";
    }

    public String getMoneda() {
        return moneda;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }
}

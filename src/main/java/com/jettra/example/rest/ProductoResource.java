package com.jettra.example.rest;

import com.jettra.example.model.Producto;
import com.jettra.example.service.ProductoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

/**
 * Recurso Jakarta REST para catálogo de productos en JettraEEExample.
 */
@Path("/api/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Catálogo de Productos", description = "Endpoints REST para gestión de inventario y precios")
public class ProductoResource {

    @Inject
    private ProductoService productoService;

    @GET
    @Operation(summary = "Listar todos los productos", description = "Retorna la colección completa de productos disponibles")
    @APIResponse(responseCode = "200", description = "Productos listados exitosamente")
    public Response listar() {
        List<Producto> lista = productoService.listarTodos();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Retorna un producto según su identificador único")
    @APIResponse(responseCode = "200", description = "Producto encontrado")
    @APIResponse(responseCode = "404", description = "Producto no encontrado")
    public Response obtenerPorId(@PathParam("id") String id) {
        return productoService.buscarPorId(id)
                .map(p -> Response.ok(p).build())
                .orElse(Response.status(404).entity(Map.of("error", "Producto no encontrado: " + id)).build());
    }

    @POST
    @Operation(summary = "Crear o actualizar producto", description = "Valida el producto y lo guarda en el catálogo")
    @APIResponse(responseCode = "201", description = "Producto creado")
    @APIResponse(responseCode = "400", description = "Validación fallida")
    public Response crear(@Valid Producto producto) {
        Producto guardado = productoService.guardar(producto);
        return Response.status(201).entity(guardado).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar producto por ID")
    @APIResponse(responseCode = "200", description = "Producto eliminado")
    @APIResponse(responseCode = "404", description = "Producto no encontrado")
    public Response eliminar(@PathParam("id") String id) {
        boolean ok = productoService.eliminar(id);
        if (ok) {
            return Response.ok(Map.of("mensaje", "Producto eliminado con éxito", "id", id)).build();
        }
        return Response.status(404).entity(Map.of("error", "No existe el producto " + id)).build();
    }

    @GET
    @Path("/sincronizar")
    @Operation(summary = "Sincronizar inventario", description = "Ejecuta sincronización con MicroProfile Fault Tolerance")
    public Response sincronizar() {
        String resultado = productoService.sincronizarConInventarioCentral();
        return Response.ok(Map.of("resultado", resultado)).build();
    }
}

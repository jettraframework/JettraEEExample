package com.jettra.example.flux;

import com.jettra.example.model.Producto;
import com.jettra.example.service.ProductoService;
import com.sun.net.httpserver.HttpExchange;
import io.jettra.core.server.Page;
import io.jettra.ee.jakarta.cdi.JettraCDIContainer;
import io.jettra.flux.core.Widget;
import io.jettra.flux.pages.FluxBaseHandler;
import io.jettra.flux.widgets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Vista de catálogo de productos renderizada con JettraFlux.
 */
@io.jettra.core.login.NoLoginRequired
@Page(path = "/flux/catalogo")
public class CatalogoFluxPage extends FluxBaseHandler {

    @Override
    protected String getTitle() {
        return "Catálogo JettraFlux";
    }

    @Override
    protected Widget buildUI(HttpExchange exchange, Map<String, String> params, String currentTheme) {
        ProductoService service = JettraCDIContainer.getInstance().getBean(ProductoService.class);
        List<Producto> productos = service != null ? service.listarTodos() : List.of();

        List<Widget> rows = new ArrayList<>();
        rows.add(Header.of(1, "📦 Catálogo de Productos (JettraFlux)"));
        rows.add(Paragraph.of("Lista obtenida desde el servicio CDI ProductoService."));

        for (Producto p : productos) {
            rows.add(io.jettra.flux.widgets.Row.of(
                    Paragraph.of("• " + p.getNombre() + " (" + p.getCategoria() + ") - $" + p.getPrecio() + " [Stock: " + p.getStock() + "]")
            ));
        }

        rows.add(Paragraph.of(" "));
        rows.add(io.jettra.flux.widgets.Link.of("← Volver a Inicio (/index.xhtml)", "/index.xhtml"));

        return Center.of(Column.of(rows.toArray(new Widget[0])));
    }
}

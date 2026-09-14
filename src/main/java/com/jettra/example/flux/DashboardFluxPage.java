package com.jettra.example.flux;

import com.sun.net.httpserver.HttpExchange;
import io.jettra.core.server.Page;
import io.jettra.flux.core.Widget;
import io.jettra.flux.pages.FluxBaseHandler;
import io.jettra.flux.widgets.*;

import java.util.Map;

/**
 * Página de Dashboard interactivo construida con componentes reactivos JettraFlux.
 */
@io.jettra.core.login.NoLoginRequired
@Page(path = "/flux/dashboard")
public class DashboardFluxPage extends FluxBaseHandler {

    @Override
    protected String getTitle() {
        return "JettraFlux - Panel de Control";
    }

    @Override
    protected Widget buildUI(HttpExchange exchange, Map<String, String> params, String currentTheme) {
        return Center.of(
            Column.of(
                Header.of(1, "🚀 Panel JettraFlux en JettraEE"),
                Paragraph.of("Esta interfaz está renderizada por el motor reactivo nativo de JettraFlux dentro de JettraEE."),
                Notification.of(
                    Paragraph.of("✨ Servidor operando con Hilos Virtuales (Java 25 Loom) y compatibilidad Eclipse MicroProfile.")
                ),
                io.jettra.flux.widgets.Row.of(
                    io.jettra.flux.widgets.Link.of("Ir a Jakarta Faces (/productos.xhtml)", "/productos.xhtml"),
                    Paragraph.of(" | "),
                    io.jettra.flux.widgets.Link.of("Ver Swagger UI (/q/swagger-ui)", "/q/swagger-ui"),
                    Paragraph.of(" | "),
                    io.jettra.flux.widgets.Link.of("Catálogo JettraFlux (/flux/catalogo)", "/flux/catalogo")
                )
            )
        );
    }
}

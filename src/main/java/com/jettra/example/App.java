package com.jettra.example;

import io.jettra.ee.JettraEE;
import io.jettra.ee.core.IO;
import io.jettra.ee.server.JettraEEServer;

/**
 * Punto de entrada principal para JettraEEExample.
 * Inicia el servidor JettraEE con autodescubrimiento de Jakarta REST, CDI,
 * MicroProfile Health, OpenAPI, interfaces Jakarta Faces y JettraFlux.
 */
public class App {

    public static void main(String[] args) {
        IO.info("Iniciando JettraEEExample...");

        JettraEEServer server = JettraEE.start(App.class, args);

        IO.success("=================================================================");
        IO.success("JettraEEExample iniciado y disponible en:");
        IO.success(" • Portal Web Principal:      http://localhost:" + server.getPort() + "/index.xhtml");
        IO.success(" • PrimeFaces Showcase:       http://localhost:" + server.getPort() + "/ui/data/datatable/basic.xhtml");
        IO.success(" • Interfaz Jakarta Faces:    http://localhost:" + server.getPort() + "/productos.xhtml");
        IO.success(" • Interfaz JettraFlux:       http://localhost:" + server.getPort() + "/flux/dashboard");
        IO.success(" • Catálogo JettraFlux:       http://localhost:" + server.getPort() + "/flux/catalogo");
        IO.success(" • API Jakarta REST:          http://localhost:" + server.getPort() + "/api/productos");
        IO.success(" • Swagger UI (OpenAPI):      http://localhost:" + server.getPort() + "/q/swagger-ui");
        IO.success(" • MicroProfile Health:       http://localhost:" + server.getPort() + "/q/health");
        IO.success(" • MicroProfile Metrics:      http://localhost:" + server.getPort() + "/q/metrics");
        IO.success("=================================================================");
    }
}

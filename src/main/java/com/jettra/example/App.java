package com.jettra.example;

import io.jettra.ee.JettraEE;
import io.jettra.ee.core.IO;
import io.jettra.ee.server.JettraEEServer;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

/**
 * Punto de entrada principal para JettraEEExample.
 * Inicia el servidor JettraEE con autodescubrimiento de Jakarta REST, CDI,
 * MicroProfile Health, OpenAPI, interfaces Jakarta Faces y JettraFlux.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "JettraEE Example API",
                version = "1.0.0",
                description = "Documentación interactiva OpenAPI 3.1 con autenticación JWT Bearer para JettraEEExample"
        ),
        security = @SecurityRequirement(name = "BearerAuth")
)
@SecurityScheme(
        securitySchemeName = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Introduzca su token JWT (Bearer Token) para autorizar el acceso"
)
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

package com.jettra.example;

import io.jettra.ee.JettraEE;
import io.jettra.ee.server.JettraEEServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas automatizadas de integración para JettraEEExample.
 */
public class AppTest {

    private static JettraEEServer server;
    private static final int TEST_PORT = 19090;
    private static HttpClient client;

    @BeforeAll
    public static void setUp() {
        client = HttpClient.newHttpClient();

        server = JettraEE.builder()
                .port(TEST_PORT)
                .contextPath("/")
                .scanPackages("com.jettra.example")
                .build();

        server.start();
    }

    @AfterAll
    public static void tearDown() {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void testServerIsRunning() {
        assertTrue(server.isRunning(), "El servidor JettraEE debería estar ejecutándose");
    }

    @Test
    public void testJakartaRestListarProductos() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/api/productos"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Laptop Dell XPS 15"));
        assertTrue(response.body().contains("Computación"));
    }

    @Test
    public void testJakartaFacesRenderXhtml() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/productos.xhtml"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.headers().firstValue("Content-Type").orElse("").contains("text/html"));
        assertTrue(response.body().contains("Jakarta Faces"));
        assertTrue(response.body().contains("Laptop Dell XPS 15"));
        assertTrue(response.body().contains("<form"));
    }

    @Test
    public void testJakartaFacesPostFormAction() throws Exception {
        String formData = "productoBean.nombre=Mouse+Gamer+RGB&productoBean.categoria=Periféricos&productoBean.precio=60.0&productoBean.stock=25&jakarta.faces.action=%23%7BproductoBean.guardar%7D";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/productos.xhtml"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("registrado con éxito"));
        assertTrue(response.body().contains("Mouse Gamer RGB"));
    }

    @Test
    public void testJettraFluxDashboardPage() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/flux/dashboard"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("JettraFlux"));
        assertTrue(response.body().contains("Panel"));
    }

    @Test
    public void testJettraFluxCatalogoPage() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/flux/catalogo"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Catálogo de Productos"));
    }

    @Test
    public void testMicroProfileHealth() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/q/health"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("\"status\":\"UP\""));
        assertTrue(response.body().contains("JettraEE-Alive"));
        assertTrue(response.body().contains("InventarioService-Ready"));
    }

    @Test
    public void testMicroProfileMetrics() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/q/metrics"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("base_memory_usedHeap_bytes"));
        assertTrue(response.body().contains("vendor_http_requests_total"));
    }

    @Test
    public void testMicroProfileOpenApiAndSwaggerUI() throws Exception {
        HttpRequest reqApi = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/q/openapi"))
                .GET()
                .build();
        HttpResponse<String> resApi = client.send(reqApi, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, resApi.statusCode());
        assertTrue(resApi.body().contains("\"openapi\":\"3.1.0\""));
        assertTrue(resApi.body().contains("/api/productos"));
        assertTrue(resApi.body().contains("\"securitySchemes\""), "Debe contener components.securitySchemes");
        assertTrue(resApi.body().contains("\"BearerAuth\""), "Debe contener el esquema BearerAuth");
        assertTrue(resApi.body().contains("\"scheme\":\"bearer\""), "Debe especificar esquema bearer");
        assertTrue(resApi.body().contains("\"bearerFormat\":\"JWT\""), "Debe especificar formato JWT");
        assertTrue(resApi.body().contains("\"security\""), "Debe contener requerimientos de seguridad globales");

        HttpRequest reqUi = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/q/swagger-ui"))
                .GET()
                .build();
        HttpResponse<String> resUi = client.send(reqUi, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, resUi.statusCode());
        assertTrue(resUi.body().contains("Swagger UI"));
        assertTrue(resUi.body().contains("persistAuthorization: true"));
    }

    @Test
    public void testPrimeFacesShowcaseDataTableBasic() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/ui/data/datatable/basic.xhtml"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.headers().firstValue("Content-Type").orElse("").contains("text/html"));
        
        String body = response.body();
        assertTrue(body.contains("PrimeFaces Showcase"), "Debe contener el título de PrimeFaces Showcase");
        assertTrue(body.contains("ui-datatable"), "Debe contener clases oficiales ui-datatable de PrimeFaces");
        assertTrue(body.contains("Code"), "Debe contener columna Code");
        assertTrue(body.contains("Name"), "Debe contener columna Name");
        assertTrue(body.contains("Category"), "Debe contener columna Category");
        assertTrue(body.contains("Quantity"), "Debe contener columna Quantity");
        assertTrue(body.contains("Price"), "Debe contener columna Price");
        assertTrue(body.contains("Rating"), "Debe contener columna Rating");
        assertTrue(body.contains("Status"), "Debe contener columna Status");

        // Validar productos del showcase
        assertTrue(body.contains("Bamboo Watch"), "Debe incluir el producto Bamboo Watch del showcase");
        assertTrue(body.contains("Black Watch"), "Debe incluir el producto Black Watch del showcase");
        assertTrue(body.contains("f230fh0g3"), "Debe incluir el código f230fh0g3");
        assertTrue(body.contains("Accessories"), "Debe incluir categoría Accessories");

        // Validar componentes PrimeFaces renderizados
        assertTrue(body.contains("p-tag"), "Debe renderizar componentes p-tag");
        assertTrue(body.contains("p-rating"), "Debe renderizar componentes p-rating");
    }

    @Test
    public void testPrimeFacesDataTableShortcutUrl() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/datatable-basic.xhtml"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Bamboo Watch"));
        assertTrue(response.body().contains("ui-datatable"));
    }

    @Test
    public void testAllPrimeFacesDataTableShowcasePages() throws Exception {
        String[] pages = {
                "/ui/data/datatable/basic.xhtml",
                "/ui/data/datatable/facets.xhtml",
                "/ui/data/datatable/paginator.xhtml",
                "/ui/data/datatable/sort.xhtml",
                "/ui/data/datatable/filter.xhtml",
                "/ui/data/datatable/selection.xhtml",
                "/ui/data/datatable/size.xhtml",
                "/ui/data/datatable/gridlines.xhtml",
                "/ui/data/datatable/striped.xhtml",
                "/ui/data/datatable/responsive.xhtml",
                "/ui/data/datatable/edit.xhtml",
                "/ui/data/datatable/crud.xhtml"
        };

        for (String page : pages) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:" + TEST_PORT + page))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode(), "La página " + page + " debería retornar HTTP 200");
            assertTrue(response.body().contains("PrimeFaces Showcase"), "La página " + page + " debe contener PrimeFaces Showcase");
            assertTrue(response.body().contains("ui-datatable"), "La página " + page + " debe renderizar clases ui-datatable");
        }
    }

    @Test
    public void testPrimeFacesCrudShowcaseFeatures() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/ui/data/datatable/crud.xhtml"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        String body = response.body();

        assertTrue(body.contains("ui-toolbar"), "CRUD debe contener ui-toolbar");
        assertTrue(body.contains("manageProductDialog"), "CRUD debe contener diálogo modal manageProductDialog");
        assertTrue(body.contains("Manage Products"), "CRUD debe contener título de tabla");
        assertTrue(body.contains("New"), "CRUD debe contener botón New");
        assertTrue(body.contains("Delete"), "CRUD debe contener botón Delete");
    }

    @Test
    public void testPrimeFacesFacetsAndPaginatorFeatures() throws Exception {
        // Facets
        HttpRequest reqFacets = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/ui/data/datatable/facets.xhtml"))
                .GET().build();
        HttpResponse<String> resFacets = client.send(reqFacets, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, resFacets.statusCode());
        assertTrue(resFacets.body().contains("List of Products"), "Facets debe contener header facet");
        assertTrue(resFacets.body().contains("In total there are"), "Facets debe contener footer facet");

        // Paginator
        HttpRequest reqPaginator = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/ui/data/datatable/paginator.xhtml"))
                .GET().build();
        HttpResponse<String> resPaginator = client.send(reqPaginator, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, resPaginator.statusCode());
        assertTrue(resPaginator.body().contains("ui-paginator"), "Paginator debe contener contenedor ui-paginator");
    }

    @Test
    public void testRootWelcomeFileResolution() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Portal de Inicio") || response.body().contains("JettraEE"),
                "La raíz '/' debe resolver automáticamente el welcome file index.xhtml");
    }

    @Test
    public void testProtectedWebInfForbidden() throws Exception {
        HttpRequest reqWebXml = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/WEB-INF/web.xml"))
                .GET()
                .build();

        HttpResponse<String> resWebXml = client.send(reqWebXml, HttpResponse.BodyHandlers.ofString());
        assertEquals(403, resWebXml.statusCode(), "El acceso a /WEB-INF/web.xml debe estar protegido con HTTP 403");

        HttpRequest reqBeansXml = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/WEB-INF/beans.xml"))
                .GET()
                .build();

        HttpResponse<String> resBeansXml = client.send(reqBeansXml, HttpResponse.BodyHandlers.ofString());
        assertEquals(403, resBeansXml.statusCode(), "El acceso a /WEB-INF/beans.xml debe estar protegido con HTTP 403");
    }

    @Test
    public void testProtectedMetaInfForbidden() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/META-INF/beans.xml"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(403, response.statusCode(), "El acceso a /META-INF/* debe estar protegido con HTTP 403");
    }

    @Test
    public void testStaticWebappResourceServing() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/css/test.css"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertTrue(response.headers().firstValue("Content-Type").orElse("").contains("text/css"));
        assertTrue(response.body().contains(".test-style"));
    }

    @Test
    public void testStandardJakartaEEDescriptorsLoaded() {
        assertNotNull(server.getWebResourceManager(), "WebResourceManager debe estar inicializado en el servidor");
        assertEquals("src/main/webapp", server.getWebResourceManager().getWebappRoot(), "El Document Root debe ser src/main/webapp");
        assertTrue(server.getWebResourceManager().getWelcomeFiles().contains("index.xhtml"), "web.xml debe haber configurado index.xhtml como welcome file");

        assertEquals("annotated", io.jettra.ee.jakarta.cdi.JettraCDIContainer.getBeanDiscoveryMode(),
                "beans.xml debe haber configurado bean-discovery-mode='annotated'");
    }

    @Test
    public void testJettraSecurityDBLoginAndJwtAuth() throws Exception {
        // 1. Intentar acceder a endpoint protegido sin token -> 401
        String nuevoProdJson = "{\"id\":\"PROD-SEC-99\",\"nombre\":\"Teclado Mecánico RGB\",\"categoria\":\"Periféricos\",\"precio\":120.0,\"stock\":15}";
        HttpRequest postSinToken = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/api/productos"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(nuevoProdJson))
                .build();
        HttpResponse<String> resSinToken = client.send(postSinToken, HttpResponse.BodyHandlers.ofString());
        assertEquals(401, resSinToken.statusCode(), "La creación de producto sin token debe fallar con 401");

        // 2. Autenticarse contra JettraSecurityDB en /api/auth/login
        String loginBody = "{\"username\":\"admin\",\"password\":\"admin\"}";
        HttpRequest loginReq = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/api/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(loginBody))
                .build();
        HttpResponse<String> loginRes = client.send(loginReq, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, loginRes.statusCode(), "El login de admin debe ser 200");
        assertTrue(loginRes.body().contains("Bearer "), "Debe retornar un token Bearer");
        assertTrue(loginRes.body().contains("ADMIN"), "Debe tener rol ADMIN");

        // Extraer token
        String bodyStr = loginRes.body();
        int tokenIdx = bodyStr.indexOf("\"token\":\"");
        assertTrue(tokenIdx != -1);
        int start = tokenIdx + 9;
        int end = bodyStr.indexOf("\"", start);
        String bearerToken = bodyStr.substring(start, end);

        // 3. Probar /api/auth/me con el token
        HttpRequest meReq = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/api/auth/me"))
                .header("Authorization", bearerToken)
                .GET()
                .build();
        HttpResponse<String> meRes = client.send(meReq, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, meRes.statusCode());
        assertTrue(meRes.body().contains("\"username\":\"admin\""));

        // 4. Invocar endpoint protegido con rol ADMIN usando el token
        HttpRequest postConToken = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/api/productos"))
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .POST(HttpRequest.BodyPublishers.ofString(nuevoProdJson))
                .build();
        HttpResponse<String> resConToken = client.send(postConToken, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, resConToken.statusCode(), "La creación con token ADMIN debe ser exitosa (201)");
        assertTrue(resConToken.body().contains("Teclado Mecánico RGB"));

        // 5. Administrar usuarios en /api/security/users
        HttpRequest usersReq = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/api/security/users"))
                .header("Authorization", bearerToken)
                .GET()
                .build();
        HttpResponse<String> usersRes = client.send(usersReq, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, usersRes.statusCode(), "Listar usuarios con token ADMIN debe ser 200");
        assertTrue(usersRes.body().contains("\"username\":\"admin\""));
    }
}



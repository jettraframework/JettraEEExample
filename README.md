# JettraEEExample 🌟

Proyecto de demostración integral construido sobre **JettraEE**, el servidor super ligero compatible con **Jakarta EE 11/12** y **Eclipse MicroProfile**, con soporte nativo para **Jakarta Faces** y **JettraFlux**.

---

## 🎯 ¿Qué demuestra este proyecto?

1. **PrimeFaces Showcase (DataTable Basic)**:
   - Vista oficial en [basic.xhtml](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/resources/webapp/ui/data/datatable/basic.xhtml) (`/ui/data/datatable/basic.xhtml`).
   - Componentes PrimeFaces: `<p:dataTable>`, `<p:column headerText="...">`, `<p:tag>`, `<p:rating>`, `<p:badge>`.
   - Backing Bean CDI: [DtBasicView.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/faces/DtBasicView.java) y [Product.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/model/Product.java) con catálogo oficial de prueba (Bamboo Watch, Black Watch, Blue Band, etc.).
2. **Jakarta Faces (JSF / Facelets XHTML)**:
   - Plantilla Facelets moderna en [productos.xhtml](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/resources/webapp/productos.xhtml).
   - Componentes estándar: `<h:form>`, `<h:inputText>`, `<h:commandButton>`, `<h:dataTable>`, `<h:column>`.
   - Backing Bean CDI: [ProductoBean.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/faces/ProductoBean.java) con `@Named("productoBean")` y expresiones EL `#{productoBean.nombre}`, `#{productoBean.guardar}`.
3. **JettraFlux (UI Reactiva Nativa)**:
   - [DashboardFluxPage.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/flux/DashboardFluxPage.java) en `/flux/dashboard`.
   - [CatalogoFluxPage.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/flux/CatalogoFluxPage.java) en `/flux/catalogo`.
   - Widgets reactivos: `Center`, `Column`, `Row`, `Header`, `Paragraph`, `Notification`, `Link`.
4. **Jakarta REST (JAX-RS 3.1)**:
   - [ProductoResource.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/rest/ProductoResource.java) en `/api/productos`.
   - Operaciones GET, POST, DELETE y respuestas JSON automáticas.
5. **Jakarta CDI 4.1**:
   - [ProductoService.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/service/ProductoService.java) y [ProductService.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/service/ProductService.java) con `@ApplicationScoped`.
6. **Jakarta Validation y JettraRules**:
   - [Producto.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/model/Producto.java) con validaciones `@NotBlank`, `@Size`, `@Positive`, `@Min`.
7. **Eclipse MicroProfile**:
   - **Config 3.1**: Inyección de propiedades `@ConfigProperty(name = "catalogo.moneda")`.
   - **Health 4.0**: Sondas `@Liveness` y `@Readiness` en [AppHealthCheck.java](file:///home/avbravo/NetBeansProjects/jettrastack_local/JettraWorkspace/JettraEEExample/src/main/java/com/jettra/example/health/AppHealthCheck.java) accesibles en `/q/health`.
   - **Metrics 5.1**: Métricas Prometheus y JSON en `/q/metrics`.
   - **OpenAPI 3.1 & Swagger UI**: Documentación interactiva en `/q/swagger-ui` y `/q/openapi`.
   - **Fault Tolerance 4.0**: Resiliencia con `@Retry` y `@Fallback` en operaciones de sincronización.

---

## 🚀 Cómo Ejecutar el Proyecto

### 1. Compilar y Ejecutar Pruebas

```bash
mvn clean test
```

### 2. Iniciar el Servidor

```bash
mvn compile exec:java -Dexec.mainClass="com.jettra.example.App"
```

O empaquetando el JAR ejecutable:

```bash
mvn package
java -XX:+UseCompactObjectHeaders -jar target/JettraEEExample-1.0.0-SNAPSHOT.jar
```

El servidor iniciará en **~10 milisegundos** en el puerto `8080` (o puerto configurado en `application.properties`).

---

## 🌐 Enlaces y Rutas Disponibles

| Tecnología / Módulo | Ruta URL | Descripción |
|---|---|---|
| **Portal de Inicio** | `http://localhost:8080/index.xhtml` | Página principal con enlaces a todos los módulos |
| **PrimeFaces: Basic** | `http://localhost:8080/ui/data/datatable/basic.xhtml` | Tabla base con columnas, precios y ratings |
| **PrimeFaces: Facets** | `http://localhost:8080/ui/data/datatable/facets.xhtml` | Cabecera y pie de tabla con `<f:facet>` |
| **PrimeFaces: Paginator** | `http://localhost:8080/ui/data/datatable/paginator.xhtml` | Paginación interactiva configurable con selector de filas |
| **PrimeFaces: Sort** | `http://localhost:8080/ui/data/datatable/sort.xhtml` | Ordenamiento dinámico ascendente y descendente por columnas |
| **PrimeFaces: Filter** | `http://localhost:8080/ui/data/datatable/filter.xhtml` | Búsqueda global y filtros por columnas en tiempo real |
| **PrimeFaces: Selection** | `http://localhost:8080/ui/data/datatable/selection.xhtml` | Selección de filas con checkboxes individuales y select-all |
| **PrimeFaces: Size** | `http://localhost:8080/ui/data/datatable/size.xhtml` | Densidades visuales `small` y `large` |
| **PrimeFaces: Gridlines** | `http://localhost:8080/ui/data/datatable/gridlines.xhtml` | Cuadrícula con bordes verticales y horizontales |
| **PrimeFaces: Striped** | `http://localhost:8080/ui/data/datatable/striped.xhtml` | Filas alternas estilo cebra (`stripedRows="true"`) |
| **PrimeFaces: Responsive** | `http://localhost:8080/ui/data/datatable/responsive.xhtml` | Tabla adaptativa con scroll horizontal responsivo |
| **PrimeFaces: Edit** | `http://localhost:8080/ui/data/datatable/edit.xhtml` | Edición en fila / celda editable |
| **PrimeFaces: CRUD** | `http://localhost:8080/ui/data/datatable/crud.xhtml` | Gestión completa con Toolbar, Dialog modal y acciones |
| **Jakarta Faces UI** | `http://localhost:8080/productos.xhtml` | Interfaz Facelets con `<h:form>` y `<h:dataTable>` |
| **JettraFlux Dashboard** | `http://localhost:8080/flux/dashboard` | Panel de control con widgets reactivos JettraFlux |
| **JettraFlux Catálogo** | `http://localhost:8080/flux/catalogo` | Vista de productos renderizada con JettraFlux |
| **Jakarta REST API** | `http://localhost:8080/api/productos` | Endpoint JSON con CRUD de productos |
| **Swagger UI** | `http://localhost:8080/q/swagger-ui` | Explorador interactivo OpenAPI 3.1 |
| **MicroProfile Health** | `http://localhost:8080/q/health` | Estado de sondas Liveness y Readiness |
| **MicroProfile Metrics** | `http://localhost:8080/q/metrics` | Métricas Prometheus y contadores de peticiones |

---

## 🏗️ Estructura del Proyecto (100% Compatible con Especificaciones Jakarta EE)

La estructura sigue rigurosamente los estándares de **Jakarta EE 10/11** y **Eclipse MicroProfile**, permitiendo desplegar la aplicación sin ninguna modificación tanto en **JettraEE** como en **Payara Micro**, **Helidon** y **WildFly**:

```
JettraEEExample/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/jettra/example/
    │   │       ├── App.java                  # Main class (JettraEE.start)
    │   │       ├── faces/
    │   │       │   ├── ProductoBean.java     # Backing Bean Jakarta Faces (@Named)
    │   │       │   └── DtBasicView.java      # Showcase DataTable Bean
    │   │       ├── flux/
    │   │       │   ├── DashboardFluxPage.java # Página JettraFlux
    │   │       │   └── CatalogoFluxPage.java  # Catálogo JettraFlux
    │   │       ├── health/
    │   │       │   └── AppHealthCheck.java    # Sondas @Liveness y @Readiness
    │   │       ├── model/
    │   │       │   ├── Producto.java          # DTO con validaciones Jakarta
    │   │       │   └── Product.java           # Modelo PrimeFaces Showcase
    │   │       ├── rest/
    │   │       │   └── ProductoResource.java  # Recurso Jakarta REST (@Path)
    │   │       └── service/
    │   │           └── ProductoService.java   # Servicio CDI (@ApplicationScoped)
    │   ├── resources/
    │   │   ├── application.properties
    │   │   └── META-INF/
    │   │       ├── beans.xml                 # Descriptor CDI Classpath (Helidon/JAR)
    │   │       └── microprofile-config.properties # Configuración MicroProfile
    │   └── webapp/                           # Document Root Oficial de la Aplicación Web
    │       ├── WEB-INF/
    │       │   ├── web.xml                   # Descriptor Servlet / Welcome files
    │       │   ├── beans.xml                 # Descriptor CDI Web (WAR / Payara Micro)
    │       │   └── faces-config.xml          # Configuración Jakarta Faces 4.0
    │       ├── index.xhtml                   # Welcome file Facelets
    │       ├── productos.xhtml               # Interfaz Facelets
    │       ├── datatable-basic.xhtml         # Showcase DataTable
    │       └── ui/
    │           └── data/
    │               └── datatable/            # Showcase PrimeFaces completo
    └── test/
        └── java/
            └── com/jettra/example/
                └── AppTest.java               # 19 Pruebas de integración automatizadas
```

---

## 🔄 Portabilidad Multi-Servidor

El proyecto puede ser ejecutado directamente o empaquetado para diversos entornos sin alterar una sola línea de código:

### 1. Ejecución con JettraEE (Modo Ultraligero con Virtual Threads)
```bash
# Desarrollo rápido
mvn compile exec:java -Dexec.mainClass="com.jettra.example.App"

# O JAR ejecutable
mvn clean package
java -jar target/JettraEEExample-1.0.0-SNAPSHOT.jar
```

### 2. Despliegue en Payara Micro
```bash
# Generar WAR estándar
mvn clean package -Pwar

# Desplegar en Payara Micro
java -jar payara-micro.jar --deploy target/JettraEEExample-1.0.0-SNAPSHOT.war
```

### 3. Ejecución en Helidon / WildFly
- **Helidon MP**: Compatible de forma nativa a través de `src/main/resources/META-INF/beans.xml` y `microprofile-config.properties`.
- **WildFly**: Copiar el WAR generado en la carpeta `deployments/` del servidor de aplicaciones.


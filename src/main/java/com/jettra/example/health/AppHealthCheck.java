package com.jettra.example.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

/**
 * Sondas de salud Eclipse MicroProfile Health 4.0 para JettraEEExample.
 */
public class AppHealthCheck {

    @Liveness
    @ApplicationScoped
    public static class LiveProbe implements HealthCheck {
        @Override
        public HealthCheckResponse call() {
            return HealthCheckResponse.named("JettraEE-Alive")
                    .up()
                    .withData("engine", "JettraEE")
                    .withData("runtime", "Java 25 Loom")
                    .build();
        }
    }

    @Readiness
    @ApplicationScoped
    public static class ReadyProbe implements HealthCheck {
        @Override
        public HealthCheckResponse call() {
            return HealthCheckResponse.named("InventarioService-Ready")
                    .up()
                    .withData("catalogoStatus", "ONLINE")
                    .build();
        }
    }
}

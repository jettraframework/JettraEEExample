package com.jettra.example.model;

import java.io.Serializable;

/**
 * Estado de inventario correspondiente al Showcase de PrimeFaces.
 */
public enum InventoryStatus implements Serializable {
    INSTOCK("In Stock", "success"),
    LOWSTOCK("Low Stock", "warning"),
    OUTOFSTOCK("Out of Stock", "danger");

    private final String text;
    private final String severity;

    InventoryStatus(String text, String severity) {
        this.text = text;
        this.severity = severity;
    }

    public String getText() {
        return text;
    }

    public String getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return text;
    }
}

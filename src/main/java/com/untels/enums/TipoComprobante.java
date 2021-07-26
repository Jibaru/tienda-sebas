package com.untels.enums;

public enum TipoComprobante {
    BOLETA, FACTURA;

    public static TipoComprobante fromString(String tipoComprobante) {
        TipoComprobante enuma = null;
        switch (tipoComprobante.toUpperCase()) {
            case "BOLETA":
                enuma = BOLETA;
                break;
            case "FACTURA":
                enuma = FACTURA;
                break;
        }
        return enuma;
    }
}

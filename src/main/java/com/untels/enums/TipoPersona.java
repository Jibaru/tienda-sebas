package com.untels.enums;

public enum TipoPersona {
    CLIENTE, PROVEEDOR;

    public static TipoPersona fromString(String tipoPersona) {
        TipoPersona enuma = null;
        switch (tipoPersona.toUpperCase()) {
            case "CLIENTE":
                enuma = CLIENTE;
                break;
            case "PROVEEDOR":
                enuma = PROVEEDOR;
                break;
        }
        return enuma;
    }
}

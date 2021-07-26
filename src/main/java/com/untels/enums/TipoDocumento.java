package com.untels.enums;

public enum TipoDocumento {
    DNI, RUC;

    public static TipoDocumento fromString(String tipoDoc) {
        TipoDocumento enuma = null;
        switch (tipoDoc.toUpperCase()) {
            case "DNI":
                enuma = DNI;
                break;
            case "RUC":
                enuma = RUC;
                break;
        }
        return enuma;
    }
}

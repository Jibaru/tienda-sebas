package com.untels.enums;

public enum Rol {
    NORMAL, ADMIN;

    public static Rol fromString(String rol) {
        Rol enuma = null;
        switch (rol.toUpperCase()) {
            case "NORMAL":
                enuma = NORMAL;
                break;
            case "ADMIN":
                enuma = ADMIN;
                break;
        }
        return enuma;
    }
}

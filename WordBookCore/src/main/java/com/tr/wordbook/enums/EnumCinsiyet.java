package com.tr.wordbook.enums;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
public enum EnumCinsiyet {

    BAY("BAY"),
    BAYAN("BAYAN");

    private final String turu;

    EnumCinsiyet(String turu) {
        this.turu = turu;
    }

    @Override
    public String toString() {
        return this.turu;
    }
}

package com.tr.wordbook.enums;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
public enum EnumSecimEH {

    EVET("EVET"),
    HAYIR("HAYIR");

    private final String turu;

    EnumSecimEH(String turu) {
        this.turu = turu;
    }

    @Override
    public String toString() {
        return this.turu;
    }
}

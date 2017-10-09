package com.tr.wordbook.enums;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
public enum EnumZorlukDerece {

    BIR("1"),
    IKI("2"),
    UC("3");

    EnumZorlukDerece(String hizmetBolgesi) {
        this.hizmetBolgesi = hizmetBolgesi;
    }

    private final String hizmetBolgesi;

    @Override
    public String toString() {
        return this.hizmetBolgesi;
    }

}

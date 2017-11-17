package com.tr.wordbook.enums;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
public enum EnumMenu {

    KELIMELER("Kelimeler"),
    KELIME_EKLE("Kelime Ekle"),
    KATEGORI_EKLE("Kategori Ekle"),
    CALIS("Çalış"),
    PROFIL("Profil"),
    PROFILIM("Profilim"),
    ARKADASLAR("Arkadaş"),
    AYARLAR("Ayarlar"),
    RAPOR("Rapor"),
    CIKIS("Çıkış"),
    ;

    private final String turu;

    EnumMenu(String turu) {
        this.turu = turu;
    }

    @Override
    public String toString() {
        return this.turu;
    }
}

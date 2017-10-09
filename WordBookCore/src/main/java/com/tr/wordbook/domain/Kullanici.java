package com.tr.wordbook.domain;

import com.tr.wordbook.standart.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Entity
@Table(name = "KULLANICI")
public class Kullanici extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "generator", sequenceName = "KULLANICI_ID_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
    @Column
    private Long id;

    @Size(max = 50)
    @Column(length = 50)
    private String kullaniciAdi;

    @Size(max = 50)
    @Column(length = 50)
    private String sifre;
    /**
     * Şifre md5
     */
    @Size(max = 150)
    @Column(length = 150)
    private String sifreKriptolu;

    @Size(max = 50)
    @Column(length = 50)
    private String adi;

    @Size(max = 50)
    @Column(length = 50)
    private String soyadi;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getSifreKriptolu() {
        return sifreKriptolu;
    }

    public void setSifreKriptolu(String sifreKriptolu) {
        this.sifreKriptolu = sifreKriptolu;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }
}

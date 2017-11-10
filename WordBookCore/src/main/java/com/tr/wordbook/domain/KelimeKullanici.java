package com.tr.wordbook.domain;

import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.enums.EnumZorlukDerece;
import com.tr.wordbook.standart.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Entity
@Table(name = "KELIME_KULLANICI")
public class KelimeKullanici extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumZorlukDerece zorlukDerece;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumSecimEH ezberlendi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI")
    @org.hibernate.annotations.ForeignKey(name = "FK_KELIME_KULLANICI_KELIME")
    public Kullanici kullanici;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KELIME")
    @org.hibernate.annotations.ForeignKey(name = "FK_KELIME_KULLANICI_KULLANICI")
    public Kelime kelime;

    public void setId(Long id) {
        this.id = id;
    }

    public EnumZorlukDerece getZorlukDerece() {
        return zorlukDerece;
    }

    public void setZorlukDerece(EnumZorlukDerece zorlukDerece) {
        this.zorlukDerece = zorlukDerece;
    }

    public EnumSecimEH getEzberlendi() {
        return ezberlendi;
    }

    public void setEzberlendi(EnumSecimEH ezberlendi) {
        this.ezberlendi = ezberlendi;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public Kelime getKelime() {
        return kelime;
    }

    public void setKelime(Kelime kelime) {
        this.kelime = kelime;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public Long getId() {
        return id;
    }
}

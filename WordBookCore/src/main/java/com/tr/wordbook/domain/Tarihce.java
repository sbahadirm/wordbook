package com.tr.wordbook.domain;

import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.enums.EnumZorlukDerece;
import com.tr.wordbook.standart.BaseEntity;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
public class Tarihce  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "generator", sequenceName = "KELIME_ID_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
    @Column
    private Long id;

    @Column
    private Date tarih;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumZorlukDerece hizmetBolgesi;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumSecimEH ezberlendi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KELIME")
    @ForeignKey(name = "FK_TARIHCE_KELIME")
    private Kelime kelime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public EnumZorlukDerece getHizmetBolgesi() {
        return hizmetBolgesi;
    }

    public void setHizmetBolgesi(EnumZorlukDerece hizmetBolgesi) {
        this.hizmetBolgesi = hizmetBolgesi;
    }

    public EnumSecimEH getEzberlendi() {
        return ezberlendi;
    }

    public void setEzberlendi(EnumSecimEH ezberlendi) {
        this.ezberlendi = ezberlendi;
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
}

package com.tr.wordbook.domain;

import com.tr.wordbook.standart.BaseEntity;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.Date;


/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Entity
@Table(name = "KULLANICI_ARKADAS")
public class KullaniciArkadas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "generator", sequenceName = "KULLANICI_ARKADAS_ID_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
    @Column
    private Long id;

    @Index(name = "IX_KULLANICI_ARKADAS_KUL")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI")
    @ForeignKey(name = "FK_KULLANICI_ARKADAS_KUL")
    public Kullanici kullanici;

    @Index(name = "IX_KULLANICI_ARKADAS_ARK")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI_ARKADAS")
    @ForeignKey(name = "FK_KULLANICI_ARKADAS_ARK")
    public Kullanici kullaniciArkadas;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date arkadaslikTarihi;

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
}

package com.tr.wordbook.domain;

import com.tr.wordbook.standart.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Entity
@Table(name = "KELIME")
public class Kategori extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "generator", sequenceName = "KATEGORI_ID_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
    @Column
    private Long id;

    @Size(max = 50)
    @Column(length = 50)
    private String adi;

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

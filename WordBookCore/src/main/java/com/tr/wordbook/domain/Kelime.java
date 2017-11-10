package com.tr.wordbook.domain;

import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.enums.EnumZorlukDerece;
import com.tr.wordbook.standart.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@Entity
@Table(name = "KELIME")
public class Kelime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    @Column(length = 50)
    private String turkce;

    @Size(max = 50)
    @Column(length = 50)
    private String ingilizce;

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngilizce() {
        return ingilizce;
    }

    public void setIngilizce(String ingilizce) {
        this.ingilizce = ingilizce;
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

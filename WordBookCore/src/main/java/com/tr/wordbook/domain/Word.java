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
public class Word extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    @Column(length = 50)
    private String turkce;

    @Size(max = 50)
    @Column(length = 50)
    private String ingilizce;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumZorlukDerece zorlukDerece;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EnumSecimEH ezberlendi;

    public Word(){

    }

    public Word(String text){
        this.turkce = text;
    }

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

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public Long getId() {
        return id;
    }
}

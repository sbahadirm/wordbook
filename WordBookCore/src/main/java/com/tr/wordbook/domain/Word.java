package com.tr.wordbook.domain;

import javax.persistence.*;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@Entity
@Table(name = "KELIME")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    public Word(){

    }

    public Word(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

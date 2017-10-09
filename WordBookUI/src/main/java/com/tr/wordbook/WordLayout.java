package com.tr.wordbook;

import com.tr.wordbook.domain.Word;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
public class WordLayout extends HorizontalLayout {

    public WordLayout(Word word){
        setSpacing(true);
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        TextField textField = new TextField();
        textField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        textField.setWidth("80%");
        textField.setValue(word.getTurkce());
        addComponent(textField);

    }

}

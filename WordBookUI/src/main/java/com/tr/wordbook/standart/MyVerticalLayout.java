package com.tr.wordbook.standart;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
public class MyVerticalLayout extends VerticalLayout {

    public MyVerticalLayout() {
        super();
        this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        this.setSpacing(true);
        this.setMargin(true);
    }
}

package com.tr.wordbook.page;

import com.tr.wordbook.enums.EnumSecimEH;
import com.vaadin.ui.ComboBox;

/**
 * @author Bahadır Memiş
 * @since 0.01
 */
public class EnumSecimEHComboField extends ComboBox {

    public EnumSecimEHComboField() {
        super();
        fillCombobox();

    }

    public void fillCombobox() {

        this.clear();

        EnumSecimEH[] values = EnumSecimEH.values();

        this.setItems(values);
    }

}

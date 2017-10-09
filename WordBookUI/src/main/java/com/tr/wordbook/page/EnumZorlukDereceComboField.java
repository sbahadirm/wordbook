package com.tr.wordbook.page;

import com.tr.wordbook.enums.EnumZorlukDerece;
import com.vaadin.ui.ComboBox;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
public class EnumZorlukDereceComboField extends ComboBox {

    public EnumZorlukDereceComboField() {
        super();
        fillCombobox();

    }

    public void fillCombobox() {

        this.clear();

        EnumZorlukDerece[] values = EnumZorlukDerece.values();

        this.setItems(values);
    }
}

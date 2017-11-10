package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.KullaniciArkadas;
import com.tr.wordbook.service.entityservice.KullaniciArkadasEntityService;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class ArkadaslarPage extends VerticalLayout {

    private TextField adiField;
    private TextField kullaniciAdiField;

    private Button araButton;

    private Grid<KullaniciArkadas> grid = new Grid<>(KullaniciArkadas.class);

    private List<KullaniciArkadas> allKullaniciArkadasList;
    private KullaniciArkadasEntityService kullaniciArkadasEntityService;

    public ArkadaslarPage(){
        super();
        kullaniciArkadasEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KullaniciArkadasEntityService.class);
        initFieldsPanel();
        Kullanici kullanici = WordBookUI.getKullanici();
        allKullaniciArkadasList = kullaniciArkadasEntityService.findAllKullaniciArkadasByKullanici(kullanici);
//        fillTable();

    }

    private void initFieldsPanel() {

        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout layout1 = new HorizontalLayout();
        HorizontalLayout layout2 = new HorizontalLayout();

        adiField = new TextField();
        adiField.setCaption("Adı");
        layout1.addComponent(adiField);

        kullaniciAdiField = new TextField();
        kullaniciAdiField.setCaption("Kullanıcı Adı");
        layout1.addComponent(kullaniciAdiField);

        araButton = new Button();
        araButton.setCaption("Ara");
        araButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                fillTable();
            }
        });
        layout2.addComponent(araButton);

        layout.addComponent(layout1);
        layout.addComponent(layout2);

        addComponent(layout);
    }

    private void fillTable(){

        addComponent(grid);

        if (!allKullaniciArkadasList.isEmpty()){

            grid.setItems(allKullaniciArkadasList);

        } else {
            Notification.show("Tüm kelimeler ezberlenmiştir!", Notification.Type.ERROR_MESSAGE);
        }
    }

}

package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.KullaniciArkadas;
import com.tr.wordbook.service.entityservice.KullaniciArkadasEntityService;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class ArkadasEklePage extends VerticalLayout {

    private TextField adiField;
    private TextField kullaniciAdiField;

    private Button araButton;

    private Grid<Kullanici> grid;

    private List<KullaniciArkadas> allKullaniciArkadasList;
    private KullaniciArkadasEntityService kullaniciArkadasEntityService;
    private KullaniciEntityService kullaniciEntityService;

    public ArkadasEklePage(){
        super();
        kullaniciArkadasEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KullaniciArkadasEntityService.class);
        kullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KullaniciEntityService.class);
        initFieldsPanel();
        Kullanici kullanici = WordBookUI.getKullanici();
        allKullaniciArkadasList = kullaniciArkadasEntityService.findAllKullaniciArkadasByKullanici(kullanici);
//        fillTable();

    }

    private void initFieldsPanel() {

        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        Panel arkadaslarPanel = new Panel();
        arkadaslarPanel.setWidth("100%");
        arkadaslarPanel.setCaption("Arkadaşlar");
        VerticalLayout arkadaslarPanelLayaot = new VerticalLayout();
        arkadaslarPanelLayaot.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        arkadaslarPanel.setContent(arkadaslarPanelLayaot);

        HorizontalLayout filtreLayout = new HorizontalLayout();
        filtreLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        HorizontalLayout filtreButtonsLayout = new HorizontalLayout();
        VerticalLayout gridLayout = new VerticalLayout();
        gridLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        adiField = new TextField();
        adiField.setCaption("Adı");
        filtreLayout.addComponent(adiField);

        kullaniciAdiField = new TextField();
        kullaniciAdiField.setCaption("Kullanıcı Adı");
        filtreLayout.addComponent(kullaniciAdiField);

        grid = new Grid<>(Kullanici.class);
        grid.setSizeFull();
        grid.getColumn("sifre").setHidden(true);
        grid.getColumn("sifreKriptolu").setHidden(true);
        gridLayout.addComponent(grid);

        araButton = new Button();
        araButton.setCaption("Ara");
        araButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                fillTable();
            }
        });
        filtreButtonsLayout.addComponent(araButton);

        arkadaslarPanelLayaot.addComponent(filtreLayout);
        arkadaslarPanelLayaot.addComponent(filtreButtonsLayout);
        arkadaslarPanelLayaot.addComponent(gridLayout);

        layout.addComponent(arkadaslarPanel);

        addComponent(layout);

    }

    private void fillTable(){

        String adiFieldValue = adiField.getValue();
        String kullaniciAdiFieldValue = kullaniciAdiField.getValue();

        List<Kullanici> kullaniciList = kullaniciEntityService.findKullaniciByKullaniciAdiAndAdi(kullaniciAdiFieldValue, adiFieldValue);

        if (!kullaniciList.isEmpty()){
            grid.setItems(kullaniciList);
        }
    }

}

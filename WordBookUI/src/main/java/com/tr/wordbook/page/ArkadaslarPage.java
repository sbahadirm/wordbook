package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.KullaniciArkadas;
import com.tr.wordbook.service.entityservice.KullaniciArkadasEntityService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
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

    private Grid<KullaniciArkadas> grid;

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
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        HorizontalLayout navigationLayout = new HorizontalLayout();
        navigationLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        Button arkadasEkleButton = new Button();
        arkadasEkleButton.setCaption("Arkadaş Ekle");
        arkadasEkleButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        navigationLayout.addComponent(arkadasEkleButton);

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

        grid = new Grid<>(KullaniciArkadas.class);
        grid.setSizeFull();
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

        layout.addComponent(navigationLayout);
        arkadaslarPanelLayaot.addComponent(filtreLayout);
        arkadaslarPanelLayaot.addComponent(filtreButtonsLayout);
        arkadaslarPanelLayaot.addComponent(gridLayout);

        layout.addComponent(arkadaslarPanel);

        addComponent(layout);

    }

    private void fillTable(){

        if (!allKullaniciArkadasList.isEmpty()){

            grid.setItems(allKullaniciArkadasList);

        }
    }

}

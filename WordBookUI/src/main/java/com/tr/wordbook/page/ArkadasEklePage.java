package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.KullaniciArkadas;
import com.tr.wordbook.service.entityservice.KullaniciArkadasEntityService;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.*;

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
        Kullanici kullanici = WordBookUI.get().getKullanici();
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
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.addSelectionListener(event -> {
            Set<Kullanici> selected = event.getAllSelectedItems();
            Notification.show(selected.size() + " items selected");
        });

        Grid.Column ekleColumn = grid.addColumn(kullanici -> "Ekle",
                new ButtonRenderer((ClickableRenderer.RendererClickListener) clickEvent -> {

                    Kullanici arkadas = (Kullanici) clickEvent.getItem();
                    arkadasEkle(arkadas);

                }));
        ekleColumn.setCaption("Ekle");

        gridLayout.addComponent(grid);

        araButton = new Button();
        araButton.setCaption("Ara");
        araButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
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

    private void arkadasEkle(Kullanici arkadas) {
        List<Kullanici> kullaniciList = getKullaniciListByFilters();

        Kullanici currentKullanici = WordBookUI.get().getKullanici();

        KullaniciArkadas kullaniciArkadas = new KullaniciArkadas();
        kullaniciArkadas.setKullanici(currentKullanici);
        kullaniciArkadas.setKullaniciArkadas(arkadas);
        kullaniciArkadas.setArkadaslikTarihi(new Date());
        kullaniciArkadas = kullaniciArkadasEntityService.save(kullaniciArkadas);
        kullaniciList.remove(arkadas);
        grid.setItems(kullaniciList);
    }

    private void fillTable(){

        List<Kullanici> kullaniciList = getKullaniciListByFilters();

        if (!kullaniciList.isEmpty()){
            grid.setItems(kullaniciList);
        }
    }

    private List<Kullanici> getKullaniciListByFilters() {
        String adiFieldValue = adiField.getValue();
        String kullaniciAdiFieldValue = kullaniciAdiField.getValue();

        List<Kullanici> kullaniciList = kullaniciEntityService.findAllKullaniciByKullaniciAdiAndAdi(kullaniciAdiFieldValue, adiFieldValue);
        Kullanici currentKullanici = WordBookUI.get().getKullanici();
        kullaniciList.remove(currentKullanici);

        List<KullaniciArkadas> kullaniciArkadasList = kullaniciArkadasEntityService.findAllKullaniciArkadasByKullanici(currentKullanici);
        Set<Kullanici> arkadasSet = new HashSet<>();
        for (KullaniciArkadas kullaniciArkadas : kullaniciArkadasList) {
            arkadasSet.add(kullaniciArkadas.getKullaniciArkadas());
        }
        kullaniciList.removeAll(arkadasSet);

        return kullaniciList;
    }

}

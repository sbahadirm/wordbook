package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.KelimeKullanici;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.service.entityservice.KelimeKullaniciEntityService;
import com.vaadin.data.HasValue;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;
import java.util.ListIterator;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class KelimePage extends VerticalLayout {

    private Button kelimeFieldButton;
    private Button ileriButton;
    private Button geriButton;
    private Button ilerleButton;
    private Kullanici kullanici;
    private CheckBox ezberlenenlerGelsin;

    private KelimeKullanici selectedKelimeKullanici;
    private List<KelimeKullanici> allKelimeKullaniciList;
    private ListIterator<KelimeKullanici> listIterator;
    private KelimeKullaniciEntityService kelimeKullaniciEntityService;

    public KelimePage(){
        super();

        this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        kullanici = WordBookUI.get().getKullanici();

        kelimeKullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KelimeKullaniciEntityService.class);
        allKelimeKullaniciList = kelimeKullaniciEntityService.findAllKelimeKullaniciByNotEzberlendiAndKullanici(EnumSecimEH.EVET, kullanici);
        if (!allKelimeKullaniciList.isEmpty()){
            initFieldsPanel();
            listIterator = allKelimeKullaniciList.listIterator();
            getNextKelime();
        }
    }

    private void initFieldsPanel(){
        addComponent(initTercihLayout());
        addComponent(initFieldsLayout());
    }

    private Panel initTercihLayout() {

        Panel panel = new Panel();
        panel.setWidth("800px");
        panel.setHeight("30px");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panel.setContent(horizontalLayout);
        horizontalLayout.setSizeFull();

        ezberlenenlerGelsin = new CheckBox();
        ezberlenenlerGelsin.setCaption("Ezberlenenler Gelsin");
        ezberlenenlerGelsin.setValue(Boolean.FALSE);
        ezberlenenlerGelsin.addValueChangeListener(new HasValue.ValueChangeListener<Boolean>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<Boolean> event) {
                Boolean eventValue = event.getValue();
                fillScreenByEzberlenenlerGelsin(eventValue);
            }
        });
        horizontalLayout.addComponent(ezberlenenlerGelsin);

        return panel;
    }

    private void fillScreenByEzberlenenlerGelsin(Boolean eventValue) {

        if (Boolean.TRUE.equals(eventValue)){
            allKelimeKullaniciList = kelimeKullaniciEntityService.findAllKelimeKullaniciByKullanici(kullanici);
            listIterator = allKelimeKullaniciList.listIterator();
        } else {
            allKelimeKullaniciList = kelimeKullaniciEntityService.findAllKelimeKullaniciByNotEzberlendiAndKullanici(EnumSecimEH.EVET, kullanici);
            listIterator = allKelimeKullaniciList.listIterator();
        }

        getNextKelime();
    }

    private Panel initFieldsLayout() {

        Panel panel = new Panel();
        panel.setWidth("800px");
        panel.setHeight("300px");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panel.setContent(horizontalLayout);
        horizontalLayout.setSizeFull();

        VerticalLayout solMenuPanelLayout = new VerticalLayout();
        solMenuPanelLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        VerticalLayout ortaMenuPanelLayout = new VerticalLayout();
        ortaMenuPanelLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        VerticalLayout sagMenuPanelLayout = new VerticalLayout();
        sagMenuPanelLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        geriButton = new Button();
        geriButton.setIcon(FontAwesome.BACKWARD);
        geriButton.setCaption("");
        geriButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getPreviousKelime();
            }
        });
        solMenuPanelLayout.addComponent(geriButton);

        kelimeFieldButton = new Button();
        kelimeFieldButton.setWidth("100%");
        kelimeFieldButton.setHeight("100%");
        kelimeFieldButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        kelimeFieldButton.addStyleName(ValoTheme.BUTTON_HUGE);
        kelimeFieldButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                dilDegistir();
            }
        });
        ortaMenuPanelLayout.addComponent(kelimeFieldButton);

        ilerleButton = new Button();
        ilerleButton.setIcon(FontAwesome.THUMBS_UP);
        ilerleButton.setCaption("EZBERLENDİ");
        ilerleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ezberlendi();
            }
        });
        ortaMenuPanelLayout.addComponent(ilerleButton);

        ileriButton = new Button();
        ileriButton.setIcon(FontAwesome.FORWARD);
        ileriButton.setCaption("");
        ileriButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getNextKelime();
            }
        });
        sagMenuPanelLayout.addComponent(ileriButton);

        horizontalLayout.addComponent(solMenuPanelLayout);
        horizontalLayout.addComponent(ortaMenuPanelLayout);
        horizontalLayout.addComponent(sagMenuPanelLayout);

        return panel;
    }

    private VerticalLayout initVerticalPanel() {

        Panel Panel = new Panel();
        Panel.setWidth("150px");
        Panel.setHeight("300px");
        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Panel.setContent(layout);

        return layout;
    }

    private HorizontalLayout initHorizontalPanel() {

        Panel Panel = new Panel();
        Panel.setWidth("150px");
        Panel.setHeight("300px");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Panel.setContent(layout);

        return layout;
    }

    private void ezberlendi() {

        selectedKelimeKullanici.setEzberlendi(EnumSecimEH.EVET);
        if (kelimeKullaniciEntityService != null){
            selectedKelimeKullanici = kelimeKullaniciEntityService.save(selectedKelimeKullanici);
        }

        getNextKelime();
    }

    private void dilDegistir(){

        if (selectedKelimeKullanici.getKelime().getTurkce().toUpperCase().equals(kelimeFieldButton.getCaption())){
            ingilizcesiniGetir();
        } else {
            turkcesiniGetir();
        }
    }

    private void turkcesiniGetir() {
        kelimeFieldButton.setCaption(selectedKelimeKullanici.getKelime().getTurkce().toUpperCase());
    }

    private void ingilizcesiniGetir() {
        kelimeFieldButton.setCaption(selectedKelimeKullanici.getKelime().getIngilizce().toUpperCase());
    }

    private void getNextKelime() {

        validateList();

        if (listIterator.hasNext()) {
            selectedKelimeKullanici = listIterator.next();
            kelimeFieldButton.setCaption(selectedKelimeKullanici.getKelime().getIngilizce().toUpperCase());
        } else {
            Notification.show("Listenin sonundasınız. İleri gidilemez!", Notification.Type.ERROR_MESSAGE);
        }

    }

    private void getPreviousKelime() {

        validateList();

        if (listIterator.hasPrevious()) {
            selectedKelimeKullanici = listIterator.previous();
            kelimeFieldButton.setCaption(selectedKelimeKullanici.getKelime().getIngilizce().toUpperCase());
        } else {

            Notification.show("Listenin başındasınız. Geri gidilemez!", Notification.Type.ERROR_MESSAGE);
        }
    }

    private void validateList() {

        if (allKelimeKullaniciList.isEmpty()){

            Boolean isEzberlenenlerGelsin = ezberlenenlerGelsin.getValue();

            String notificationValue = "Tüm kelimeler ezberlenmiştir!";
            if (isEzberlenenlerGelsin){
                notificationValue = "Kelime bulunamadı! Kelime ekleyiniz.";
            }
            Notification.show(notificationValue, Notification.Type.ERROR_MESSAGE);
        }
    }
}

package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class ProfilPage extends VerticalLayout {

    private KullaniciEntityService kullaniciEntityService;

    public ProfilPage(){
        super();
        kullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KullaniciEntityService.class);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        initFieldsPanel();
    }


    private void initFieldsPanel(){

        HorizontalLayout layout1 = new HorizontalLayout();

        Panel solMenuPanel = new Panel();
        solMenuPanel.setWidth("150px");
        solMenuPanel.setHeight("300px");
        VerticalLayout solMenuPanelLayout = new VerticalLayout();
        solMenuPanelLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        solMenuPanel.setContent(solMenuPanelLayout);

        Panel sagMenuPanel = new Panel();
        sagMenuPanel.setWidth("900px");
        sagMenuPanel.setHeight("300px");
        VerticalLayout sagMenuPanelLayout = new VerticalLayout();
        sagMenuPanelLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        sagMenuPanel.setContent(sagMenuPanelLayout);

        Button buttonKaydet = new Button();
        buttonKaydet.setCaption("Profil");
        buttonKaydet.setWidth("120px");
        buttonKaydet.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                initProfilLayout();
            }
        });
        solMenuPanelLayout.addComponent(buttonKaydet);

        Button buttonTakipciler = new Button();
        buttonTakipciler.setCaption("Takipçiler");
        buttonTakipciler.setWidth("120px");
        buttonKaydet.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                initTakipcilerLayout();
            }
        });
        solMenuPanelLayout.addComponent(buttonTakipciler);

        Button buttonTakipEdilen = new Button();
        buttonTakipEdilen.setCaption("Takip Edilen");
        buttonTakipEdilen.setWidth("120px");
        buttonKaydet.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                initTakipEdilenLayout();
            }
        });
        solMenuPanelLayout.addComponent(buttonTakipEdilen);

        Button buttonTakipIstek = new Button();
        buttonTakipIstek.setCaption("İstekler");
        buttonTakipIstek.setWidth("120px");
        buttonKaydet.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                initTakipIstekLayout();
            }
        });
        solMenuPanelLayout.addComponent(buttonTakipIstek);

        Button buttonKategori = new Button();
        buttonKategori.setCaption("Kategoriler");
        buttonKategori.setWidth("120px");
        buttonKaydet.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                initKategoriLayout();
            }
        });
        solMenuPanelLayout.addComponent(buttonKategori);

        layout1.addComponent(solMenuPanel);
        layout1.addComponent(sagMenuPanel);

        addComponent(layout1);

    }

    private void initProfilLayout() {

    }

    private void initTakipcilerLayout() {

    }

    private void initTakipEdilenLayout() {

    }

    private void initTakipIstekLayout() {

    }

    private void initKategoriLayout() {

    }

}

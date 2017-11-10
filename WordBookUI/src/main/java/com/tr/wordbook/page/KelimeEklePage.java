package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.Kelime;
import com.tr.wordbook.domain.KelimeKullanici;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.enums.EnumZorlukDerece;
import com.tr.wordbook.field.EnumSecimEHComboField;
import com.tr.wordbook.field.EnumZorlukDereceComboField;
import com.tr.wordbook.service.entityservice.KelimeEntityService;
import com.tr.wordbook.service.entityservice.KelimeKullaniciEntityService;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class KelimeEklePage extends HorizontalLayout {

    private KelimeEntityService kelimeEntityService;
    private KelimeKullaniciEntityService kelimeKullaniciEntityService;

    private TextField turkceField;
    private TextField ingilizceField;
    private EnumSecimEHComboField ezberlendiField;
    private EnumZorlukDereceComboField zorlukDereceField;
    private Button kaydetButton;

    private ListSelect<String> listSelectField;
    private List<KelimeKullanici> kelimeKullaniciList;

    public KelimeEklePage(){
        super();

        kelimeKullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KelimeKullaniciEntityService.class);
        kelimeEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KelimeEntityService.class);
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        initListePanel();
        initFieldsPanel();
        fillList();
    }

    public KelimeEklePage(List<KelimeKullanici> allKelimeKullanici){
        super();
        kelimeKullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KelimeKullaniciEntityService.class);
        kelimeEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KelimeEntityService.class);

        this.kelimeKullaniciList = allKelimeKullanici;
        initListePanel();
        initFieldsPanel();
        fillList();
    }


    private void initFieldsPanel(){

        Panel fieldsPanel = new Panel();
        fieldsPanel.setWidth("100%");
        VerticalLayout fieldsPanelLayaot = new VerticalLayout();
        fieldsPanelLayaot.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        fieldsPanel.setContent(fieldsPanelLayaot);

        ingilizceField = new TextField();
        ingilizceField.setCaption("İngilizce");
        ingilizceField.setWidth("100%");
        fieldsPanelLayaot.addComponent(ingilizceField);

        turkceField = new TextField();
        turkceField.setCaption("Türkçe");
        turkceField.setWidth("100%");
        fieldsPanelLayaot.addComponent(turkceField);

        ezberlendiField = new EnumSecimEHComboField();
        ezberlendiField.setCaption("Ezberlendi");
        ezberlendiField.setValue(EnumSecimEH.HAYIR);
        ezberlendiField.setWidth("100%");
        fieldsPanelLayaot.addComponent(ezberlendiField);

        zorlukDereceField = new EnumZorlukDereceComboField();
        zorlukDereceField.setCaption("Şifre");
        zorlukDereceField.setValue(EnumZorlukDerece.BIR);
        zorlukDereceField.setWidth("100%");
        fieldsPanelLayaot.addComponent(zorlukDereceField);

        kaydetButton = new Button();
        kaydetButton.setCaption("Kaydet");
        kaydetButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        kaydetButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                kaydet();
            }
        });
        fieldsPanelLayaot.addComponent(kaydetButton);

        addComponent(fieldsPanelLayaot);

    }

    private void initListePanel(){

        Panel listePanel = new Panel();
        listePanel.setWidth("100%");
        listePanel.setCaption("Kelimeler");
        VerticalLayout listePanelLayaot = new VerticalLayout();
        listePanelLayaot.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        listePanel.setContent(listePanelLayaot);

        listSelectField = new ListSelect<>();
        listSelectField.setCaption("Kelimeler");
        listSelectField.setWidth("100%");
        listSelectField.addContextClickListener(new ContextClickEvent.ContextClickListener() {
            @Override
            public void contextClick(ContextClickEvent contextClickEvent) {
                Set<String> listSelectFieldValue = listSelectField.getValue();
            }
        });
        listePanelLayaot.addComponent(listSelectField);

        Button sil = new Button();
        sil.setCaption("Sil");
        sil.addStyleName(ValoTheme.BUTTON_PRIMARY);
        sil.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                sil();
            }
        });
        listePanelLayaot.addComponent(sil);

        addComponent(listePanelLayaot);
    }

    private void sil(){
    }

    private void kaydet(){

        if (kelimeKullaniciEntityService != null){

            Kullanici kullanici = WordBookUI.getKullanici();

            Kelime kelime = new Kelime();
            kelime.setTurkce(turkceField.getValue());
            kelime.setIngilizce(ingilizceField.getValue());
            kelime = kelimeEntityService.save(kelime);

            KelimeKullanici kelimeKullanici = new KelimeKullanici();
            kelimeKullanici.setEzberlendi((EnumSecimEH) ezberlendiField.getValue());
            kelimeKullanici.setZorlukDerece((EnumZorlukDerece) zorlukDereceField.getValue());
            kelimeKullanici.setKelime(kelime);
            kelimeKullanici.setKullanici(kullanici);

            kelimeKullanici = kelimeKullaniciEntityService.save(kelimeKullanici);

            Notification.show("Kaydedildi!", Notification.Type.HUMANIZED_MESSAGE);
            turkceField.setValue("");
            ingilizceField.setValue("");
        }

        fillList();
    }

    private void fillList() {

        if (kelimeKullaniciEntityService != null){
            Kullanici kullanici = WordBookUI.getKullanici();
            kelimeKullaniciList = kelimeKullaniciEntityService.findAllKelimeKullaniciByKullanici(kullanici);
        }

        List<String> stringList = new ArrayList<>();
        for (KelimeKullanici kelimeKullanici : kelimeKullaniciList) {
            stringList.add(kelimeKullanici.getKelime().getIngilizce() + " - " + kelimeKullanici.getKelime().getTurkce());
        }

        listSelectField.setItems(stringList);
    }
}

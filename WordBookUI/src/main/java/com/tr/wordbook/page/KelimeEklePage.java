package com.tr.wordbook.page;

import com.tr.wordbook.domain.Word;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.enums.EnumZorlukDerece;
import com.tr.wordbook.service.entityservice.WordBookEntityService;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class KelimeEklePage extends HorizontalLayout {

    @Autowired
    WordBookEntityService wordBookEntityService;

    private TextField turkceField;
    private TextField ingilizceField;
    private EnumSecimEHComboField ezberlendiField;
    private EnumZorlukDereceComboField zorlukDereceField;
    private Button kaydetButton;

    private ListSelect<String> listSelectField;
    private List<Word> kelimeList;

    public KelimeEklePage(){
        super();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        initListePanel();
        initFieldsPanel();
        fillList();
    }

    public KelimeEklePage(List<Word> allWord){
        super();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        this.kelimeList = allWord;
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

        turkceField = new TextField();
        turkceField.setCaption("Türkçe");
        turkceField.setWidth("100%");
        fieldsPanelLayaot.addComponent(turkceField);

        ingilizceField = new TextField();
        ingilizceField.setCaption("İngilizce");
        ingilizceField.setWidth("100%");
        fieldsPanelLayaot.addComponent(ingilizceField);

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

        if (wordBookEntityService != null){

            Word kelime = new Word();
            kelime.setTurkce(turkceField.getValue());
            kelime.setIngilizce(ingilizceField.getValue());
            kelime.setEzberlendi((EnumSecimEH) ezberlendiField.getValue());
            kelime.setZorlukDerece((EnumZorlukDerece) zorlukDereceField.getValue());

            kelime = wordBookEntityService.save(kelime);

            Notification.show("Şifreler uyumsuz!", Notification.Type.ERROR_MESSAGE);
        }

        fillList();
    }

    private void fillList() {
        if (wordBookEntityService != null){
            kelimeList = wordBookEntityService.findAllWord();
        }

        List<String> stringList = new ArrayList<>();
        for (Word kelime : kelimeList) {
            stringList.add(kelime.getIngilizce() + " - " + kelime.getTurkce());
        }

        listSelectField.setItems(stringList);

    }

}

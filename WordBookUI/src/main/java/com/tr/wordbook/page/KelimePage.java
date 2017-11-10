package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.Kelime;
import com.tr.wordbook.domain.KelimeKullanici;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.service.entityservice.KelimeEntityService;
import com.tr.wordbook.service.entityservice.KelimeKullaniciEntityService;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class KelimePage extends VerticalLayout {

    private Label kelimeField;
//    private Button ileriButton;
//    private Button geriButton;
    private Button ilerleButton;

    private KelimeKullanici selectedKelimeKullanici;
    private List<KelimeKullanici> allKelimeKullaniciList;
    private KelimeKullaniciEntityService kelimeKullaniciEntityService;

    public KelimePage(){
        super();

        Kullanici kullanici = WordBookUI.getKullanici();

        kelimeKullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KelimeKullaniciEntityService.class);
        initFieldsPanel();
        allKelimeKullaniciList = kelimeKullaniciEntityService.findAllKelimeKullaniciByNotEzberlendiAndKullanici(EnumSecimEH.EVET, kullanici);
        getNextKelime();
    }

    public KelimePage(List<KelimeKullanici> allKelimeKullaniciList){
        super();
        kelimeKullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KelimeKullaniciEntityService.class);
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        initFieldsPanel();
        if (allKelimeKullaniciList != null){
            this.allKelimeKullaniciList = allKelimeKullaniciList;
        } else {
            this.allKelimeKullaniciList = new ArrayList<>();
        }

        getNextKelime();
    }


    private void initFieldsPanel(){

//        geriButton = new Button();
//        geriButton.setIcon(FontAwesome.BACKWARD);
//        geriButton.setCaption("");
//        layout1.addComponent(geriButton);

        kelimeField = new Label();
        kelimeField.setWidth("100%");
        kelimeField.setHeight("100%");
        kelimeField.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
        kelimeField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        kelimeField.addStyleName(ValoTheme.LABEL_H1);
        addComponent(kelimeField);

        ilerleButton = new Button();
        ilerleButton.setIcon(FontAwesome.HAND_O_RIGHT);
        ilerleButton.setCaption("");
        ilerleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ilerle();
            }
        });
        addComponent(ilerleButton);

    }

    private void ilerle(){

        if (FontAwesome.THUMBS_UP.equals(ilerleButton.getIcon())){
            ezberlendi();
        } else {
            turkcesiniGetir();
        }
    }

    private void ezberlendi() {
        ilerleButton.setIcon(FontAwesome.HAND_O_RIGHT);

        selectedKelimeKullanici.setEzberlendi(EnumSecimEH.EVET);
        if (kelimeKullaniciEntityService != null){
            selectedKelimeKullanici = kelimeKullaniciEntityService.save(selectedKelimeKullanici);
        }

        allKelimeKullaniciList.remove(selectedKelimeKullanici);

        getNextKelime();
    }

    private void turkcesiniGetir() {
        ilerleButton.setIcon(FontAwesome.THUMBS_UP);
        kelimeField.setValue(selectedKelimeKullanici.getKelime().getTurkce().toUpperCase());
    }

//    private void ileri(){
//        Long id = selectedKelime.getId();
//        wordBookEntityService.findById(id + 1L)
//    }

//    private void geri(){
//
//    }

//    private void kaydol(){
//
//        if (getSifreField().getValue().equals(getSifreTekrarField().getValue())){
//
//            Kullanici user = kullaniciEntityService.findKullaniciByKullaniciAdi(getKullaniciAdiField().getValue());
//
//            if (user == null){
//                Kullanici kullanici = new Kullanici();
//                kullanici.setAdi(getKelimeField().getValue());
//                kullanici.setSoyadi(getSoyadiField().getValue());
//                kullanici.setKullaniciAdi(getKullaniciAdiField().getValue());
//                kullanici.setSifre(Md5Service.getHash(getSifreField().getValue()));
//                kullanici.setSifreKriptolu(Md5Service.getHash(getSifreField().getValue()));
//                kullanici = kullaniciEntityService.save(kullanici);
//            } else {
//                Notification.show("Kullanıcı zaten var!", Notification.Type.ERROR_MESSAGE);
//            }
//        } else {
//            Notification.show("Şifreler uyumsuz!", Notification.Type.ERROR_MESSAGE);
//        }
//    }

    private void getNextKelime(){

        if (!allKelimeKullaniciList.isEmpty()){
            selectedKelimeKullanici = allKelimeKullaniciList.get(0);
            kelimeField.setValue(selectedKelimeKullanici.getKelime().getIngilizce().toUpperCase());
        } else {
            Notification.show("Tüm kelimeler ezberlenmiştir!", Notification.Type.ERROR_MESSAGE);
        }
    }

}

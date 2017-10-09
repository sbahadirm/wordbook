package com.tr.wordbook.page;

import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.service.Md5Service;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.tr.wordbook.standart.MyVerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class KaydolPage extends VerticalLayout {

    @Autowired
    KullaniciEntityService kullaniciEntityService;

    private TextField adiField;
    private TextField soyadiField;
    private TextField kullaniciAdiField;
    private TextField sifreField;
    private TextField sifreTekrarField;
    private Button kaydolButton;

    public KaydolPage(){
        super();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        initFieldsPanel();
    }


    private void initFieldsPanel(){

        MyVerticalLayout layout1 = new MyVerticalLayout();

        adiField = new TextField();
        adiField.setCaption("Adı");
        layout1.addComponent(adiField);

        soyadiField = new TextField();
        soyadiField.setCaption("Soyadı");
        layout1.addComponent(soyadiField);

        kullaniciAdiField = new TextField();
        kullaniciAdiField.setCaption("Kullanıcı Adı");
        kullaniciAdiField.setDescription("En az 5 karakter");
        layout1.addComponent(kullaniciAdiField);

        sifreField = new TextField();
        sifreField.setCaption("Şifre");
        sifreField.setDescription("En az 8 karakter olmalıdır.");
        layout1.addComponent(sifreField);

        sifreTekrarField = new TextField();
        sifreTekrarField.setCaption("Şifre (Tekrar)");
        sifreTekrarField.setDescription("En az 8 karakter olmalıdır.");
        layout1.addComponent(sifreTekrarField);

        Button buttonKaydet = new Button();
        buttonKaydet.setCaption("Kaydol");
        buttonKaydet.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                kaydol();
            }
        });
        layout1.addComponent(buttonKaydet);

        addComponent(layout1);

    }

    private void kaydol(){

        if (getSifreField().getValue().equals(getSifreTekrarField().getValue())){

            Kullanici user = kullaniciEntityService.findKullaniciByKullaniciAdi(getKullaniciAdiField().getValue());

            if (user == null){
                Kullanici kullanici = new Kullanici();
                kullanici.setAdi(getAdiField().getValue());
                kullanici.setSoyadi(getSoyadiField().getValue());
                kullanici.setKullaniciAdi(getKullaniciAdiField().getValue());
                kullanici.setSifre(Md5Service.getHash(getSifreField().getValue()));
                kullanici.setSifreKriptolu(Md5Service.getHash(getSifreField().getValue()));
                kullanici = kullaniciEntityService.save(kullanici);
            } else {
                Notification.show("Kullanıcı zaten var!", Notification.Type.ERROR_MESSAGE);
            }
        } else {
            Notification.show("Şifreler uyumsuz!", Notification.Type.ERROR_MESSAGE);
        }
    }

    public TextField getAdiField() {
        return adiField;
    }

    public TextField getSoyadiField() {
        return soyadiField;
    }

    public TextField getKullaniciAdiField() {
        return kullaniciAdiField;
    }

    public TextField getSifreField() {
        return sifreField;
    }

    public TextField getSifreTekrarField() {
        return sifreTekrarField;
    }
}

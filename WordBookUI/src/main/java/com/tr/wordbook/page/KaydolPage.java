package com.tr.wordbook.page;

import com.tr.wordbook.WordBookUI;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.service.Md5Service;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.tr.wordbook.standart.MyVerticalLayout;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Configurable
public class KaydolPage extends VerticalLayout {

    private KullaniciEntityService kullaniciEntityService;
    private TextField adiField;
    private TextField soyadiField;
    private TextField kullaniciAdiField;
    private PasswordField sifreField;
    private PasswordField sifreTekrarField;
    private Button kaydolButton;

    public KaydolPage(){
        super();
        kullaniciEntityService = ((WordBookUI) UI.getCurrent()).getApplicationContext().getBean(KullaniciEntityService.class);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        initFieldsPanel();
        addListeners();
    }

    private void initFieldsPanel(){

        MyVerticalLayout layout1 = new MyVerticalLayout();

        Panel kaydolPanel = new Panel();
        kaydolPanel.setWidth("30%");
        kaydolPanel.setCaption("Kaydol");
        VerticalLayout kaydolPanelLayout = new VerticalLayout();
        kaydolPanelLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        kaydolPanel.setContent(kaydolPanelLayout);

        adiField = new TextField();
        adiField.setCaption("Adı");
        kaydolPanelLayout.addComponent(adiField);

        soyadiField = new TextField();
        soyadiField.setCaption("Soyadı");
        kaydolPanelLayout.addComponent(soyadiField);

        kullaniciAdiField = new TextField();
        kullaniciAdiField.setCaption("Kullanıcı Adı");
        kullaniciAdiField.setDescription("En az 5 karakter");
        kaydolPanelLayout.addComponent(kullaniciAdiField);

        sifreField = new PasswordField();
        sifreField.setCaption("Şifre");
        sifreField.setDescription("En az 8 karakter olmalıdır.");
        kaydolPanelLayout.addComponent(sifreField);

        sifreTekrarField = new PasswordField();
        sifreTekrarField.setCaption("Şifre (Tekrar)");
        sifreTekrarField.setDescription("En az 8 karakter olmalıdır.");
        kaydolPanelLayout.addComponent(sifreTekrarField);

        kaydolButton = new Button();
        kaydolButton.setCaption("Kaydol");
        kaydolPanelLayout.addComponent(kaydolButton);

        layout1.addComponent(kaydolPanel);

        addComponent(layout1);

    }

    private void addListeners() {

        kaydolButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                kaydol();
            }
        });
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
                Notification.show("Tebrikler. Kaydolma işlemi başarılı!", Notification.Type.HUMANIZED_MESSAGE);
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

package com.tr.wordbook;

import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.Word;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.page.KaydolPage;
import com.tr.wordbook.page.KelimeEklePage;
import com.tr.wordbook.page.KelimePage;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.tr.wordbook.service.entityservice.WordBookEntityService;
import com.vaadin.annotations.Theme;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@SpringUI
@Theme("valo")
public class WordBookUI extends UI {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WordBookEntityService wordBookEntityService;

    @Autowired
    KullaniciEntityService kullaniciEntityService;

    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout lytWordList = new VerticalLayout();
    private Label lblHeader;
    private HorizontalLayout headerLayout;
    private HorizontalLayout navigationLayout;
    private VerticalLayout baslikLayout;
    private HorizontalLayout contentLayout;

    private TextField usernameField;
    private PasswordField passwordField;

    private String username;
    private String password;

    private boolean isKullaniciLogin;

    private Button kelimeEkleButton;
    private Button homePageButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setupLayout();
        addHeader();

        if (isKullaniciLogin){

            addButtons();

        } else {

            addLoginLayout();
        }
    }

    private void addLoginLayout() {

        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Panel loginPanel = new Panel();
        loginPanel.setWidth("30%");
        loginPanel.setCaption("Giriş");
        VerticalLayout loginPanelLayaot = new VerticalLayout();
        loginPanelLayaot.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        loginPanel.setContent(loginPanelLayaot);

        usernameField = new TextField();
        usernameField.setCaption("Kullanıcı Adı");
        loginPanelLayaot.addComponent(usernameField);

        passwordField = new PasswordField();
        passwordField.setCaption("Şifre");
        loginPanelLayaot.addComponent(passwordField);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button loginButton= new Button();
        loginButton.setCaption("Giriş");
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                girisYap();
            }
        });

        Button signInButton = new Button();
        signInButton.setCaption("Kaydol");
        signInButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                openKaydolPage();
            }
        });

        buttonLayout.addComponent(loginButton);
        buttonLayout.addComponent(signInButton);


        loginPanelLayaot.addComponent(buttonLayout);

        loginLayout.addComponent(loginPanel);

        mainLayout.addComponent(loginLayout);
    }

    private void openKaydolPage() {
        mainLayout.removeAllComponents();

        addHeader();

        KaydolPage kaydolPage = new KaydolPage();
        mainLayout.addComponent(kaydolPage);
    }

    private void girisYap() {

        Kullanici kullanici = kullaniciEntityService.findKullaniciByKullaniciAdiAndSifre(usernameField.getValue(), passwordField.getValue());

        if (kullanici != null){

            username = usernameField.getValue();
            password = passwordField.getValue();
            isKullaniciLogin = true;

            mainLayout.removeAllComponents();
            addHeader();
            addButtons();
        } else {
            Notification.show("Kullanıcı Bulunamadı!", Notification.Type.ERROR_MESSAGE);
        }

    }

    private void setupLayout() {
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(mainLayout);
    }

    private void addHeader() {

        headerLayout = new HorizontalLayout();
        lblHeader = new Label("WORD BOOK");
        lblHeader.addStyleName(ValoTheme.LABEL_H1);
        lblHeader.setSizeUndefined();
        headerLayout.addComponent(lblHeader);
        headerLayout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                ekraniTemizle();
            }
        });

        mainLayout.addComponent(headerLayout);

    }

    private void addButtons(){

        navigationLayout = new HorizontalLayout();
        navigationLayout.setHeight("50px");

        homePageButton = new Button();
        homePageButton.setIcon(FontAwesome.HOME);
        homePageButton.setCaption("Anasayfa");
        homePageButton.setWidth("100%");
        homePageButton.setHeight("100%");
        homePageButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        homePageButton.addStyleName(ValoTheme.BUTTON_HUGE);
        homePageButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ekraniTemizle();
            }
        });
        navigationLayout.addComponent(homePageButton);

        kelimeEkleButton = new Button();
        kelimeEkleButton.setIcon(FontAwesome.PLUS);
        kelimeEkleButton.setCaption("Kelime Ekle");
        kelimeEkleButton.setWidth("100%");
        kelimeEkleButton.setHeight("100%");
        kelimeEkleButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        kelimeEkleButton.addStyleName(ValoTheme.BUTTON_HUGE);
        kelimeEkleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                kelimeEkle();
            }
        });
        navigationLayout.addComponent(kelimeEkleButton);

        Button calisButton = new Button();
        calisButton.setIcon(FontAwesome.PENCIL_SQUARE_O);
        calisButton.setCaption("Çalış");
        calisButton.setWidth("100%");
        calisButton.setHeight("100%");
        calisButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        calisButton.addStyleName(ValoTheme.BUTTON_HUGE);
        calisButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                calis();
            }
        });
        navigationLayout.addComponent(calisButton);

        Button raporlaButton = new Button();
        raporlaButton.setIcon(FontAwesome.PIE_CHART);
        raporlaButton.setCaption("Rapor");
        raporlaButton.setWidth("100%");
        raporlaButton.setHeight("100%");
        raporlaButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        raporlaButton.addStyleName(ValoTheme.BUTTON_HUGE);
        navigationLayout.addComponent(raporlaButton);

        Button ayarlarButton = new Button();
        ayarlarButton.setIcon(FontAwesome.WRENCH);
        ayarlarButton.setCaption("Ayarlar");
        ayarlarButton.setWidth("100%");
        ayarlarButton.setHeight("100%");
        ayarlarButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        ayarlarButton.addStyleName(ValoTheme.BUTTON_HUGE);
        navigationLayout.addComponent(ayarlarButton);

        navigationLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        navigationLayout.setHeight("80px");

        mainLayout.addComponent(navigationLayout);
    }

    private void kelimeEkle() {

        KelimeEklePage kelimeEklePage = new KelimeEklePage(wordBookEntityService.findAllWord());
        kelimeEklePage.setWidth("100%");

        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(kelimeEklePage);

    }

    private void calis(){

        KelimePage kelimePage = new KelimePage(wordBookEntityService.findAllWordByNotEzberlendi(EnumSecimEH.EVET));
        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(kelimePage);
    }

    private void addForm() {

        contentLayout = new HorizontalLayout();
        contentLayout.setSpacing(true);
        contentLayout.setWidth("80%");

        TextField textField = new TextField();
        textField.setWidth("80%");

        Button button = new Button("");
        button.setIcon(FontAwesome.PLUS);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                String text = textField.getValue();
                Word newWord = new Word(text);
                newWord = wordBookEntityService.save(newWord);
                Notification.show("Kelime eklendi.", Notification.Type.HUMANIZED_MESSAGE);

                textField.clear();
                textField.focus();

                ekraniTemizle();
            }
        });
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);

        contentLayout.addComponents(textField, button);
        contentLayout.setExpandRatio(textField, 1);
        mainLayout.addComponent(contentLayout);

        lytWordList.setWidth("80%");
        mainLayout.addComponent(lytWordList);

    }

    public void ekraniTemizle(){

        mainLayout.removeAllComponents();
        if (isKullaniciLogin){

            mainLayout.addComponent(headerLayout);
            mainLayout.addComponent(navigationLayout);
        } else {
            setupLayout();
            addHeader();
            addLoginLayout();
        }

    }
}

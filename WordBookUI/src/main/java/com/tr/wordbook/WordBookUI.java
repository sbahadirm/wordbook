package com.tr.wordbook;

import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.page.ArkadaslarPage;
import com.tr.wordbook.page.KaydolPage;
import com.tr.wordbook.page.KelimeEklePage;
import com.tr.wordbook.page.KelimePage;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.tr.wordbook.service.entityservice.KelimeEntityService;
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
    private KelimeEntityService wordBookEntityService;

    @Autowired
    KullaniciEntityService kullaniciEntityService;

    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout lytWordList = new VerticalLayout();
    private Label lblHeader;
    private VerticalLayout headerLayout;
    private HorizontalLayout navigationLayout;
    private VerticalLayout baslikLayout;
    private HorizontalLayout contentLayout;

    private TextField usernameField;
    private PasswordField passwordField;

    private static Kullanici kullanici;

    private Button kelimeEkleButton;
    private Button homePageButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        initGiris();
    }

    private void initGiris() {
        setupLayout();
        addHeader();

        if (kullanici != null){

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

        if (kullanici == null){
            if (usernameField.getValue() != null && passwordField.getValue() != null){
                kullanici = kullaniciEntityService.findKullaniciByKullaniciAdiAndSifre(usernameField.getValue(), passwordField.getValue());
            }
        }

        if (kullanici != null){
            mainLayout.removeAllComponents();
            addHeader();
            addButtons();
        } else {
            Notification.show("Kullanıcı Bulunamadı!", Notification.Type.ERROR_MESSAGE);
        }

    }

    private void setupLayout() {
        mainLayout.removeAllComponents();
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(mainLayout);
    }

    private void addHeader() {

        headerLayout = new VerticalLayout();

        HorizontalLayout headerLayout1 = new HorizontalLayout();
        headerLayout1.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        headerLayout1.setSizeFull();

        HorizontalLayout headerLayout2 = new HorizontalLayout();
        headerLayout2.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        headerLayout2.setSizeFull();
        headerLayout2.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                ekraniTemizle();
            }
        });

        Button signOutButton = new Button();
        signOutButton.setCaption("Çıkış");
        signOutButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signOutButton.setVisible(kullanici != null);
        signOutButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                kullanici = null;
                initGiris();
            }
        });
        headerLayout1.addComponent(signOutButton);

        lblHeader = new Label("WORD BOOK");
        lblHeader.addStyleName(ValoTheme.LABEL_H1);
        lblHeader.addStyleName(ValoTheme.LABEL_BOLD);
        lblHeader.setSizeUndefined();
        headerLayout2.addComponent(lblHeader);

        headerLayout.addComponent(headerLayout1);
        headerLayout.addComponent(headerLayout2);

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

        Button arkadaslarButton = new Button();
        arkadaslarButton.setIcon(FontAwesome.USERS);
        arkadaslarButton.setCaption("Arkadaşlar");
        arkadaslarButton.setWidth("100%");
        arkadaslarButton.setHeight("100%");
        arkadaslarButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        arkadaslarButton.addStyleName(ValoTheme.BUTTON_HUGE);
        arkadaslarButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                openArkadaslarPage();
            }
        });
        navigationLayout.addComponent(arkadaslarButton);

        navigationLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        navigationLayout.setHeight("80px");

        mainLayout.addComponent(navigationLayout);
    }

    private void openArkadaslarPage() {

        ArkadaslarPage arkadaslarPage = new ArkadaslarPage();
        arkadaslarPage.setWidth("100%");

        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(arkadaslarPage);
    }

    private void kelimeEkle() {

        KelimeEklePage kelimeEklePage = new KelimeEklePage();
        kelimeEklePage.setWidth("100%");

        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(kelimeEklePage);

    }

    private void calis(){

        KelimePage kelimePage = new KelimePage();
        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(kelimePage);
    }

    public void ekraniTemizle(){

        mainLayout.removeAllComponents();
        if (kullanici != null){

            mainLayout.addComponent(headerLayout);
            mainLayout.addComponent(navigationLayout);
        } else {
            setupLayout();
            addHeader();
            addLoginLayout();
        }

    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Kullanici getKullanici(){
        return kullanici;
    }
}

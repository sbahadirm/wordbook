package com.tr.wordbook;

import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.enums.EnumMenu;
import com.tr.wordbook.page.*;
import com.tr.wordbook.service.entityservice.KullaniciEntityService;
import com.tr.wordbook.service.entityservice.KelimeEntityService;
import com.vaadin.annotations.Theme;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.File;

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
//    private HorizontalLayout navigationLayout;
    private VerticalLayout baslikLayout;
    private HorizontalLayout contentLayout;

    private TextField usernameField;
    private PasswordField passwordField;

    private Kullanici kullanici;

    private Button kelimeEkleButton;
    private Button homePageButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        initGiris();
    }

    public void initGiris() {
        setupLayout();
        addHeader();

        kullanici = (Kullanici) getSession().getAttribute("kullanici");
        if (kullanici == null){

            addLoginLayout();
//            addButtons();

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
            getSession().setAttribute("kullanici", kullanici);
            mainLayout.removeAllComponents();
            addHeader();
            calis();
//            addButtons();
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

        HorizontalLayout sagUstNavigationLayout = new HorizontalLayout();
        sagUstNavigationLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        sagUstNavigationLayout.setVisible(kullanici != null);

        HorizontalLayout ustNavigationLayout = new HorizontalLayout();
        ustNavigationLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        ustNavigationLayout.setSizeFull();

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        headerLayout.setSizeFull();
        headerLayout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                ekraniTemizle();
            }
        });

        // A feedback component
        final Label selection = new Label("-");

        // Define a common menu command for all the menu items.
        MenuBar.Command mycommand = new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                openSelectedPage(selectedItem);
            }
        };

        MenuBar barmenu = new MenuBar();
        sagUstNavigationLayout.addComponent(barmenu);

        MenuBar.MenuItem kelimeler = barmenu.addItem(EnumMenu.KELIMELER.name(), null, null);

        // Submenu
        MenuBar.MenuItem kelimeEkle = kelimeler.addItem(EnumMenu.KELIME_EKLE.name(), null, mycommand);
        kelimeEkle.setIcon(FontAwesome.PLUS);
        MenuBar.MenuItem kategoriEkle = kelimeler.addItem(EnumMenu.KATEGORI_EKLE.name(), null, mycommand);
        kategoriEkle.setIcon(FontAwesome.PLUS);

        // Another top-level item
        MenuBar.MenuItem calis = barmenu.addItem(EnumMenu.CALIS.name(), null, mycommand);
        calis.setIcon(FontAwesome.PENCIL_SQUARE_O);

        // Yet another top-level item
        MenuBar.MenuItem profil = barmenu.addItem(EnumMenu.PROFIL.name(), null, null);

        MenuBar.MenuItem profilim = profil.addItem(EnumMenu.PROFILIM.name(), null, mycommand);
        profilim.setIcon(FontAwesome.USER);

        MenuBar.MenuItem arkadaslar = profil.addItem(EnumMenu.ARKADASLAR.name(), null, mycommand);
        arkadaslar.setIcon(FontAwesome.USER_PLUS);

        MenuBar.MenuItem ayarlar = profil.addItem(EnumMenu.AYARLAR.name(), null, mycommand);
        ayarlar.setIcon(FontAwesome.WRENCH);

        MenuBar.MenuItem rapor = profil.addItem(EnumMenu.RAPOR.name(), null, mycommand);
        rapor.setIcon(FontAwesome.PIE_CHART);

        MenuBar.MenuItem cikis = profil.addItem(EnumMenu.CIKIS.name(), null, mycommand);
        cikis.setIcon(FontAwesome.POWER_OFF);


        ustNavigationLayout.addComponent(sagUstNavigationLayout);

        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath +
                "/WEB-INF/image.png"));

        Image image = new Image("", resource);
        image.addStyleName(ValoTheme.LABEL_BOLD);
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                ekraniTemizle();
            }
        });

        headerLayout.addComponent(image);
        this.headerLayout.addComponent(ustNavigationLayout);
        this.headerLayout.addComponent(headerLayout);

        mainLayout.addComponent(this.headerLayout);
    }

    private void openSelectedPage(MenuBar.MenuItem selectedItem) {

        if (selectedItem.getText().equals(EnumMenu.KELIME_EKLE.name())){
            kelimeEkle();
        } else if (selectedItem.getText().equals(EnumMenu.KATEGORI_EKLE.name())){
            openKategoriEklePage();
        } else if (selectedItem.getText().equals(EnumMenu.CALIS.name())){
            calis();
        } else if (selectedItem.getText().equals(EnumMenu.PROFILIM.name())){
            openProfilPage();
        } else if (selectedItem.getText().equals(EnumMenu.AYARLAR.name())){
            // ayarlar page
        } else if (selectedItem.getText().equals(EnumMenu.RAPOR.name())){
            // rapor page
        } else if (selectedItem.getText().equals(EnumMenu.CIKIS.name())){
            cikisYap();
        } else if (selectedItem.getText().equals(EnumMenu.ARKADASLAR.name())){
            openArkadaslarPage();
        } else {

        }

    }

    private void cikisYap() {
        kullanici = null;
        getSession().setAttribute("kullanici", null);
        initGiris();
    }

    private void openProfilPage() {

        ProfilPage profilPage = new ProfilPage();
        profilPage.setWidth("100%");

        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
//        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(profilPage);

    }

//    private void addButtons(){
//
//        navigationLayout = new HorizontalLayout();
//        navigationLayout.setHeight("50px");
//
//        homePageButton = new Button();
//        homePageButton.setIcon(FontAwesome.HOME);
//        homePageButton.setCaption("Anasayfa");
//        homePageButton.setWidth("100%");
//        homePageButton.setHeight("100%");
//        homePageButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        homePageButton.addStyleName(ValoTheme.BUTTON_HUGE);
//        homePageButton.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                ekraniTemizle();
//            }
//        });
//        navigationLayout.addComponent(homePageButton);
//
//        kelimeEkleButton = new Button();
//        kelimeEkleButton.setIcon(FontAwesome.PLUS);
//        kelimeEkleButton.setCaption("Kelime Ekle");
//        kelimeEkleButton.setWidth("100%");
//        kelimeEkleButton.setHeight("100%");
//        kelimeEkleButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        kelimeEkleButton.addStyleName(ValoTheme.BUTTON_HUGE);
//        kelimeEkleButton.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                kelimeEkle();
//            }
//        });
//        navigationLayout.addComponent(kelimeEkleButton);
//
//        Button calisButton = new Button();
//        calisButton.setIcon(FontAwesome.PENCIL_SQUARE_O);
//        calisButton.setCaption("Çalış");
//        calisButton.setWidth("100%");
//        calisButton.setHeight("100%");
//        calisButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        calisButton.addStyleName(ValoTheme.BUTTON_HUGE);
//        calisButton.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                calis();
//            }
//        });
//        navigationLayout.addComponent(calisButton);
//
//        Button raporlaButton = new Button();
//        raporlaButton.setIcon(FontAwesome.PIE_CHART);
//        raporlaButton.setCaption("Rapor");
//        raporlaButton.setWidth("100%");
//        raporlaButton.setHeight("100%");
//        raporlaButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        raporlaButton.addStyleName(ValoTheme.BUTTON_HUGE);
//        navigationLayout.addComponent(raporlaButton);
//
//        Button arkadaslarButton = new Button();
//        arkadaslarButton.setIcon(FontAwesome.USERS);
//        arkadaslarButton.setCaption("Arkadaşlar");
//        arkadaslarButton.setWidth("100%");
//        arkadaslarButton.setHeight("100%");
//        arkadaslarButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        arkadaslarButton.addStyleName(ValoTheme.BUTTON_HUGE);
//        arkadaslarButton.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                openArkadaslarPage();
//            }
//        });
//        navigationLayout.addComponent(arkadaslarButton);
//
//        navigationLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
//        navigationLayout.setHeight("80px");
//
//        mainLayout.addComponent(navigationLayout);
//    }

    private void openArkadaslarPage() {

        ArkadaslarPage arkadaslarPage = new ArkadaslarPage();
        arkadaslarPage.setWidth("100%");
        arkadaslarPage.setMargin(false);

        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
//        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(arkadaslarPage);
    }

    private void kelimeEkle() {

        KelimeEklePage kelimeEklePage = new KelimeEklePage();
        kelimeEklePage.setWidth("100%");

        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
//        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(kelimeEklePage);

    }

    private void calis(){

        KelimePage kelimePage = new KelimePage();
        mainLayout.removeAllComponents();

        mainLayout.addComponent(headerLayout);
//        mainLayout.addComponent(navigationLayout);
        mainLayout.addComponent(kelimePage);
    }

    public void ekraniTemizle(){

        mainLayout.removeAllComponents();
        if (kullanici != null){

            mainLayout.addComponent(headerLayout);
//            mainLayout.addComponent(navigationLayout);
        } else {
            setupLayout();
            addHeader();
            addLoginLayout();
        }

    }

    private void openKategoriEklePage() {

    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Kullanici getKullanici(){
        return kullanici;
    }

    public static WordBookUI get() {
        return (WordBookUI) getCurrent();
    }
}
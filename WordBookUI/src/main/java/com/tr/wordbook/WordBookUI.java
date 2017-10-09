package com.tr.wordbook;

import com.tr.wordbook.domain.Word;
import com.tr.wordbook.service.WordBookEntityService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

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

    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout lytWordList = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        refreshWords();
    }

    private void setupLayout() {
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(mainLayout);
    }

    private void addHeader() {
        Label lblHeader = new Label("WORD BOOK");
        lblHeader.addStyleName(ValoTheme.LABEL_H1);
        lblHeader.setSizeUndefined();
        mainLayout.addComponent(lblHeader);
    }

    private void addForm() {

        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setSpacing(true);
        formLayout.setWidth("80%");

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

                refreshWords();
            }
        });
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);

        formLayout.addComponents(textField, button);
        formLayout.setExpandRatio(textField, 1);
        mainLayout.addComponent(formLayout);

        lytWordList.setWidth("80%");
        mainLayout.addComponent(lytWordList);

    }

    public void refreshWords(){

        lytWordList.removeAllComponents();

        List<Word> wordList = wordBookEntityService.findAll();
        for (Word word : wordList) {
            lytWordList.addComponent(new WordLayout(word));
        }
    }
}

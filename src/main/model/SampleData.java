package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kpant on 6/22/17.
 */
public class SampleData {

    final ObservableList<EmailMessageBean> inBox = FXCollections.observableArrayList(
            new EmailMessageBean("Hello from Karan", "abc@abc.com", 550000, "<b>Hello</b>", false),
            new EmailMessageBean("Hello from Sita", "sita@sita.com", 234, "<b>Hello</b>", true),
            new EmailMessageBean("Hello from rita", "rita@rita.com", 3214, "<b>Hello</b>", true),
            new EmailMessageBean("Hello from Geeta", "Geeta@Geeta.com", 1414, "<b>Hello</b>", true)
    );

    final ObservableList<EmailMessageBean> sent = FXCollections.observableArrayList(
            new EmailMessageBean("sent from Karan", "abc@abc.com", 550000, "<b>Hello</b>", true),
            new EmailMessageBean("sent from Sita", "sita@sita.com", 234, "<b>Hello</b>", false),
            new EmailMessageBean("sent from rita", "rita@rita.com", 3214, "<b>Hello</b>", false),
            new EmailMessageBean("sent from Geeta", "Geeta@Geeta.com", 1414, "<b>Hello</b>", true)
    );

    final ObservableList<EmailMessageBean> spam = FXCollections.observableArrayList(
            new EmailMessageBean("spam from Karan", "abc@abc.com", 550000, "<b>Hello</b>", true),
            new EmailMessageBean("spam from Sita", "sita@sita.com", 234, "<b>Hello</b>", false),
            new EmailMessageBean("spam from rita", "rita@rita.com", 3214, "<b>Hello</b>", false),
            new EmailMessageBean("spam from Geeta", "Geeta@Geeta.com", 1414, "<b>Hello</b>", true),
            new EmailMessageBean("spam from Karan", "abc@abc.com", 550000, "<b>Hello</b>", true),
            new EmailMessageBean("spam from Sita", "sita@sita.com", 234, "<b>Hello</b>", false),
            new EmailMessageBean("spam from rita", "rita@rita.com", 3214, "<b>Hello</b>", true),
            new EmailMessageBean("spam from Geeta", "Geeta@Geeta.com", 1414, "<b>Hello</b>", true),
            new EmailMessageBean("spam from Karan", "abc@abc.com", 550000, "<b>Hello</b>", false),
            new EmailMessageBean("spam from Sita", "sita@sita.com", 234, "<b>Hello</b>", true),
            new EmailMessageBean("spam from rita", "rita@rita.com", 3214, "<b>Hello</b>", true),
            new EmailMessageBean("spam from Geeta", "Geeta@Geeta.com", 1414, "<b>Hello</b>", true)
    );

    public Map<String, ObservableList<EmailMessageBean>> emailFolders = new HashMap<>();

    public SampleData() {
        emailFolders.put("inBox", inBox);
        emailFolders.put("sent", sent);
        emailFolders.put("spam", spam);
    }
}

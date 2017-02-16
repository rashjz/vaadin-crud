package rashjz.info.component;

import com.vaadin.ui.Window;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;

/**
 * Created by rasha_000 on 2/4/2017.
 */
public class AboutWindows extends Window {
    public AboutWindows() {
        super("About Application"); // Set window caption
        center();

        // Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        Label header = new Label("Yol Nəqliyyat hadisələrinin Qeydiyyat sistemi");
        content.addComponent(header);
        content.addComponent(new Label("Copyright by rashjz Bakı 2017"));
        content.setComponentAlignment(header, Alignment.MIDDLE_CENTER);
        content.setMargin(true);


        this.setWidth(35, Unit.PERCENTAGE);
        this.setHeight(35, Unit.PERCENTAGE);
        // Disable the close button
        setClosable(true);

        // Trivial logic for closing the sub-window
        Button ok = new Button("OK");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                close(); // Close the sub-window
            }
        });

        content.addComponent(ok);
        content.setComponentAlignment(ok, Alignment.BOTTOM_CENTER);


        content.setSizeFull();
        setContent(content);

    }
}
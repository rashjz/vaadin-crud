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
        content.addComponent(new Label("Just say it's OK!"));
        content.setMargin(true);
        setContent(content);
        this.setWidth(50, Unit.PERCENTAGE);
        this.setHeight(50,Unit.PERCENTAGE);
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
    }
}
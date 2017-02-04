package rashjz.info.util;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

/**
 * Created by rasha_000 on 1/29/2017.
 */
public class VaadinUtils {

    public static final void notify(String message, String type) {
        new Notification(message, type).show(Page.getCurrent());
    }

    public static final void logOut() {
        VaadinSession.getCurrent().getSession().invalidate();
        Page.getCurrent().reload();
    }

    public static void changeContent(Component view, AbstractComponentContainer content) {
        content.removeAllComponents();
//        content.addComponent(view);
//        content.setComponentAlignment(view, Alignment.MIDDLE_CENTER);
    }
    public void createWindows(){


    }
}

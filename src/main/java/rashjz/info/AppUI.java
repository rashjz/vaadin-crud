package rashjz.info;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.authentication.AccessControl;
import rashjz.info.authentication.BasicAccessControl;
import rashjz.info.jpa.CustomerRepository;

import javax.servlet.annotation.WebServlet;

/**
 *
 */

@SpringUI
@Theme("mytheme")
public class AppUI extends UI {
    private AccessControl accessControl = new BasicAccessControl();
    private final CustomerRepository repository;
    @Autowired
        public AppUI(CustomerRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("Application");

//        setContent(new MainLayout(repository));
        if (!accessControl.isUserSignedIn()) {
            setContent(new LoginView(accessControl, new LoginView.LoginListener() {
                @Override
                public void loginSuccessful() {
                    showMainView();
                }
            }));
        } else {
            showMainView();
        }
    }
    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainLayout(repository));
        getNavigator().navigateTo(getNavigator().getState());
    }

    public static AppUI get() {
        return (AppUI) UI.getCurrent();
    }

    public AccessControl getAccessControl() {
        return accessControl;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AppUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }


}

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
import rashjz.info.authentication.LoginListener;
import rashjz.info.jpa.AccidentRepository;
import rashjz.info.jpa.UsersRepository;

import javax.servlet.annotation.WebServlet;

/**
 *
 */

@SpringUI
@Theme("mytheme")
public class AppUI extends UI {

    private AccessControl accessControl;
    public AccidentRepository accidentRepository;
    public UsersRepository usersRepository;

    @Autowired
    public AppUI(AccidentRepository accidentRepository, UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.accidentRepository = accidentRepository;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        accessControl = new BasicAccessControl(usersRepository);

        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("BDYPI QAS");

        if (!accessControl.isUserSignedIn()) {
            setContent(new LoginView(accessControl
                    , (LoginListener) () -> showMainView())
            );

        } else {
            showMainView();
        }
    }

    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainLayout(accidentRepository,usersRepository));
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

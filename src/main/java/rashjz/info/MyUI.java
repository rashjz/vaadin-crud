package rashjz.info;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.jpa.CustomerRepository;

import javax.servlet.annotation.WebServlet;

/**
 *
 */

@SpringUI
@Theme("mytheme")
public class MyUI extends UI {

    private final CustomerRepository repository;
    @Autowired
        public MyUI(CustomerRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new MainLayout(repository));

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

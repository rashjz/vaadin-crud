package rashjz.info;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.jpa.CustomerRepository;

public class MainLayout extends MainLayoutDesign {

    private final CustomerRepository repository;

    @Autowired
    public MainLayout(CustomerRepository repository) {
        this.repository = repository;
        Navigator navigator = new Navigator(UI.getCurrent(), contentPanel);
        navigator.setErrorView(ErrorView.class);
        navigator.addView(StatsView.VIEW_NAME, new StatsView(repository));
        navigator.addView(PluginsView.VIEW_NAME, PluginsView.class);
        navigator.addView(PermissionsView.VIEW_NAME, PermissionsView.class);




        menuButton1.addClickListener(event -> doNavigate(StatsView.VIEW_NAME));
        menuButton2.addClickListener(event -> doNavigate(PluginsView.VIEW_NAME));
        menuButton3.addClickListener(event -> doNavigate(PermissionsView.VIEW_NAME));
        logout.addClickListener(event ->    {VaadinSession.getCurrent().getSession().invalidate();
        Page.getCurrent().reload();});
        if (navigator.getState().isEmpty()) {
            navigator.navigateTo(StatsView.VIEW_NAME);
        } else {
            navigator.navigateTo(navigator.getState());
        }
    }

    private void doNavigate(String viewName) {
        getUI().getNavigator().navigateTo(viewName);
    }
}

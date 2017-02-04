package rashjz.info;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.jpa.CustomerRepository;
import rashjz.info.util.VaadinUtils;

public class MainLayout extends MainLayoutDesign {

    private final CustomerRepository repository;

    @Autowired
    public MainLayout(CustomerRepository repository) {
        this.repository = repository;


        Navigator navigator = new Navigator(UI.getCurrent(), contentPanel);
        navigator.setErrorView(ErrorView.class);
        navigator.addView(AccidentView.VIEW_NAME, new AccidentView(repository, menuLayout));
        navigator.addView(PluginsView.VIEW_NAME, PluginsView.class);
        navigator.addView(PermissionsView.VIEW_NAME, PermissionsView.class);


        accident.addClickListener(event -> doNavigate(AccidentView.VIEW_NAME));
        menuButton2.addClickListener(event -> doNavigate(PluginsView.VIEW_NAME));
        menuButton3.addClickListener(event -> doNavigate(PermissionsView.VIEW_NAME));
        hideMenu.addClickListener(event -> menuLayout.setVisible(false));
        logout.addClickListener(event -> VaadinUtils.logOut());


        if (navigator.getState().isEmpty()) {
            navigator.navigateTo(AccidentView.VIEW_NAME);
        } else {
            navigator.navigateTo(navigator.getState());
        }
    }

    private void doNavigate(String viewName) {
        getUI().getNavigator().navigateTo(viewName);
    }
}

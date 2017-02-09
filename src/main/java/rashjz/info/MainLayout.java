package rashjz.info;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.jpa.CustomerRepository;
import rashjz.info.jpa.UsersRepository;
import rashjz.info.util.VaadinUtils;

public class MainLayout extends MainLayoutDesign {

    private final CustomerRepository customerRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public MainLayout(CustomerRepository customerRepository,UsersRepository usersRepository) {
        this.customerRepository = customerRepository;
        this.usersRepository = usersRepository;

        Navigator navigator = new Navigator(UI.getCurrent(), contentPanel);
        navigator.setErrorView(ErrorView.class);
        navigator.addView(AccidentView.VIEW_NAME, new AccidentView(customerRepository, menuLayout));
        navigator.addView(PluginsView.VIEW_NAME, PluginsView.class);
        navigator.addView(PermissionsView.VIEW_NAME, new PermissionsView(usersRepository));


        accident.addClickListener(event -> doNavigate(AccidentView.VIEW_NAME));
        menuButton2.addClickListener(event -> doNavigate(PluginsView.VIEW_NAME));
        permission.addClickListener(event -> doNavigate(PermissionsView.VIEW_NAME));
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

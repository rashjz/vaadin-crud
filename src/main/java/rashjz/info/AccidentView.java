package rashjz.info;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.vaadin.viritin.fields.MTable;
import rashjz.info.component.AboutWindows;
import rashjz.info.component.CustomerEditor;
import rashjz.info.domain.Customer;
import rashjz.info.jpa.CustomerRepository;
import rashjz.info.util.VaadinUtils;

import java.util.logging.Logger;

//import rashjz.info.component.TableLazyLoadContainer;

@SpringComponent
@UIScope
public class AccidentView extends AccidentViewDesign implements View {
    private final static Logger logger = Logger.getLogger(AccidentView.class.getName());
    public static final String VIEW_NAME = "accident";
    public static int PAGESIZE = 10;
    private final CustomerEditor editor;
    public final Grid grid;
    private final TextField filter;
    private final Button addNewBtn;
    private final MenuBar barmenu;
    public MenuBar.MenuItem main, about, contact, logout;
    private Navigator navigator;
    private VerticalLayout mainLayout;
    private HorizontalLayout actions;
    public static MTable table;

    public AccidentView(CustomerRepository repository, VerticalLayout menuLayout) {

        super(repository);
        this.editor = new CustomerEditor(repository);
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("Add Item", FontAwesome.PLUS_CIRCLE);
        this.barmenu = new MenuBar();
        //-------- initialize menu ------------------------
        initializeMenu(menuLayout);
        //-----------------------------------------------
    }


    @Override
    public void enter(ViewChangeEvent event) {
        logger.info("enter(ViewChangeEvent event) invoked ");
        //create table
        table = new MTable<>(Customer.class)
                .withProperties("id", "firstName", "lastName", "currency", "idate")
                .withColumnHeaders("id", "Ad", "Soyad", "Valuta", "Tarix")
                // setSortableProperties("name", "email")
                .withFullWidth();
        table.setSelectable(true);
        table.setCaption("Customer List with lazy loading");

        //design
        actions = new HorizontalLayout(filter, addNewBtn);
        mainLayout = new VerticalLayout(barmenu, actions, table, editor);
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setComponentAlignment(actions, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(editor, Alignment.MIDDLE_LEFT);

        editor.setChangeHandler(() -> {
            System.out.println("xxxxxxxxxx");
//            listCustomers(filter.getValue(), table.getPageLength(), table.getCurrentPage());
        });

        filter.setInputPrompt("Filter by last name");

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listCustomers(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        table.addItemClickListener(e -> {
            if (null == table.getValue())
                showEditor(false);
            else {
                showEditor(true);
                Customer customer = (Customer) table.getValue();
                editor.editCustomer(customer);
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "", "", null)));
        // Listen changes made by the editor, refresh data from backend

        listCustomers("");
        removeAllComponents();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
    }

    public void listCustomers(String text) {
        table.lazyLoadFrom(
                // entity fetching strategy
                (firstRow, asc, sortProperty) -> repository.findByFirstNameLike(
                        text + "%",
                        new PageRequest(firstRow / PAGESIZE, PAGESIZE,
                                asc ? Sort.Direction.ASC : Sort.Direction.DESC,
                                // fall back to id as "natural order"
                                sortProperty == null ? "id" : sortProperty))
                ,
                // count fetching strategy
                () -> (int) repository.countByFirstNameLike(text + "%"),
                PAGESIZE
        );
    }

    public void initializeMenu(VerticalLayout menuLayout) {
        main = barmenu.addItem("Main", FontAwesome.FLAG, (MenuBar.Command) selectedItem -> {
            table.setVisible(true);
            editor.setVisible(false);
        });
        about = barmenu.addItem("About", FontAwesome.INFO, (MenuBar.Command) selectedItem -> {
            AboutWindows aboutWindows = new AboutWindows();
            UI.getCurrent().addWindow(aboutWindows);
        });
        contact = barmenu.addItem("Show Menu", FontAwesome.MOBILE, (MenuBar.Command) selectedItem -> menuLayout.setVisible(true));
        logout = barmenu.addItem("LogOut", FontAwesome.SIGN_OUT, (MenuBar.Command) selectedItem -> VaadinUtils.logOut());
        barmenu.setSizeFull();
    }

    public void showEditor(boolean b) {
        actions.setVisible(!b);
        table.setVisible(!b);
        editor.setVisible(b);
    }
}

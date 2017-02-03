package rashjz.info;

import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.util.StringUtils;
import rashjz.info.component.CustomerEditor;
import rashjz.info.domain.Customer;
import rashjz.info.jpa.CustomerRepository;
import rashjz.info.util.VaadinUtils;
import rashjz.info.component.TableLazyLoadContainer;

import java.util.Date;
import java.util.logging.Logger;

@SpringComponent
@UIScope
public class AccidentView extends AccidentViewDesign implements View {
    private final static Logger logger = Logger.getLogger(AccidentView.class.getName());
    public static final String VIEW_NAME = "accident";

    private final CustomerEditor editor;
    public final Grid grid;
    private final TextField filter;
    private final Button addNewBtn;
    private final MenuBar barmenu;
    public MenuBar.MenuItem main, about, contact, logout;
    private Navigator navigator;
    private VerticalLayout mainLayout;
    private TableLazyLoadContainer container;
//    private ControlsLayout controlsLayout;

    public AccidentView(CustomerRepository repository, VerticalLayout menuLayout) {
        super(repository);
        this.editor = new CustomerEditor(repository);
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", FontAwesome.PLUS);
        this.barmenu = new MenuBar();
        container = new TableLazyLoadContainer(Customer.class);
        //-------- initialize menu ------------------------
        initializeMenu(menuLayout);
        //-----------------------------------------------
    }


    @Override
    public void enter(ViewChangeEvent event) {
        logger.info("enter(ViewChangeEvent event) invoked  *  *  *  *");


        PagedTable table = new PagedTable("Cədvəl");
        //new BeanItemContainer(Customer.class, repository.findAll())
        table.setContainerDataSource(container);
//        https://vaadin.com/directory#!addon/pagedtable  https://github.com/Peppe/PagedTable
        table.setPageLength(5);
        table.setVisibleColumns("id", "firstName", "lastName", "currency", "idate");
        table.setColumnHeaders("id", "Ad", "Soyad", "Valuta", "Tarix");
        HorizontalLayout components = table.createControls();

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        mainLayout = new VerticalLayout(barmenu, actions, table, components, editor);
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setComponentAlignment(actions, Alignment.TOP_CENTER);
//        mainLayout.setComponentAlignment(grid, Alignment.MIDDLE_LEFT);
        mainLayout.setComponentAlignment(editor, Alignment.MIDDLE_LEFT);

        table.setSelectable(true);
        table.setHeight(300, Sizeable.Unit.PIXELS);
        table.setWidth(99, Unit.PERCENTAGE);


        filter.setInputPrompt("Filter by last name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listCustomers(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        table.addItemClickListener(e -> {
            if (null == e.getItem()) editor.setVisible(false);
            else {
                Customer customer=(Customer)((BeanItem)e.getItem()).getBean();
                editor.editCustomer(customer);
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "", "", null)));
        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);
        removeAllComponents();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.TOP_LEFT);
    }

    public void listCustomers(String text) {
        if (StringUtils.isEmpty(text))
            grid.setContainerDataSource(new BeanItemContainer(Customer.class, repository.findAll()));
//            System.out.println("");
        else grid.setContainerDataSource(new BeanItemContainer(Customer.class,
                repository.findByLastNameStartsWithIgnoreCase(text)));
    }

    public void initializeMenu(VerticalLayout menuLayout) {
        main = barmenu.addItem("Main", FontAwesome.FLAG, null);
        about = barmenu.addItem("About", FontAwesome.INFO, null);
        contact = barmenu.addItem("Show Menu", FontAwesome.MOBILE, (MenuBar.Command) selectedItem -> menuLayout.setVisible(true));
        logout = barmenu.addItem("LogOut", FontAwesome.SIGN_OUT, (MenuBar.Command) selectedItem -> VaadinUtils.logOut());
        barmenu.setSizeFull();
    }
}

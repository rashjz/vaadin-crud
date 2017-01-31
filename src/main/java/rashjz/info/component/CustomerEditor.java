package rashjz.info.component;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.domain.Customer;
import rashjz.info.jpa.CustomerRepository;
import rashjz.info.util.NotifyUtil;

import java.util.Date;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout {

    @Autowired
    private final CustomerRepository repository;

    /**
     * The currently edited customer
     */
    private Customer customer;

    /* Fields to edit properties in Customer entity */
    public TextField firstName = new TextField("First name");
    public TextField lastName = new TextField("Last name");
    public ComboBox currency = new ComboBox("Select Currency");
    public DateField idate = new DateField();

    /* Action buttons */
    public Button save = new Button("Save", FontAwesome.SAVE);
    public Button cancel = new Button("Cancel");
    public Button delete = new Button("Delete", FontAwesome.TRASH_O);


    CssLayout actions = new CssLayout(save, cancel, delete);

    // Creates a new combobox using an existing container


    @Autowired
    public CustomerEditor(CustomerRepository repository) {
        this.repository = repository;

        addComponents(firstName, lastName, currency, idate, actions);


        //............................................................

        currency.setInputPrompt("No currency selected");
        currency.setFilteringMode(FilteringMode.CONTAINS);
        currency.setImmediate(true);

        // Disallow null selections
        currency.setNullSelectionAllowed(false);
        currency.addItem("GBP");
        currency.addItem("EUR");
        currency.addItem("USD");

        //............................................................
        idate.setValue(new Date());
        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(customer));
        delete.addClickListener(e -> repository.delete(customer));
        cancel.addClickListener(e -> editCustomer(customer));
        setVisible(false);

    }


    public interface ChangeHandler {

        void onChange();
    }

    public final void editCustomer(Customer c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            customer = repository.findOne(c.getId());
        } else {
            customer = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binueding" or programmatically
        // moving values from fields to entities befo689re saving
        BeanFieldGroup.bindFieldsUnbuffered(customer, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in firstName field automatically
        firstName.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
        currency.addValueChangeListener((Property.ValueChangeListener) event -> NotifyUtil.notify("" + event.getProperty().getValue(), Notification.Type.ERROR_MESSAGE.getStyle()));
    }

}

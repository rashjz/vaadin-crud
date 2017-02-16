package rashjz.info.component;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.domain.Accident;
import rashjz.info.domain.Citizen;
import rashjz.info.jpa.AccidentRepository;
import rashjz.info.jpa.EventTypeRepository;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout {

    private final AccidentRepository repository;
    private FieldGroup binder;

    @Autowired
    private EventTypeRepository eventTypeRepository;
    /**
     * The currently edited accident
     */
    private Accident accident;
    private PropertysetItem myfields;

    /* Fields to edit properties in Accident entity */
    public TextField location = new TextField("Location");
    public DateField insertdate = new DateField("Insert Date");
    public DateField actionDate = new DateField("Action Date");

    /* Action buttons */
    public Button save = new Button("Save", FontAwesome.SAVE);
    public Button cancel = new Button("Cancel");
    public Button delete = new Button("Delete", FontAwesome.TRASH_O);

    CssLayout actions = new CssLayout(save, cancel, delete);

    public CustomerEditor(AccidentRepository repository) {
        this.repository = repository;
        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(accident));
        delete.addClickListener(e -> repository.delete(accident));
        cancel.addClickListener(e -> editCustomer(accident));


        setVisible(false);

    }


    public interface ChangeHandler {

        void onChange();
    }

    public final void editCustomer(Accident c) {
        final boolean persisted = c.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            accident = repository.findOne(Integer.valueOf(c.getId()));
        } else {
            accident = c;
        }
        cancel.setVisible(persisted);

        addComponents(location, insertdate, actionDate);


        PropertysetItem item = new PropertysetItem();
        for (Citizen citizen : accident.getCitizens()) {

            myfields = new PropertysetItem();
            FormLayout layout = new FormLayout();
            myfields.addItemProperty("citizen.name", new ObjectProperty(citizen.getName()));
            myfields.addItemProperty("citizen.surname", new ObjectProperty(citizen.getSurname()));
            myfields.addItemProperty("citizen.patronymic", new ObjectProperty(citizen.getPatronymic()));


            Panel panel = new Panel("Citizen Panel " + citizen.getCitizenId());

            TextField name = new TextField("Citizen name");
            TextField surname = new TextField("Citizen surname");
            TextField patronymic = new TextField("Citizen patronymic");
            layout.addComponents(name, surname, patronymic);

            binder = new FieldGroup(myfields);
            binder.bind(name, "citizen.name");
            binder.bind(surname, "citizen.surname");
            binder.bind(patronymic, "citizen.patronymic");

            panel.setContent(layout);

            addComponent(layout);
        }

        myfields.addItemProperty("location", new ObjectProperty(accident.getLocation()));
        myfields.addItemProperty("actionDate", new ObjectProperty(accident.getActionDate()));
        myfields.addItemProperty("insertdate", new ObjectProperty(accident.getInsertDate()));

        binder.bind(insertdate, "insertdate");
        binder.bind(location, "location");
        binder.bind(actionDate, "actionDate");
        addComponents(actions);


        // Bind accident properties to similarly named fields
        // Could also use annotation or "manual binueding" or programmatically
        // moving values from fields to entities befo689re saving
//        BeanFieldGroup.bindFieldsUnbuffered(accident, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in firstName field automatically
        location.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());

    }

}

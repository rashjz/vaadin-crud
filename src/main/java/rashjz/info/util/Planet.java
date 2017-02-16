//package rashjz.info.util;
//
//import com.vaadin.data.Item;
//import com.vaadin.data.fieldgroup.FieldGroup;
//import com.vaadin.event.MouseEvents;
//import com.vaadin.ui.*;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Planet implements Serializable {
//    String name;
//    List<String> moons = new ArrayList<String>();
//
//    public Planet(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public List<String> getMoons() {
//        return moons;
//    }
//    public void setMoons(List<String> moons) {
//        this.moons = moons;
//    }
//}
//
//
//
//// Simple editor for string lists
//class ListEditor extends CustomField<List<String>> {
//    VerticalLayout fields = new VerticalLayout();
//
//    public ListEditor(String caption) {
//        setCaption(caption);
//    }
//
//    @Override
//    protected Component initContent() {
//        HorizontalLayout content = new HorizontalLayout();
//        content.addComponent(fields);
//
//        Button add = new Button("+"); // Java 8
//        content.addComponent(add);
//        content.setComponentAlignment(fields, Alignment.TOP_LEFT);
//        content.setComponentAlignment(add, Alignment.BOTTOM_LEFT);
//        return content;
//    }
//
//    void addItem(MouseEvents.ClickEvent event) {
//        List<String> list = getValue();
//        if (list == null)
//            list = new ArrayList<String>();
//        list.add("");
//        setValue(list);
//
//        final TextField tf = new TextField();
//        tf.addValueChangeListener(valueChange -> { // Java 8
//            int index = fields.getComponentIndex(tf);
//            List<String> values = getValue();
//            values.set(index, tf.getValue());
//            setValue(values);
//        });
//        fields.addComponent(tf);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public Class<? extends List<String>> getType() {
//        return (Class<List<String>>) (new ArrayList<String>()).getClass();
//    }
//
//    @Override
//    public List<String> getValue() {
//        return super.getValue();
//    }
//}
//
//// Define a form as a class that extends some layout
//class MyForm extends VerticalLayout {
//    // Member that will bind to the "name" property
//    private TextField name = new TextField("Name");
//
//    // Member that will bind to the "age" property
//    private ListEditor moons = new ListEditor("Moons");
//
//    private FieldGroup binder = new FieldGroup();
//
//    private Runnable notifyOk;
//
//    public MyForm(Item item, Runnable notifyOk) {
//        setCaption("Planet");
//        this.notifyOk = notifyOk;
//
//        FormLayout form = new FormLayout();
//        form.addComponents(name, moons);
//        addComponent(form);
//
//        addComponent(new Button("Commit"));
//
//        // Now bind the member fields to the item
//        binder.setItemDataSource(item);
//        binder.bindMemberFields(this);
//    }
//
//    void commit(MouseEvents.ClickEvent event) {
//        try {
//            binder.commit();
//            notifyOk.run();
//        } catch (FieldGroup.CommitException e) {
//            Notification.show("Commit failed");
//        }
//    }
//}
///*
//    // Here we use it
//    void onetomany(final VerticalLayout layout) {
//        Planet planet = new Planet("My Planet");
//        BeanItem<Planet> item = new BeanItem<Planet>(planet);
//        layout.addComponent(new MyForm(item, () -> { // Java 8
//            Notification.show(planet.name,
//                    planet.moons.toString(),
//                    Notification.Type.HUMANIZED_MESSAGE);
//        }));
//    }
//
//*/
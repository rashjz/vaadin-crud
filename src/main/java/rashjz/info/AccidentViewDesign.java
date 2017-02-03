package rashjz.info;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import rashjz.info.jpa.CustomerRepository;


@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class AccidentViewDesign extends VerticalLayout {

    @Autowired
    protected final CustomerRepository repository;

    public AccidentViewDesign(CustomerRepository repository) {
        this.repository = repository;
//        Design.read(this);
    }




}
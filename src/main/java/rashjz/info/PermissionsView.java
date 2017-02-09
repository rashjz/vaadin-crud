package rashjz.info;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import org.vaadin.viritin.fields.MTable;
import rashjz.info.domain.Users;
import rashjz.info.jpa.UsersRepository;

import java.util.List;

public class PermissionsView extends PermissionsViewDesign implements View {

    public static final String VIEW_NAME = "permissions";
    private MTable table;
    private UsersRepository repository;

    public PermissionsView(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        removeAllComponents();
        this.setMargin(true);
        this.setSpacing(true);
        table = new MTable<>(Users.class)
                .withProperties("id", "username", "password", "role", "status")
                .withColumnHeaders("id", "Ad", "Sifre", "Rolu", "status")
                // setSortableProperties("name", "email")
                .withFullWidth();
        table.setSelectable(false);
        List<Users> usersList = repository.findAll();
        table.setCaption("Istifadəçilər siyahısı");
        table.setRows(usersList);

        // Lazy binding for better optimized connection from the Vaadin Table to
        // Spring Repository. This approach uses less memory and database
        // resources. Use this approach if you expect you'll have lots of data
        // in your table. There are simpler APIs if you don't need sorting.
        // list.lazyLoadFrom(
        //         // entity fetching strategy
        //         (firstRow, asc, sortProperty) -> repo.findByNameLikeIgnoreCase(
        //                 likeFilter,
        //                 new PageRequest(
        //                         firstRow / PAGESIZE,
        //                         PAGESIZE,
        //                         asc ? Sort.Direction.ASC : Sort.Direction.DESC,
        //                         // fall back to id as "natural order"
        //                         sortProperty == null ? "id" : sortProperty
        //                 )
        //         ),
        //         // count fetching strategy
        //         () -> (int) repo.countByNameLike(likeFilter),
        //         PAGESIZE
        // );



        addComponent(table);

    }

}

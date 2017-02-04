package rashjz.info.component;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import org.springframework.data.domain.PageRequest;
import rashjz.info.AccidentView;
import rashjz.info.AppUI;
import rashjz.info.domain.Customer;

import java.util.List;

public class TableLazyLoadContainer extends BeanContainer {


    public TableLazyLoadContainer(Class type) {
        super(type);
    }

    @Override
    public int size() {
        // TODO: here you should get COUNT from database
        System.out.println("size -- - - - - -- : " + AccidentView.table.getCurrentPage());
        long size = AppUI.repository.findAll(new PageRequest(AccidentView.table.getCurrentPage(),5)).getTotalElements();
        System.out.println(size+"____________________");
        return (int) size;
    }

    @Override
    public BeanItem getItem(Object itemId) {
        return new BeanItem((Customer) itemId);
    }

    @Override
    public List<?> getItemIds(final int startIndex, final int numberOfItems) {
        // TODO: here you should place fetching data from database (it should be paged SQL of course)
        System.out.println("startIndex: " + startIndex + ", numberOfItems: " + numberOfItems);
        List<Customer> res = AppUI.repository.findAll(new PageRequest(startIndex, startIndex+5)).getContent();
//        size = AppUI.repository.findAll(pageable).getSize();
        return res;
    }
}
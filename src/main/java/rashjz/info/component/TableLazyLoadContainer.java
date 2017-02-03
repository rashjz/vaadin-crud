package rashjz.info.component;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import rashjz.info.domain.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableLazyLoadContainer extends BeanContainer {

    private List<Customer> dbFake = new ArrayList<Customer>();

    {
        for (int i = 0; i < 1000; i++) {
            dbFake.add(new Customer("Sara " + i,"test","usd",new Date()));
            dbFake.add(new Customer("Nicolas " + i,"test","usd",new Date()));
            dbFake.add(new Customer("Matthew " + i,"test","usd",new Date()));
            dbFake.add(new Customer("Michaela " + i,"test","usd",new Date()));
            dbFake.add(new Customer("Martin " + i,"test","usd",new Date()));
            dbFake.add(new Customer("Anna " + i,"test","usd",new Date()));
        }
    }


    public TableLazyLoadContainer(Class type) {
        super(type);
    }

    @Override
    public int size() {
        // TODO: here you should get COUNT from database
        int size = dbFake.size();
        return size;
    }

    @Override
    public BeanItem getItem(Object itemId) {
        return new BeanItem((Customer) itemId);
    }

    @Override
    public List<?> getItemIds(final int startIndex, final int numberOfItems) {
        int end = startIndex + numberOfItems;
        // TODO: here you should place fetching data from database (it should be paged SQL of course)
        System.out.println("start: " + startIndex + ", end: " + end);
        //new Sort(Sort.Direction.DESC, "description").and(new Sort(Sort.Direction.ASC, "title"))
//        Pageable pageable = new PageRequest(startIndex, end, null);
        List<Customer> res = dbFake.subList(startIndex, end);
//        List<Customer> res = repository.lazyloadCustomers(pageable);

        return res;
    }
}
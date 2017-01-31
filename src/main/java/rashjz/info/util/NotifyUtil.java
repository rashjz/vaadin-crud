package rashjz.info.util;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

/**
 * Created by rasha_000 on 1/29/2017.
 */
public class NotifyUtil {

    public  static final  void notify(String message,String type){
        new Notification(message,type).show(Page.getCurrent());
    }
}

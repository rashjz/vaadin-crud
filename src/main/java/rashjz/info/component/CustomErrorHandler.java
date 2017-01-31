package rashjz.info.component;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.ui.UI;

import java.nio.file.AccessDeniedException;

public class CustomErrorHandler implements ErrorHandler {


    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Override
    public void error(ErrorEvent event) {
        Throwable finalCause = getFinalCause(event.getThrowable());
        if (AccessDeniedException.class.isAssignableFrom(finalCause.getClass())) {
            // do everything you need
            UI.getCurrent().getSession().close();                   //close Vaadin session
            UI.getCurrent().getSession().getSession().invalidate(); //close Http session
            UI.getCurrent().getPage().setLocation("/login");        //redirect..
//            UI.getCurrent().getNavigator().navigateTo("viewName");      //... or using navigator
            return;
        }

        DefaultErrorHandler.doDefault(event);
    }

    private Throwable getFinalCause(Throwable throwable) {
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        return throwable;
    }
}
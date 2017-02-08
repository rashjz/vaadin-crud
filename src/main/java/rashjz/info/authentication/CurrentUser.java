package rashjz.info.authentication;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

public final class CurrentUser {


    public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = CurrentUser.class.getCanonicalName();

    private CurrentUser() {
    }


    public static String get() {
        String currentUser = (String) getCurrentHttpSession().getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        if (currentUser == null) {
            return "";
        } else {
            return currentUser;
        }
    }

    private static WrappedSession getCurrentHttpSession() {
        VaadinSession s = VaadinSession.getCurrent();
        if (s == null) throw new IllegalStateException("No session found for current thread");
        return s.getSession();
    }


    public static void set(String currentUser) {
        if (currentUser == null) getCurrentHttpSession().removeAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        else getCurrentHttpSession().setAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY, currentUser);
    }

}

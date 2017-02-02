package rashjz.info;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "login";

    public LoginPage(){
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        addComponent(panel);


        FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        content.addComponent(username);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);

        Button send = new Button("Login");
//        send.addClickListener(new ClickListener() {
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public void buttonClick(ClickEvent event) {
//                if(VaadinloginUI.AUTH.authenticate(username.getValue(), password.getValue())){
//                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
//                    getUI().getNavigator().addView(SecurePage.NAME, SecurePage.class);
//                    getUI().getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
//                    Page.getCurrent().setUriFragment("!"+SecurePage.NAME);
//                }else{
//                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
//                }
//            }
//
//        });
        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
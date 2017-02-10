package rashjz.info;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import rashjz.info.authentication.AccessControl;
import rashjz.info.authentication.LoginListener;
import rashjz.info.util.VaadinUtils;


public class LoginView extends CssLayout implements View {

    private TextField username;
    private PasswordField password;
    private Button login;
//    private Button forgotPassword;
    private LoginListener loginListener;
    private AccessControl accessControl;
    private Component loginForm,loginPanel;
    private VerticalLayout centeringLayout;

    public LoginView(AccessControl accessControl, LoginListener loginListener) {
//        Design.read(this);
        this.loginListener = loginListener;
        this.accessControl = accessControl;
        buildUI();

        this.setSizeFull();
        this.setResponsive(true);
        this.setSizeFull();

        username.focus();
    }

    private void buildUI() {
        centeringLayout = new VerticalLayout();
        loginPanel = buildLoginForm();
        centeringLayout.addComponent(loginPanel);
        centeringLayout.setComponentAlignment(loginPanel,Alignment.MIDDLE_CENTER);
        centeringLayout.setMargin(true);
        this.setStyleName("backColorBlack");
        addComponent(centeringLayout);
    }

    private Component buildLoginForm() {
        Panel loginPanel = new Panel("Login  Panel");
        FormLayout loginForm = new FormLayout();
        loginForm.setSizeUndefined();
        loginForm.setMargin(true);

        loginForm.addComponent(username = new TextField("Username", "rashjz"));
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField("Password", "12"));
        password.setWidth(15, Unit.EM);

        CssLayout buttons = new CssLayout();
        loginForm.addComponent(buttons);

        buttons.addComponent(login = new Button("Giriş"));
        login.setDisableOnClick(true);
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    login();
                } finally {
                    login.setEnabled(true);
                }
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_TINY);

//        buttons.addComponent(forgotPassword = new Button("Forgot password?"));
//        forgotPassword.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//                VaadinUtils.showNotification(new Notification("Hint: Try anything"));
//            }
//        });
//        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        loginPanel.setWidth(40,Unit.PERCENTAGE);
        loginPanel.setContent(loginForm);
        return loginPanel;
    }

    private void login() {
        if (accessControl.signIn(username.getValue(), password.getValue())) {
            loginListener.loginSuccessful();
        } else {
            VaadinUtils.showNotification(new Notification("Login failed", "Please check  username and password", Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
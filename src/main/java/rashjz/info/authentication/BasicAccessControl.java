package rashjz.info.authentication;

import rashjz.info.domain.Users;
import rashjz.info.jpa.UsersRepository;

import java.util.logging.Logger;


public class BasicAccessControl implements AccessControl {
    private final static Logger logger = Logger.getLogger(BasicAccessControl.class.getName());
    private UsersRepository repository;

    public BasicAccessControl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty())
            return false;
        Users users = repository.findByUsernameAndPassword(username, password);
        if (users == null)
            return false;
        logger.info(password + " " + username + " " + users);
        CurrentUser.set(username);
        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

}

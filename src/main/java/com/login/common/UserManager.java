package com.login.common;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class UserManager {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserManager.class);

    public static final String HOME_PAGE_REDIRECT = "/secured/hello.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT = "/login.xhtml?faces-redirect=true";

    private String userId;
    private String userPassword;
    private User currentUser;

    public String login() {
        // lookup the user based on user name and user password
        currentUser = find(userId, userPassword);

        if (currentUser != null) {
            LOGGER.info("login successful for '{}'", userId);

            return HOME_PAGE_REDIRECT;
        } else {
            LOGGER.info("login failed for '{}'", userId);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Login failed",
                            "Invalid or unknown credentials."));

            return null;
        }
    }

    public String logout() {
        String identifier = userId;

        // invalidate the session
        LOGGER.debug("invalidating session for '{}'", identifier);
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        LOGGER.info("logout successful for '{}'", identifier);
        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }

        return null;
    }

    private User find(String userId, String password) {
        User result = null;

        // code block to be replaced with actual retrieval of user
        if ("achandwani".equalsIgnoreCase(userId)
                && "1234".equals(password)) {
            result = new User(userId, "Avinash", "Chandwani");
        }

        return result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // do not provide a setter for currentUser!
}
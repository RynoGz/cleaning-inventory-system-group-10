package za.belgiumcampus.cleaninginventory.controller;
import za.belgiumcampus.cleaninginventory.dao.UserDAO;
import za.belgiumcampus.cleaninginventory.model.User;

public class AuthenticationController {
    private final UserDAO userDAO;

    public AuthenticationController() {
        userDAO = new UserDAO();
    }
    
    private User currentUser;
    
    public User login(String username, String password) {
        //Validate that username/password are not empty.
        if (username == null || password == null) {
            return null;
        }

        username = username.trim();
        password = password.trim();

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        // Call userDAO.login(username, password).
        User user = userDAO.login(username, password);

        // Return the User object if found, otherwise return null.
        if (user != null) {
            currentUser = user;
            return user;
        }
        return null;
    }
    
    private boolean isValidPassword(String password) {

        if (password.length() < 8)
            return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c))
                hasUpper = true;

            if (Character.isLowerCase(c))
                hasLower = true;

            if (Character.isDigit(c))
                hasDigit = true;
        }

        return hasUpper && hasLower && hasDigit;
}

    public boolean register(User user) {
        // Validate required fields.
        if (user == null ||
            user.getUsername() == null || user.getUsername().trim().isEmpty() ||
            user.getEmail() == null || user.getEmail().trim().isEmpty() ||
            user.getPasswordHash() == null || user.getPasswordHash().trim().isEmpty() ||
            user.getRole() == null || user.getRole().trim().isEmpty()) {
            return false;
        }

        String username = user.getUsername().trim();
        String email = user.getEmail().trim();
        String passwordHash = user.getPasswordHash().trim();
        String role = user.getRole().trim();

        if (userDAO.usernameExists(username)) {
            return false;
        }

        if (userDAO.emailExists(email)) {
            return false;
        }
        
        if (!isValidPassword(passwordHash)) {
            return false;
        }
        
        try {
            userDAO.insertUser(username, email, passwordHash, role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void logout() {
        // Clear the currently logged-in user.
        currentUser = null;
    }

    public User getCurrentUser() {
        // Return the logged-in user.
        return currentUser;
    }

    public boolean isLoggedIn() {
        // Return true if there is a current user.
        return currentUser != null;
    }
    
}

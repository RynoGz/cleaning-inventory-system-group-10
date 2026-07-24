package za.belgiumcampus.cleaninginventory.controller;
import za.belgiumcampus.cleaninginventory.dao.UserDAO;
import za.belgiumcampus.cleaninginventory.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    
    private final UserDAO userDAO;
    private final AuthenticationController authController;

    public UserController() {
        userDAO = new UserDAO();
        authController = new AuthenticationController();
    }

    public boolean addUser(User user) {
        return authController.register(user);
    }

    public boolean updateUser(User user) {

        if (user == null)
            return false;

        try {

            return userDAO.updateUser(
                    user.getUserId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPasswordHash(),
                    user.getRole());

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    public boolean deleteUser(int userId) {

        return userDAO.deleteUser(userId);

    }

    public List<User> getAllUsers() {

        return userDAO.getAllUsers();

    }

    public List<User> searchUsers(String text) {

        return userDAO.searchUsers(text);

    }
}

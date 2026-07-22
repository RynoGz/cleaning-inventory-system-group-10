package za.belgiumcampus.cleaninginventory.controller;

import java.sql.SQLException;
import java.util.List;

import za.belgiumcampus.cleaninginventory.dao.CleanerDAO;
import za.belgiumcampus.cleaninginventory.model.Cleaner;
import za.belgiumcampus.cleaninginventory.util.Validation;


public class CleanerController {    
    
     private final CleanerDAO cleanerDAO;
    private final Validation validation;

    public CleanerController() {
        cleanerDAO = new CleanerDAO();
        validation = new Validation();
    }

    public boolean addCleaner(Cleaner cleaner) throws SQLException {

        if (Validation.isEmpty(cleaner.getFirstName()))
            return false;

        if (Validation.isEmpty(cleaner.getLastName()))
            return false;

        if (Validation.isEmpty(cleaner.getPhone()))
            return false;

        if (Validation.isEmpty(cleaner.getEmail()))
            return false;

        if (Validation.isEmpty(cleaner.getDepartment()))
            return false;

        if (!Validation.isValidEmail(cleaner.getEmail()))
            return false;

        if (!Validation.isValidPhoneNumber(cleaner.getPhone()))
            return false;

        if (!Validation.isWithinLength(cleaner.getFirstName(), 50))
            return false;

        if (!Validation.isWithinLength(cleaner.getLastName(), 50))
            return false;

        if (!Validation.isWithinLength(cleaner.getEmail(), 100))
            return false;

        if (!Validation.isWithinLength(cleaner.getPhone(), 10))
            return false;

        if (!Validation.isValidBoolean(cleaner.getIsActive()))
            return false;

        return cleanerDAO.insertCleaner(cleaner);
    }

    public boolean updateCleaner(Cleaner cleaner) throws SQLException {

        if (Validation.isEmpty(cleaner.getFirstName()))
            return false;

        if (Validation.isEmpty(cleaner.getLastName()))
            return false;

        if (Validation.isEmpty(cleaner.getPhone()))
            return false;

        if (Validation.isEmpty(cleaner.getEmail()))
            return false;

        if (Validation.isEmpty(cleaner.getDepartment()))
            return false;

        if (!Validation.isValidEmail(cleaner.getEmail()))
            return false;

        if (!Validation.isValidPhoneNumber(cleaner.getPhone()))
            return false;

        if (!Validation.isWithinLength(cleaner.getFirstName(), 50))
            return false;

        if (!Validation.isWithinLength(cleaner.getLastName(), 50))
            return false;

        if (!Validation.isWithinLength(cleaner.getEmail(), 100))
            return false;

        if (!Validation.isWithinLength(cleaner.getPhone(), 10))
            return false;

        return cleanerDAO.updateCleaner(cleaner);
    }

    public boolean deleteCleaner(int cleanerId) throws SQLException {
        return cleanerDAO.deleteCleaner(cleanerId);
    }

    public List<Cleaner> getAllCleaners() throws SQLException {
        return cleanerDAO.getAllCleaners();
    }

    public List<Cleaner> searchCleaners(String searchText) throws SQLException {
        return cleanerDAO.searchCleaners(searchText);
    }
}

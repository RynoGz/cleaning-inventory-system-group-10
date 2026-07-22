package za.belgiumcampus.cleaninginventory.controller;

import java.sql.SQLException;
import java.util.List;

import za.belgiumcampus.cleaninginventory.dao.IssieDAO;
import za.belgiumcampus.cleaninginventory.model.Issue;
import za.belgiumcampus.cleaninginventory.util.Validation;

public class IssueController {
    
    private final IssieDAO issuanceDAO;
    private final Validation validation;

    public IssueController() {
        issuanceDAO = new IssieDAO();
        validation = new Validation();
    }

    public int addIssuance(Issue issuance) throws SQLException {

        // Validate material
        if (issuance.getMaterialId() <= 0)
            return -1;

        // Validate cleaner
        if (issuance.getCleanerId() <= 0)
            return -1;

        // Validate quantity
        if (issuance.getQuantityIssued() <= 0)
            return -1;

        // Insert and return generated issuance_id
        return issuanceDAO.insertIssuance(issuance);
    }

    public boolean updateIssuance(Issue issuance) throws SQLException {

        if (issuance.getIssuanceId() <= 0)
            return false;

        if (issuance.getMaterialId() <= 0)
            return false;

        if (issuance.getCleanerId() <= 0)
            return false;

        if (issuance.getQuantityIssued() <= 0)
            return false;

        return issuanceDAO.updateIssuance(issuance);
    }
    public boolean deleteIssuance(int issuanceId) throws SQLException {
        return issuanceDAO.deleteIssuance(issuanceId);
    }

    public List<Issue> getAllIssuances() throws SQLException {
        return issuanceDAO.getAllIssuances();
    }

    public List<Issue> searchIssuances(String searchText) throws SQLException {
        return issuanceDAO.searchIssuances(searchText);
    }
}

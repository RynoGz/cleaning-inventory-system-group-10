package za.belgiumcampus.cleaninginventory.controller;

import za.belgiumcampus.cleaninginventory.dao.DashboardDAO;
import za.belgiumcampus.cleaninginventory.model.Issue;

import java.util.List;

public class DashboardController {

    private final DashboardDAO dashboardDAO;

    public DashboardController() {
        dashboardDAO = new DashboardDAO();
    }

    public int getTotalMaterials() {
        return dashboardDAO.getTotalMaterials();
    }

    public int getTotalCleaners() {
        return dashboardDAO.getTotalCleaners();
    }

    public int getLowStockCount() {
        return dashboardDAO.getLowStockCount();
    }

    public List<Issue> getRecentIssues() {
        return dashboardDAO.getRecentIssues();
    }
}

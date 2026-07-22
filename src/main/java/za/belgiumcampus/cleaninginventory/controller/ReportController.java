package za.belgiumcampus.cleaninginventory.controller;

import java.sql.SQLException;
import java.util.List;

import za.belgiumcampus.cleaninginventory.dao.ReportsDAO;
import za.belgiumcampus.cleaninginventory.model.InventoryReport;
import za.belgiumcampus.cleaninginventory.model.LowStockReport;
import za.belgiumcampus.cleaninginventory.model.IssuanceHistoryReport;
import za.belgiumcampus.cleaninginventory.model.MaterialUsageReport;
import za.belgiumcampus.cleaninginventory.util.Validation;

public class ReportController {
    
    private final ReportsDAO reportsDAO;
    private final Validation validation;

    public ReportController() {
        reportsDAO = new ReportsDAO();
        validation = new Validation();
    }

    // Inventory Report

    public List<InventoryReport> getInventoryReport() throws SQLException {
        return reportsDAO.getInventoryReport();
    }

    // Low Stock Report

    public List<LowStockReport> getLowStockReport(int threshold) throws SQLException {

        if (!Validation.isPositiveNumber(threshold))
            return null;

        return reportsDAO.getLowStockReport(threshold);
    }

    // Issuance History Report

    public List<IssuanceHistoryReport> getIssuanceHistory(int cleanerId) throws SQLException {

        if (!Validation.isPositiveNumber(cleanerId))
            return null;

        return reportsDAO.getIssuanceHistory(cleanerId);
    }

    // Material Usage Report

    public List<MaterialUsageReport> getMaterialUsageReport(int materialId) throws SQLException {

        if (!Validation.isPositiveNumber(materialId))
            return null;

        return reportsDAO.getMaterialUsageReport(materialId);
    }
}

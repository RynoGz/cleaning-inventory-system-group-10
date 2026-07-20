package za.belgiumcampus.cleaninginventory.controller;

import java.sql.SQLException;
import java.util.List;

import za.belgiumcampus.cleaninginventory.dao.SupplierDAO;
import za.belgiumcampus.cleaninginventory.model.Supplier;
import za.belgiumcampus.cleaninginventory.util.Validation;

public class SupplierController {
    private final SupplierDAO supplierDAO;

    public SupplierController(){
        supplierDAO = new SupplierDAO();
    }

    public boolean addSupplier(Supplier supplier) throws SQLException {

        if (Validation.isEmpty(supplier.getName()))
            return false;

        if (Validation.isEmpty(supplier.getContactPerson()))
            return false;

        if (!Validation.isValidPhoneNumber(supplier.getPhone()))
            return false;

        if (!Validation.isValidEmail(supplier.getEmail()))
            return false;

        if (!Validation.isWithinLength(supplier.getName(), 100))
            return false;

        if (!Validation.isWithinLength(supplier.getContactPerson(), 100))
            return false;

        if (!Validation.isWithinLength(supplier.getPhone(), 10))
            return false;

        if (!Validation.isWithinLength(supplier.getEmail(), 100))
            return false;

        if (!Validation.isWithinLength(supplier.getAddress(), 255))
            return false;

        supplier.setName(supplier.getName().trim());
        supplier.setContactPerson(supplier.getContactPerson().trim());

        return supplierDAO.insertSupplier(supplier);
    }

    public boolean updateSupplier(Supplier supplier) throws SQLException {

        if (Validation.isEmpty(supplier.getName()))
            return false;

        if (Validation.isEmpty(supplier.getContactPerson()))
            return false;

        if (!Validation.isValidPhoneNumber(supplier.getPhone()))
            return false;

        if (!Validation.isValidEmail(supplier.getEmail()))
            return false;

        if (!Validation.isWithinLength(supplier.getName(), 100))
            return false;

        if (!Validation.isWithinLength(supplier.getContactPerson(), 100))
            return false;

        if (!Validation.isWithinLength(supplier.getPhone(), 10))
            return false;

        if (!Validation.isWithinLength(supplier.getEmail(), 100))
            return false;

        if (!Validation.isWithinLength(supplier.getAddress(), 255))
            return false;

        supplier.setName(supplier.getName().trim());
        supplier.setContactPerson(supplier.getContactPerson().trim());

        return supplierDAO.updateSupplier(supplier);
    }

    public boolean deleteSupplier(int supplierId) throws SQLException {

        return supplierDAO.deleteSupplier(supplierId);
    }

    public List<Supplier> getAllSuppliers() throws SQLException {

        return supplierDAO.getAllSuppliers();
    }

    public List<Supplier> searchSuppliers(String searchText) throws SQLException {

        return supplierDAO.searchSuppliers(searchText);
    }
}

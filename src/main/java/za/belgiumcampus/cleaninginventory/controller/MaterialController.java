package za.belgiumcampus.cleaninginventory.controller;

import java.sql.SQLException;
import java.util.List;

import za.belgiumcampus.cleaninginventory.dao.MaterialDAO;
import za.belgiumcampus.cleaninginventory.model.Material;
import za.belgiumcampus.cleaninginventory.util.Validation;

public class MaterialController {
    private final MaterialDAO materialDAO;
    private final Validation validation;

    public MaterialController(){
        materialDAO = new MaterialDAO();
        validation = new Validation();
    }

    public boolean addMaterial(Material material) throws SQLException {

        if (Validation.isEmpty(material.getName()))
            return false;

        if (Validation.isEmpty(material.getDescription()))
            return false;

        if (!Validation.isWithinLength(material.getName(), 100))
            return false;

        if (!Validation.isWithinLength(material.getDescription(), 255))
            return false;

        if (material.getQuantityAvailable() < 0)
            return false;

        if (material.getReorderLevel() < 0)
            return false;

        material.setName(material.getName().trim());
        material.setDescription(material.getDescription().trim());

        return materialDAO.insertMaterial(material);
    }

    public boolean updateMaterial(Material material) throws SQLException {

        if (Validation.isEmpty(material.getName()))
            return false;

        if (Validation.isEmpty(material.getDescription()))
            return false;

        if (!Validation.isWithinLength(material.getName(), 100))
            return false;

        if (!Validation.isWithinLength(material.getDescription(), 255))
            return false;

        if (material.getQuantityAvailable() < 0)
            return false;

        if (material.getReorderLevel() < 0)
            return false;

        material.setName(material.getName().trim());
        material.setDescription(material.getDescription().trim());

        return materialDAO.updateMaterial(material);
    }

    public boolean deleteMaterial(int materialId) throws SQLException {

        return materialDAO.deleteMaterial(materialId);
    }

    public List<Material> getAllMaterials() throws SQLException {

        return materialDAO.getAllMaterials();
    }

    public List<Material> searchMaterials(String searchText) throws SQLException {

        return materialDAO.searchMaterials(searchText);
    }

    public List<Material> getLowStockMaterials() throws SQLException {

        return materialDAO.getLowStockMaterials();
    }
}

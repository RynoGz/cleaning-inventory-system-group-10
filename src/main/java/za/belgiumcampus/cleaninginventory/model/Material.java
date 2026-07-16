package za.belgiumcampus.cleaninginventory.model;

import java.sql.Timestamp;

public class Material {
    private int materialId;
    private String name;
    private String description;
    private int quantityAvailable;
    private int reorderLevel;
    private int supplierId;
    private Timestamp createdAt;

    //Default Constructor
    public Material(){}
    // Add Constructor
    public Material(String name, String description, Integer quantityAvailable, Integer reorderLevel, Integer supplierId) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.reorderLevel = reorderLevel;
        this.supplierId = supplierId;
    }
    // Fetch Constructor
    public Material(Integer materialId, String name, String description, Integer quantityAvailable, Integer reorderLevel, Integer supplierId, Timestamp createdAt){
        this.materialId = materialId;
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.reorderLevel = reorderLevel;
        this.supplierId = supplierId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString(){
        return name;
    }
}

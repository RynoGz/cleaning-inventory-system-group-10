package za.belgiumcampus.cleaninginventory.model;

import java.sql.Timestamp;

public class Issue {
    
     private int issuanceId;
    private int materialId;
    private int cleanerId;
    private int quantityIssued;
    private int issuedByUserId;
    private Timestamp createdAt;

    public Issue(){}

    public Issue(int materialId, int cleanerId, int quantityIssued, int issuedByUserId) {
        this.materialId = materialId;
        this.cleanerId = cleanerId;
        this.quantityIssued = quantityIssued;
        this.issuedByUserId = issuedByUserId;
    }



    public int getIssuanceId() {
        return issuanceId;
    }

    public void setIssuanceId(int issuanceId) {
        this.issuanceId = issuanceId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(int cleanerId) {
        this.cleanerId = cleanerId;
    }

    public int getQuantityIssued() {
        return quantityIssued;
    }

    public void setQuantityIssued(int quantityIssued) {
        this.quantityIssued = quantityIssued;
    }

    public int getIssuedByUserId() {
        return issuedByUserId;
    }

    public void setIssuedByUserId(int issuedByUserId) {
        this.issuedByUserId = issuedByUserId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

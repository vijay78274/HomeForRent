package com.example.homeforrent.contract;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FullContracts")
public class FullContract {
    @Id
    private String id;
    private String landlordName;
    private String tenantName;
    private LocalDateTime contractDate;
    private String tenantAdhaar;
    private String landlordAdhaar;
    private String roomFor;
    private String roomType;
    private String rent;
    private String landlordPhone;
    private String tenantPhone;
   
    
    public FullContract(String landlordName, String tenantName, LocalDateTime contractDate, String tenantAdhaar,
            String landlordAdhaar, String roomFor, String roomType, String rent, String landlordPhone,
            String tenantPhone) {
        this.landlordName = landlordName;
        this.tenantName = tenantName;
        this.contractDate = contractDate;
        this.tenantAdhaar = tenantAdhaar;
        this.landlordAdhaar = landlordAdhaar;
        this.roomFor = roomFor;
        this.roomType = roomType;
        this.rent = rent;
        this.landlordPhone = landlordPhone;
        this.tenantPhone = tenantPhone;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLandlordName() {
        return landlordName;
    }
    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }
    public String getTenantName() {
        return tenantName;
    }
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    public LocalDateTime getContractDate() {
        return contractDate;
    }
    public void setContractDate(LocalDateTime contractDate) {
        this.contractDate = contractDate;
    }
    public String getTenantAdhaar() {
        return tenantAdhaar;
    }
    public void setTenantAdhaar(String tenantAdhaar) {
        this.tenantAdhaar = tenantAdhaar;
    }
    public String getLandlordAdhaar() {
        return landlordAdhaar;
    }
    public void setLandlordAdhaar(String landlordAdhaar) {
        this.landlordAdhaar = landlordAdhaar;
    }
    public String getRoomFor() {
        return roomFor;
    }
    public void setRoomFor(String roomFor) {
        this.roomFor = roomFor;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public String getRent() {
        return rent;
    }
    public void setRent(String rent) {
        this.rent = rent;
    }
    public String getLandlordPhone() {
        return landlordPhone;
    }
    public void setLandlordPhone(String landlordPhone) {
        this.landlordPhone = landlordPhone;
    }
    public String getTenantPhone() {
        return tenantPhone;
    }
    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }
}

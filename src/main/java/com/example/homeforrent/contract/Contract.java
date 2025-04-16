package com.example.homeforrent.contract;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Contract")
public class Contract {
    @Id
    private String id;
    private String role;
    private String to;
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public LocalDateTime getContractDate() {
        return contractDate;
    }
    public void setContractDate(LocalDateTime contractDate) {
        this.contractDate = contractDate;
    }
    public String getAdhaar() {
        return adhaar;
    }
    public void setAdhaar(String adhaar) {
        this.adhaar = adhaar;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    private String from;
    private LocalDateTime contractDate;
    private String adhaar;
    private String roomFor;
    private String roomType;
    private String rent;
    private String phone;
}

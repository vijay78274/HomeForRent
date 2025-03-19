package com.example.homeforrent;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tenet")
public class Tenet {
    @Id
    private String userName;
    private String name;
    private String phone;
    private String age;
    private String gender;
    private String occuption;
    private String martial;
    private String TypeofRoom;
    private String RoomFor;
    private String image;
    private String password;
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getOccuption() {
        return occuption;
    }
    public void setOccuption(String occuption) {
        this.occuption = occuption;
    }
    public String getMartial() {
        return martial;
    }
    public void setMartial(String martial) {
        this.martial = martial;
    }
    public String getTypeofRoom() {
        return TypeofRoom;
    }
    public void setTypeofRoom(String typeofRoom) {
        TypeofRoom = typeofRoom;
    }
    public String getRoomFor() {
        return RoomFor;
    }
    public void setRoomFor(String roomFor) {
        RoomFor = roomFor;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}

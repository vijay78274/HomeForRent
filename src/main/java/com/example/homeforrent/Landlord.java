package com.example.homeforrent;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "LandLord")
public class Landlord {
    @Id
    private String userName;
    private String name;
    private String phone;
    private String TypeofRoom;
    private String RoomFor;
    private String status;
    private String password;
    private String[] room_images;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String[] getRoom_images() {
        return room_images;
    }
    public void setRoom_images(String[] room_images) {
        this.room_images = room_images;
    }
    
}

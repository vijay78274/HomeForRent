package com.example.homeforrent.LandLord;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LandLord")
public class Landlord {
    @Id
    private String userName;
    private String name;
    private String phone;
    private String[] TypeofRoom;
    public String[] getTypeofRoom() {
        return TypeofRoom;
    }
    public void setTypeofRoom(String[] typeofRoom) {
        TypeofRoom = typeofRoom;
    }
    public String[] getRoomFor() {
        return RoomFor;
    }
    public void setRoomFor(String[] roomFor) {
        RoomFor = roomFor;
    }
    private String[] RoomFor;
    private String status;
    private String password;
    private String images;
    private String address;
    private double latitude; 
    private String[] roomImages;
    private String profile;
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String[] getRoomImages() {
        return roomImages;
    }
    public void setRoomImages(String[] roomImages) {
        this.roomImages = roomImages;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    private double longitude;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
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

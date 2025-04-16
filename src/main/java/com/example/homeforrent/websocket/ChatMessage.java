package com.example.homeforrent.websocket;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "messages")
public class ChatMessage {
    @Id
    private String id;
    private String sender;
    private String receiver; 
    private String content;
    private java.util.Date timestamp;
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String from) {
        this.sender = from;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReciever(String to) {
        this.receiver = to;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public java.util.Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
    
}

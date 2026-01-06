package br.com.example.rabbit.rsan.rabbit.entity;

import java.time.LocalDateTime;

public class ChatMessage {

    private String username;
    private String message;
    private LocalDateTime timestamp;

    public ChatMessage() {
        this.timestamp = LocalDateTime.now();
    }

    public ChatMessage(String username, String message) {
        this.username = username;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + username + ": " + message;
    }
}

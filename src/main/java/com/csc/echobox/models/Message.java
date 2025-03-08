package com.csc.echobox.models;

import jakarta.persistence.*;

@Entity
@Table(name = "message") // Explicit table name for clarity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Message content - can be null if this is just an image or audio message
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    // Type of message: text, image, file, audio
    @Column(name = "message_type")
    private String messageType;

    // For storing image data
    @Lob
    @Column(name = "image_data",  nullable = true)
    private byte[] imageData = null;

    // Image file name (optional, but useful)
    @Column(name = "image_name")
    private String imageName;

    @Column(name = "message_status")
    private String messageStatus;

    // For storing file data
    @Lob
    @Column(name = "file_data",  nullable = true)
    private byte[] fileData = null;

    // File name (optional, but useful)
    @Column(name = "file_name")
    private String fileName;

    // For storing audio data
    @Lob
    @Column(name = "audio_data",  nullable = true)
    private byte[] audioData = null;

    // Audio file name (optional, but useful)
    @Column(name = "audio_name")
    private String audioName;

    // When the message was sent (using java.sql.Timestamp for better JPA compatibility)
    @Column(name = "timestamp")
    private java.sql.Timestamp timestamp;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }

    public byte[] getImageData() { return imageData; }
    public void setImageData(byte[] imageData) { this.imageData = imageData; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getMessageStatus() { return messageStatus; }
    public void setMessageStatus(String messageStatus) { this.messageStatus = messageStatus; }

    public byte[] getFileData() { return fileData; }
    public void setFileData(byte[] fileData) { this.fileData = fileData; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public byte[] getAudioData() { return audioData; }
    public void setAudioData(byte[] audioData) { this.audioData = audioData; }

    public String getAudioName() { return audioName; }
    public void setAudioName(String audioName) { this.audioName = audioName; }

    public java.sql.Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(java.sql.Timestamp timestamp) { this.timestamp = timestamp; }
}
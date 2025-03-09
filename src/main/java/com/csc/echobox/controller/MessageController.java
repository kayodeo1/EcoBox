package com.csc.echobox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.csc.echobox.models.Message;
import com.csc.echobox.models.Messages;
import com.csc.echobox.service.MessageService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController()
@RequestMapping("/api")
public class MessageController {

	@Autowired
	MessageService messageService;

	@GetMapping("/test")
	public String sayHello() {
		return "Hello , working";
	}

	@GetMapping("/get-messages")
	public ResponseEntity<List<Messages>> getMessages() {
		List<Messages> msg = messageService.getAllMessages();
		
		 return new ResponseEntity<List<Messages>>(msg,HttpStatus.OK);
		            
		   
	}

	@PostMapping("/add-message")
	public ResponseEntity<String> addMessage(@RequestParam("type") String type,
			@RequestParam(required = false) String text, @RequestParam(required = false) MultipartFile image,
			@RequestParam(required = false) MultipartFile document,
			@RequestParam(required = false) MultipartFile audio) {

		if (type == null) {
			return ResponseEntity.badRequest().body("Message type cannot be null");

		}
		Message message = new Message();
		message.setMessageStatus("unread");
		switch (type) {
		case "text":
			if (text == null) {
				return ResponseEntity.badRequest().body("Text message cannot be null");
			}
			message.setText(text);
			message.setMessageType("text");
			message.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
			break;

		case "image":
			if (image == null) {
				return ResponseEntity.badRequest().body("Image message cannot be null");
			}
			try {
				message.setImageData(image.getBytes());
				message.setImageName(image.getOriginalFilename());
				message.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
				message.setMessageType("image");
				if (text != null) {
					message.setText(text);
				}
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Error uploading image");
			}
			break;
		case "document":
			if (document == null) {
				return ResponseEntity.badRequest().body("Document message cannot be null");
			}
			try {
				message.setFileData(document.getBytes());
				message.setFileName(document.getOriginalFilename());
				message.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
				message.setMessageType("document");
				if (text != null) {
					message.setText(text);
				}
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Error uploading document");
			}
			break;
		case "audio":
			if (audio == null) {
				return ResponseEntity.badRequest().body("Audio message cannot be null");
			}
			try {
				message.setAudioData(audio.getBytes());
				message.setAudioName(audio.getOriginalFilename());
				message.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
				message.setMessageType("audio");
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Error uploading audio");
			}
			break;
		default:
			return ResponseEntity.badRequest().body("Invalid message type");

		}
		messageService.saveMessage(message);

		return new ResponseEntity("Message saved successfully", HttpStatus.OK);
	}

	@GetMapping("read-messages")
	public ResponseEntity<String> readMessages(@PathVariable("id") Long id) {
		Message message = messageService.getMessage(id);
		if (message == null) {
			return ResponseEntity.badRequest().body("Message not found");
		}
		message.setMessageStatus("read");
		messageService.saveMessage(message);
		return new ResponseEntity<String>("Message read successfully", HttpStatus.OK);
	}

	@GetMapping("/get-media/{id}")
    public ResponseEntity<byte[]> getMedia(@PathVariable("id") Long id) {
        Message message = messageService.getMessage(id);
        if (message == null || message.getMessageType() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String contentType;
        String contentDisposition;
        String fileName;
        byte[] fileData;

        switch (message.getMessageType()) {
            case "image":
                contentType = "image/jpeg";  // Change if using PNG, GIF, etc.
                contentDisposition = "inline";
                fileName = message.getImageName();
                fileData = message.getImageData();
                break;
            case "document":
                contentType = "application/pdf"; // Change if using DOCX, TXT, etc.
                contentDisposition = "inline";
                fileName = message.getFileName();
                fileData = message.getFileData();
                break;
            case "audio":
                contentType = "audio/mpeg"; // Change if using WAV, OGG, etc.
                contentDisposition = "attachment";  // Force download
                fileName = message.getAudioName();
                fileData = message.getAudioData();
                break;
            default:
                return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition + "; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(fileData);
    }
}



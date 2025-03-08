package com.csc.echobox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.csc.echobox.models.Message;
import com.csc.echobox.repository.MessageRepo;

@Service
public class MessageService {
	
	@Autowired			
	MessageRepo repo;
	
	public String getAllMessages() {
		List<Message> messages = repo.findAll();
		String out = "{\n 'message': 'for media, use the id to get the media with the /api/media/{id} route' \n";
		for (Message message : messages) {
			out += "[";
			
			if (message.getMessageType().equals("text")) {
                out += " id: " + message.getId() + 
                		"\n,text: " + message.getText() + ",\n";
                
                
            } else if (message.getMessageType().equals("image")) {
				out += " id: " + message.getId() + ",\ntext: " + message.getText() + ",\n";

			} else if (message.getMessageType().equals("file")) {
				out += " id: " + message.getId() + ",\ntext: " + message.getText() + ",\n";

			} else if (message.getMessageType().equals("audio")) {
				out += " id: " + message.getId() + ",\n";
			}
			out += "Status: " + message.getMessageStatus() + ",\n";
			out += "Type: " + message.getMessageType() + ",\n";
			out+= "Time: " + message.getTimestamp() + ",\n";
			out += "], \n";
            }
		
                  out += "}"; 
		return out;
	}

	public void saveMessage(Message message) {
		
        repo.save(message);
    }

	public Message getMessage(Long id) {
		Message message = repo.findById(id).orElse(null);
		return message;
	}
	
	
	
	
	
	
		
	}
	
	
		
		
		

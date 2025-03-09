package com.csc.echobox.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.csc.echobox.models.Message;
import com.csc.echobox.models.Messages;
import com.csc.echobox.repository.MessageRepo;

@Service
public class MessageService {
	
	@Autowired			
	MessageRepo repo;
	
	public List<Messages> getAllMessages() {
		List<Message> messages = repo.findAll();
		List<Messages> MessageList = new ArrayList();
		for (Message message : messages) {
			Messages msg = new Messages(message.getId(),message.getText(),message.getMessageType(),message.getTimestamp(),message.getMessageStatus());
			MessageList.add(msg);
		}
		
                  
		return MessageList;
	}

	public void saveMessage(Message message) {
		
        repo.save(message);
    }

	public Message getMessage(Long id) {
		Message message = repo.findById(id).orElse(null);
		return message;
	}
	
	
	
	
	
	
		
	}
	

		
		

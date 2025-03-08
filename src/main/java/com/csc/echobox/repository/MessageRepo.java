package com.csc.echobox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc.echobox.models.Message;

public interface MessageRepo extends JpaRepository<Message, Long>{

}

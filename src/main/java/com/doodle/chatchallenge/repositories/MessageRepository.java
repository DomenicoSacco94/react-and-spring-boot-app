package com.doodle.chatchallenge.repositories;

import com.doodle.chatchallenge.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}

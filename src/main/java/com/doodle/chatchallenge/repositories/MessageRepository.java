package com.doodle.chatchallenge.repositories;

import com.doodle.chatchallenge.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface managing the <code>Message</code> entity
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}

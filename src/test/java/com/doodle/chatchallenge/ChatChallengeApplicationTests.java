package com.doodle.chatchallenge;

import com.doodle.chatchallenge.models.Message;
import com.doodle.chatchallenge.repositories.MessageRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
  * Contains some API Rest tests
 */
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ChatchallengeApplication.class)
class ChatChallengeApplicationTests {

	private String API_PATH = "/api/v1/messages";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private DataSource datasource;

	@Autowired
	private MessageRepository messageRepository;

	private static boolean dataLoaded = false;


	//sets up some examples on which do the testing
	@BeforeAll
	public void setup() throws SQLException {
		if (!dataLoaded) {
			try (Connection con = datasource.getConnection()) {
				ScriptUtils.executeSqlScript(con, new ClassPathResource("pre_test_data.sql"));
				dataLoaded = true;
			}
		}
	}

	//deletes the messages examples used in testing
	@AfterAll
	public void destroy() throws SQLException {
		if (dataLoaded) {
			try (Connection con = datasource.getConnection()) {
				ScriptUtils.executeSqlScript(con, new ClassPathResource("post_test_data.sql"));
				dataLoaded = false;
			}
		}
	}

	@DisplayName("Checks that the messages can be retrieved and are ordered")
	@Test
	void checkGetMessages() {
		ResponseEntity<Message[]> result = this.restTemplate.getForEntity(API_PATH , Message[].class);
		List<Message> messages = Arrays.asList(result.getBody());
		assert (messages.size() > 0);
		Timestamp date1 = messages.get(0).getSendDate();
		Timestamp date2 = messages.get(1).getSendDate();
		assert(date1.before(date2) || date1.equals(date2));
	}

	@DisplayName("Checks that a message can be created")
	@Test
	void checkCreateMessage() {
		Message message = new Message();
		message.setText("Test for create message");
		message.setSenderName("John Doe");
		message.setSendDate(Timestamp.valueOf(LocalDateTime.now()));

		ResponseEntity<Message> result = this.restTemplate.postForEntity(API_PATH , message, Message.class);

		assert (result.getBody().getId() != null);
		assert (result.getStatusCode().equals(HttpStatus.CREATED));

		Message messageToDelete = result.getBody();
		messageRepository.delete(messageToDelete);

	}
}

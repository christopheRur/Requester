package com.codelabs.Requester;

import com.codelabs.Requester.Controller.RequesterController;
import com.codelabs.Requester.model.Sender;
import com.codelabs.Requester.service.RequesterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RequesterApplicationTests {

	@Mock
	private Sender sender;

	@Mock
	private RequesterService reqServ;

	@InjectMocks
	RequesterController controller;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSendMessage(){
		sender.setMessage("here is the message");

		Mockito.when(reqServ.sendMessage(sender)).thenReturn(sender);

		ResponseEntity<?> response= controller.sendMessage(reqServ.sendMessage(sender));

		assertEquals(HttpStatus.OK,response.getStatusCode());

	}

}

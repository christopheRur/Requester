package com.codelabs.Requester;

import com.codelabs.Requester.Controller.RequesterController;
import com.codelabs.Requester.model.Sender;
import com.codelabs.Requester.service.RequesterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;
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

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RequesterApplicationTests {

	private static final Logger log = LogManager.getLogger(RequesterApplicationTests.class);

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
	public void testSendMessage() throws MessagingException {

		sender.setMessage("Here is the message");

		Mockito.when(reqServ.sendMessage(sender)).thenReturn(sender);

		ResponseEntity<?> response= controller.sendMessage(reqServ.sendMessage(sender));

		assertEquals(HttpStatus.OK,response.getStatusCode());

	}

	@Test
	public void testUpdateSender(){


		Mockito.when(reqServ.updateSender(1L,sender)).thenReturn(sender);

		ResponseEntity<?> response =controller.updateSender(1L,sender);

		assertEquals(HttpStatus.OK,response.getStatusCode());


	}

	@Test
	public void testDeleteSender() throws JSONException{
		JSONObject resp= new JSONObject();
		resp.put("Success","removed sender");

		Mockito.when(reqServ.deleteSenderById(1L)).thenReturn(resp);

		ResponseEntity<?> response =controller.deleteSender(1L);

		assertEquals(HttpStatus.OK,response.getStatusCode());

	}

	@Test
	public void testGetAllSenders(){

		List<Sender> senders = new ArrayList<>();

		senders.add(new Sender().setLastName("tester01"));
		senders.add(new Sender().setLastName("tester02"));

		when(reqServ.getAllSenders()).thenReturn(senders);

		ResponseEntity<?> response =controller.getAllSenders();

		log.info("====-->--"+response.getBody());

		assertEquals(HttpStatus.OK, response.getStatusCode());





	}


}

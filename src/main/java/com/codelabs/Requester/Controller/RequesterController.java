package com.codelabs.Requester.Controller;

import com.codelabs.Requester.model.Sender;
import com.codelabs.Requester.repostitory.RequesterRepository;
import com.codelabs.Requester.service.RequesterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequesterController {
    private static final Logger log = LogManager.getLogger(RequesterController.class);

    RequesterService requesterService;
    public RequesterController(RequesterService req){
        this.requesterService=req;
    }

    @PostMapping("/sendMsg")
    public ResponseEntity<?> sendMessage(@RequestBody Sender sender){

        log.info("====> Message processing ..."+sender.getMessage());
       return new ResponseEntity<>(requesterService.sendMessage(sender), HttpStatus.OK);

    }
}

package com.codelabs.Requester.Controller;

import com.codelabs.Requester.model.Sender;
import com.codelabs.Requester.repostitory.RequesterRepository;
import com.codelabs.Requester.service.RequesterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequesterController {
    private static final Logger log = LogManager.getLogger(RequesterController.class);

    RequesterService requesterService;
    public RequesterController(RequesterService req){
        this.requesterService=req;
    }

    /**
     * sends the message
     * @param sender Sender
     * @return ResponseEntity
     */
    @PostMapping("/sendMsg")
    public ResponseEntity<?> sendMessage(@RequestBody Sender sender){

        try {

            if (sender == null) {
                return ResponseEntity.badRequest().body("The body is empty");
            }

            log.info("====> Message processing ..." + sender.getMessage());
            return new ResponseEntity<>(requesterService.sendMessage(sender), HttpStatus.OK);

        }catch(Exception e){
            log.error(e.getCause());
            return ResponseEntity.badRequest().body("Error occurred!");
        }

    }

    /**
     * Retrieve message by last name
     * @param sender
     * @return
     */
    @GetMapping("/getMsg")
    public ResponseEntity<?> getMessage(@RequestBody final Sender sender) {

        try {
            if (sender.getLastName().isEmpty())
                return ResponseEntity.badRequest().body("Missing LastName");

            return new ResponseEntity<>(requesterService.findSenderByName(sender
                    .getLastName()).getMessage(), HttpStatus.OK);

        } catch (Exception e) {
            log.info("==>"+e.getCause());
            return ResponseEntity.badRequest().body(sender.getLastName()+" not found!");
        }
    }



}

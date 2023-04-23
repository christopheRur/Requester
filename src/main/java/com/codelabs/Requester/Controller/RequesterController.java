package com.codelabs.Requester.Controller;

import com.codelabs.Requester.model.Sender;
import com.codelabs.Requester.repostitory.RequesterRepository;
import com.codelabs.Requester.service.RequesterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Retrieve all senders
     * @return
     */
    @GetMapping("/senders")
    public ResponseEntity<?> getAllSenders(){
        try{

        return new ResponseEntity<>(requesterService.getAllSenders(),HttpStatus.OK);}
        catch (Exception e){
            log.info("==>"+e.getCause());
            return ResponseEntity.badRequest().body("Failed to get list!");

        }
    }

    /**
     * Updates sender accordingly
     * @param id
     * @param sender
     * @return
     */
    @PutMapping("/updateSender")
    public ResponseEntity<?> updateSender(@RequestHeader final Long id,
                                          @RequestBody Sender sender){
        try{

            if(id==null || sender==null){
              return  ResponseEntity.badRequest().body("Sender or id is empty!");
            }
            log.error(" ===> Processing sender Updates!");

          return new ResponseEntity<>(requesterService.updateSender(id,sender),HttpStatus.OK);

        }catch(Exception e){
            log.error(" ===> Failed to update sender: "+e.getMessage());

            return ResponseEntity.badRequest().body("Unable to update sender!");
        }

    }

    /**
     * Delete sender once id is identified
     * @param id
     * @return
     */
    @DeleteMapping("/delSender")
    public ResponseEntity<?> deleteSender(@RequestHeader final Long id) {

        try{

            if(id==null){
                log.info(" ===> Check id!");

                return new ResponseEntity<>("id is null!",
                        HttpStatus.BAD_REQUEST);
            }


            return new ResponseEntity<>(requesterService.deleteSenderById(id),HttpStatus.OK);


        }catch(Exception e){
            log.error(" ===> Failed to delete sender: "+e.getMessage());

            return ResponseEntity.badRequest().body("Failed to delete!");
        }
    }



}

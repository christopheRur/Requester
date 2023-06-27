package com.codelabs.Requester.service;

import com.codelabs.Requester.exception.SenderException;
import com.codelabs.Requester.service.mailer.Mailer;
import com.codelabs.Requester.model.Sender;
import com.codelabs.Requester.repostitory.RequesterRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class RequesterService {

    private static final Logger log = LogManager.getLogger(RequesterService.class);

    Sender senderDetails=new Sender();

    Mailer mail= new Mailer(senderDetails);
    @Autowired
    private RequesterRepository reqRep;

    public RequesterService(RequesterRepository requesterRepository){
        this.reqRep=requesterRepository;
    }

    /**
     * send message
     * @param sender
     * @return
     */
    public Sender sendMessage(Sender sender) throws MessagingException {

        mail.sendMessage(sender);

        log.info("===> Message was sent.....");
        return reqRep.save(sender);

    }

    public Sender loginSender(Sender sender){
        log.info("=Your password==> your are here"+sender.getEmail());

        if(!findSenderByName(sender.getEmail()).getPassword().isEmpty())

        return findSenderByName(sender.getEmail());

        return sender;
    }

    /**
     * retrieve sender's message by last name
     * @param name String.
     * @return
     */
    public Sender findSenderByName(String name){
        return reqRep.findSenderByLastName(name).orElseThrow(
                ()->new SenderException("The specified name "+name+" not found!"));
    }

    /**
     * retrieve sender by id
     * @param senderId
     * @return
     */
    public Sender findSenderById(Long senderId){
        return reqRep.findById(senderId).orElseThrow(
                ()->new SenderException("The specified SenderId "+senderId+" not found!"));
    }

    /**
     * Delete sender by id
     * @param id
     * @return
     */
    public JSONObject deleteSenderById(Long id){
        Sender sender= findSenderById(id);
         reqRep.delete(sender);

         JSONObject resp= new JSONObject();
         resp.put("Success","removed sender with id "+id);

         return resp;

    }

    /**
     * List all senders
     * @return List<Sender>
     */
    public List<Sender> getAllSenders(){

        return reqRep.findAll();
    }

    /**
     * Update sender
     * @param id
     * @param sender
     * @return
     */
    public Sender updateSender(Long id, Sender sender){
        Sender user = findSenderById(id);
        user.setFirstName(sender.getFirstName())
                .setLastName(sender.getLastName())
                .setEmail(sender.getEmail())
                .setMessage(sender.getMessage())
                .setSubject(sender.getSubject());

        return reqRep.save(user);
    }

}

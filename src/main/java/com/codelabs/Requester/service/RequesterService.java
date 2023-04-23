package com.codelabs.Requester.service;

import com.codelabs.Requester.exception.SenderException;
import com.codelabs.Requester.model.Sender;
import com.codelabs.Requester.repostitory.RequesterRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequesterService {

    private static final Logger log = LogManager.getLogger(RequesterService.class);
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
    public Sender sendMessage(Sender sender){
        log.info("===> Message was sent.....");
        return reqRep.save(sender);

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

}

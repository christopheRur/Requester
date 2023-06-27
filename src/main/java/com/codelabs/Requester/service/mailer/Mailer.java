package com.codelabs.Requester.service.mailer;

import com.codelabs.Requester.model.Sender;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static com.codelabs.Requester.constants.Constants.*;

public class Mailer {
    private static final Logger log = LogManager.getLogger(Mailer.class);

    private final Sender senderInfo;
    private Properties props;

    public Mailer(Sender senderInfo) {
        this.senderInfo = senderInfo;

        props= new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
    }

    /**
     * This will initialize the session
     * @return Session
     */
    private Session startSession(Sender sender) {

        Session session = null;

        try {

             session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    log.info("Auth success!"+sender.getPassword());
                    return new javax.mail.PasswordAuthentication(sender.getEmail(),
                            sender.getPassword());
                }
            });


    }catch(Exception ex) {

            log.info("Error Occurred =>"+ex.getMessage());

        }

        return session;
    }

    /**
     * Creates message content
     * @param sender Sender
     * @return MimeMessage
     * @throws MessagingException
     */
    private MimeMessage createMessage(Sender sender) throws MessagingException {

        log.info("==> message creation initialized..."+sender.getMessage());

        MimeMessage message = new MimeMessage(startSession(sender));
        message.setFrom(new InternetAddress(sender.getEmail()));
        message.setRecipient(Message.RecipientType.TO,
                new InternetAddress(sender.getRecipient(),false));
        log.info("-----===== recipient==>"+sender.getRecipient());
        message.setSubject(sender.getSubject());
        message.setContent(sender.getMessage(),"text/plain");
        message.setSentDate(new Date());

        log.info("--> Message was created!");

        return message;
    }

    /**
     *  This function will transport the message
     * @param sender Sender
     */
    public void sendMessage(Sender sender) {

        try {


           Transport.send(createMessage(sender));
            log.info("---> Message was transported.... ");

        } catch (Exception e) {
            log.error(e.getCause());
            e.printStackTrace();
        }
    }
}

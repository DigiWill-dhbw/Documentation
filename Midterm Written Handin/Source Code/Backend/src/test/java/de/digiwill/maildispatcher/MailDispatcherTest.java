package de.digiwill.maildispatcher;

import de.digiwill.exception.EmailException;
import de.digiwill.model.UserHandle;
import de.digiwill.util.EmailDispatcher;
import de.digiwill.util.EmailResponseHandle;
import de.digiwill.util.EmailTransportWrapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MailDispatcherTest {

    EmailDispatcher emailDispatcher;
    Session session;
    @Mock EmailTransportWrapper emailTransportWrapper;

    ArgumentCaptor<Message> sentMessage;
    String subject = "subject";
    String content = "content";

    @Before
    public void setup(){
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.google.com");
        session = Session.getInstance(properties);
        emailDispatcher = new EmailDispatcher(session, emailTransportWrapper);
        sentMessage = ArgumentCaptor.forClass(Message.class);
        reset(emailTransportWrapper);
    }

    public void compareMessage(ArgumentCaptor<Message> m, String[] recipients, String subject, String content) throws IOException, MessagingException {
        InternetAddress[] mailAddresses = InternetAddress.parse(String.join(",",recipients),true);
        Assert.assertArrayEquals(mailAddresses, m.getValue().getAllRecipients());
        Assert.assertEquals(subject, m.getValue().getSubject());
        Assert.assertEquals(content, m.getValue().getContent());
    }

    @Test
    public void sendEmailTest() throws Exception{
        String[] recipient = {"test@testmail.com"};

        emailDispatcher.sendEmail(recipient[0], subject, true, content);
        verify(emailTransportWrapper).sendMessage(sentMessage.capture());
        compareMessage(sentMessage, recipient, subject, content);
    }

    @Test
    public void endEmailListTest()throws Exception{
        List<String> recipients = new ArrayList<String>();
        for(int i=0; i<10; i++){
            recipients.add("test"+i+"@test.de");
        }
        emailDispatcher.sendEmail(recipients, subject, true, content);
        verify(emailTransportWrapper).sendMessage(sentMessage.capture());
        compareMessage(sentMessage, recipients.toArray(new String[recipients.size()]), subject, content);
    }

    @Test(expected = EmailException.class)
    public void sendEmailTestMissingRecipients() throws Exception{
        emailDispatcher.sendEmail("", subject, true, content);
    }

    @Test(expected = EmailException.class)
    public void sendMailMalformedEmailAddress() throws Exception{
        emailDispatcher.sendEmail("jfdsklfjsl", subject, true, content);
    }


    @Test
    public void sendRegistrationConfirmationEmailTest() throws Exception{
        UserHandle uh = new UserHandle("user@name.de", "test", null);
        uh.setVerified(false);
        EmailResponseHandle erh = EmailResponseHandle.getRegistrationHandle(uh);
        String[] recipient = {uh.getUsername()};

        emailDispatcher.sendRegistrationConfirmationEmail(erh);
        verify(emailTransportWrapper).sendMessage(sentMessage.capture());
        compareMessage(sentMessage, recipient, EmailDispatcher.REGISTRATION_EMAIL_SUBJECT,
                EmailDispatcher.REGISTRATION_EMAIL_CONTENT.replaceAll("<username>", uh.getUsername()));

    }

    @Test
    public void sendResetEmailTest() throws Exception{
        //TODO out of scope right now, so test will be implemented later

        /*UserHandle uh = new UserHandle("user@name.de", "test", null);
        uh.setVerified(false);
        EmailResponseHandle erh = EmailResponseHandle.getResetHandle(uh);
        String[] recipient = {uh.getUsername()};

        emailDispatcher.sendResetEmail(erh);
        verify(emailTransportWrapper).sendMessage(sentMessage.capture());
        compareMessage(sentMessage, recipient, EmailDispatcher.RESET_EMAIL_SUBJECT,
                EmailDispatcher.RESET_EMAIL_SUBJECT.replaceAll("<username>", uh.getUsername()));*/
    }

    @Test
    public void sendReminderEmailTest() throws Exception{
        UserHandle uh = new UserHandle("user@name.de", "test", null);
        String[] recipient = {uh.getUsername()};

        emailDispatcher.sendReminderEmail(uh);
        verify(emailTransportWrapper).sendMessage(sentMessage.capture());
        compareMessage(sentMessage, recipient, EmailDispatcher.REMINDER_EMAIL_SUBJECT,
                EmailDispatcher.REMINDER_EMAIL_CONTENT.replaceAll("<username>", uh.getUsername()));

    }



}

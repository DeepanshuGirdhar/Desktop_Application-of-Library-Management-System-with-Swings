/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.



https://myaccount.google.com/lesssecureapps?pli=1
 */
package librarymanagement;

/**
 *
 * @author deepanshu girdhar
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
public class EmailSend {
            static String mailto,text_message;
            static int roll_no;
    public static void main(String args[]){
        try{
            String host ="smtp.gmail.com" ;
            String user = "idcommon2@gmail.com ";
            String pass = "idcommon2006";
            String to = mailto;
            String from = "deep.girdhar1@gmail.com ";
            String subject = "Notification For Your Library Book";
            String messageText = text_message;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
    public static void Sendmail(int roll,String mail,String message)
    {
         System.out.println("in emailsend");
        roll_no=roll;
        mailto=mail;
        text_message=message;
        EmailSend.main(new String[]{});
        
    }
}

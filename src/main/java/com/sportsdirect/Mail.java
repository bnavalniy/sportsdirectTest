package com.sportsdirect;


import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {


    public String getUrlRecovery(){
        Properties props = new Properties();
        Mail mail = new Mail();
        String result = "";
        try {

            props.load(new FileInputStream(new File("src/main/resources/smtp.properties")));
            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", "tsportman880@gmail.com","tsport123");

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_WRITE);
            int messageCount = inbox.getMessageCount();
            Message[] messages = inbox.getMessages();

            for (int i = 0; i < messageCount; i++) {
                if(messages[i].getSubject().contains("Sports Direct Latvia - Forgotten Password")) {
                    result = mail.extractUrl(mail.getTextFromMimeMultipart((MimeMultipart) messages[i].getContent()));
                    messages[i].setFlag(Flags.Flag.DELETED, true);
                    return result;
                }
            }
            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";

}
    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break;
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }
    private String extractUrl(String text){
        String url = "";
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            url = text.substring(urlMatcher.start(0),
                    urlMatcher.end(0));
        }
        return url;
    }
}
package dev.zerphyis.upload.service;

import java.io.InputStream;
import java.util.logging.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.internet.MimeMessage;

public class JavaMailSenderMock implements JavaMailSender {

    private static final Logger LOGGER = Logger.getLogger(JavaMailSenderMock.class.getName());

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        for (SimpleMailMessage simpleMessage : simpleMessages) {
            LOGGER.info("E-mail enviado com sucesso para: " + simpleMessage.getTo());
        }
    }

    @Override
    public MimeMessage createMimeMessage() {
        throw new UnsupportedOperationException("Unimplemented method 'createMimeMessage'");
    }

    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        throw new UnsupportedOperationException("Unimplemented method 'createMimeMessage'");
    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }

}

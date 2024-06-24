package fr.epita.discover.infrastructure.config;

import org.springframework.stereotype.Service;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */
@Service
public class MailService {
    public void sendMail(String to, String subject, String body) {
        System.out.println("Sending mail to " + to + " with subject " + subject + " and body " + body);
    }
}
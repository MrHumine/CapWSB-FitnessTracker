package pl.wsb.fitnesstracker.mail.api;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Profile("email")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Service
@RequiredArgsConstructor
@Slf4j
class EmailServiceImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(final EmailDto email) {
        log.info("Sending email to: {}", email.toAddress());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.toAddress());
        message.setSubject(email.subject());
        message.setText(email.content());

        javaMailSender.send(message);
        log.info("Email sent to: {}", email.toAddress());
    }
}

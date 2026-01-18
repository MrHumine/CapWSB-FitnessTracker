package pl.wsb.fitnesstracker.event.api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import org.springframework.mail.javamail.JavaMailSender;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("listener")
class MySpringEventListener implements ApplicationListener<MySpringEvent> {
    private final EmailSender javaMailSender;

    @Override
    public void onApplicationEvent(final MySpringEvent event) {
        log.info("Received spring custom event in Listener - " + event.getMyMessage());
        log.info("Sending email with Received E-mail Title " + event.getMyMessage());


        EmailDto emailDto = new EmailDto("to.address@mail.com",
                "fitnesstracker@cap.wsb.com",
                event.getMyMessage()
        );
        javaMailSender.send(emailDto);

    }



}
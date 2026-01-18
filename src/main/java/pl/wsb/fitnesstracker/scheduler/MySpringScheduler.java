package pl.wsb.fitnesstracker.scheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class MySpringScheduler {
    private final UserProvider userProvider;
    private final TrainingProvider trainingProvider;
    private final EmailSender emailSender;

    @Scheduled(cron = "0 0 8 * * MON")
    public void scheduleTask() {
        List<User> allUsers = userProvider.findAllUsers();

        for (User user : allUsers) {
            List<TrainingDto> trainings = trainingProvider.getTrainingsByUserId(user.getId());

            long weeklyCount = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -7);
            Date oneWeekAgo = calendar.getTime();

            for (TrainingDto t : trainings) {
                if (t.getStartTime().after(oneWeekAgo)) {
                    weeklyCount++;
                }
            }

            double distance = 0;
            for (TrainingDto t : trainings) {
                if (t.getStartTime().after(oneWeekAgo)) {
                    distance += t.getDistance();
                }
            }
            sendEmail(user, weeklyCount, distance);
        }
    }

    private void sendEmail(final User user, final long weeklyCount, double distance) {
        String subject = "Informacje z tego tygodnia";
        String content = "Cześć "
                + user.getFirstName()
                + "\n" + "Twoja ilość treningów to "
                + weeklyCount + " a suma przebytej odległości to "
                + distance + "km";

        emailSender.send(new EmailDto(user.getEmail(),
                subject,
                content));

    }
}
package pl.wsb.fitnesstracker.training.internal;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final UserMapper userMapper;

    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }
}
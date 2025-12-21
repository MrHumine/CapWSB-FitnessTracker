package pl.wsb.fitnesstracker.training.api;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {

    private Long id;
    private UserDto user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;
}
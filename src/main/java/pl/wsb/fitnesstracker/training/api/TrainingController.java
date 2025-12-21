package pl.wsb.fitnesstracker.training.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.training.internal.TrainingMapper;
import pl.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId);
    }
}


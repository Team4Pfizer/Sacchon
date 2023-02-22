package gr.codehub.sacchon.logger;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CustomLoggerServiceImpl implements CustomLoggerService{

    private final Clock clock;

    private final CustomLoggerRepository customLoggerRepository;
    public Long logError (String message){

        return customLoggerRepository.save(new CustomLoggerDTO(message,LogType.ERROR).toEntity(LocalDateTime.now(clock))).getLogId();
    }

    public Long logInfo (String message){
        return customLoggerRepository.save(new CustomLoggerDTO(message,LogType.INFO).toEntity(LocalDateTime.now(clock))).getLogId();

    }
}

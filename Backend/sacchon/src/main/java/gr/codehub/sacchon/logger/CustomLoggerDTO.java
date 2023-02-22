package gr.codehub.sacchon.logger;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomLoggerDTO {

    private String message;

    private String logType;


    public CustomLoggerDTO(String message,LogType logType){
        this.message=message;
        if (logType==LogType.ERROR){
            this.logType="ERROR";
        }else {
            this.logType="INFO";
        }
    }

    public CustomLoggerModel toEntity (LocalDateTime errorTime){
        return CustomLoggerModel.builder()
                .logType(this.logType)
                .message(this.message)
                .errorTime(errorTime)
                .build();
    }
}

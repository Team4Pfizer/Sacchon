package gr.codehub.sacchon.logger;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="LOGS")
public class CustomLoggerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    private String message;

    private String logType;

    private LocalDateTime errorTime;

}

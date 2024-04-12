package mattiaconsiglio.u5w2d5.dtos;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime timestamp) {
}

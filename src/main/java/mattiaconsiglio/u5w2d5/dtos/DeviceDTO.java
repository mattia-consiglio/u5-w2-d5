package mattiaconsiglio.u5w2d5.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mattiaconsiglio.u5w2d5.entities.DeviceStatus;

import java.util.UUID;

public record DeviceDTO(
        @NotBlank(message = "Device name (name) is required")
        @Size(min = 3, max = 50, message = "Device name (name) must be between 3 and 20 characters")
        String name,
        @NotBlank(message = "Device type (type) is required")
        String type,
        @NotBlank(message = "Device description (description) is required")
        String description,
        @NotNull(message = "Device status (status) is required")
        DeviceStatus status,
        @Nullable
        UUID employeeId
) {
}

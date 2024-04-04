package com.rocketseat.passin.dto.attendeeDTO;

import java.time.LocalDateTime;

public record AttendeeDetailsDTO(
        String id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime checkinAt
) {
}

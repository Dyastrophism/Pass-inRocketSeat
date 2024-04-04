package com.rocketseat.passin.dto.eventDTO;

public record EventCreateDTO(
        String title,
        String details,
        Integer maximumAttendees
) {
}

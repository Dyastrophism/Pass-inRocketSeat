package com.rocketseat.passin.dto.eventDTO;

public record EventDetailDTO(
        String id,
        String title,
        String detail,
        String slug,
        Integer maximumAttendees,
        Integer attendeesAmount
) {
}

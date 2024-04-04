package com.rocketseat.passin.dto.eventDTO;

import com.rocketseat.passin.domain.event.Event;
import lombok.Getter;

@Getter
public class EventResponseDTO {

    EventDetailDTO eventDetailDTO;

    public EventResponseDTO(Event event, Integer numberOfAttendees) {
        this.eventDetailDTO = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
    }
}

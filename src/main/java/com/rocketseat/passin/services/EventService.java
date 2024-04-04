package com.rocketseat.passin.services;

import com.rocketseat.passin.domain.attendee.Attendee;
import com.rocketseat.passin.domain.event.Event;
import com.rocketseat.passin.domain.event.exceptions.EventNotFoundException;
import com.rocketseat.passin.dto.eventDTO.EventCreateDTO;
import com.rocketseat.passin.dto.eventDTO.EventIdDTO;
import com.rocketseat.passin.dto.eventDTO.EventResponseDTO;
import com.rocketseat.passin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventCreateDTO eventCreateDTO){
        Event newEvent = new Event();
        newEvent.setTitle(eventCreateDTO.title());
        newEvent.setDetails(eventCreateDTO.details());
        newEvent.setMaximumAttendees(eventCreateDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventCreateDTO.title()));

        this.eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-").toLowerCase();
    }
}

package com.rocketseat.passin.services;

import com.rocketseat.passin.domain.attendee.Attendee;
import com.rocketseat.passin.domain.checkin.Checkin;
import com.rocketseat.passin.dto.attendeeDTO.AttendeeDetailsDTO;
import com.rocketseat.passin.dto.attendeeDTO.AttendeeListResponseDTO;
import com.rocketseat.passin.repository.AttendeeRepository;
import com.rocketseat.passin.repository.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private AttendeeRepository attendeeRepository;
    private CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeeListResponseDTO getEventAttendees(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId); // pegamos todos os participantes do evento
        List<AttendeeDetailsDTO> attendeeDetailsDTOList = attendeeList.stream().map(attendee -> { // passando por cada um dos participantes
            Optional<Checkin> checkin = this.checkinRepository.findByAttendeeId(attendee.getId()); // é feita a consulta na tabela de checkin para cada participante
            LocalDateTime checkedinAt = checkin.<LocalDateTime>map(Checkin::getCreatedAt).orElse(null); // verifico se o participante tem um checkin registrado ou não, caso tenha, é pego o horário que foi feito o checkin
            return new AttendeeDetailsDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedinAt); // caso tenha, é montado o detalhe do participante com o horário que foi feito o checkin
        }).toList();

        return new AttendeeListResponseDTO(attendeeDetailsDTOList);
    }
}

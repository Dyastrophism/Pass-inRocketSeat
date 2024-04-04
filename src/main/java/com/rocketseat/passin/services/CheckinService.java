package com.rocketseat.passin.services;

import com.rocketseat.passin.domain.attendee.Attendee;
import com.rocketseat.passin.domain.checkin.Checkin;
import com.rocketseat.passin.domain.checkin.exceptions.CheckinAlreadyExistsException;
import com.rocketseat.passin.repository.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckinService {
    private final CheckinRepository checkinRepository;

    public void registerCheckin(Attendee attendee) {
        this.verifyCheckinExists(attendee.getId());
        Checkin newCheckin = new Checkin();
        newCheckin.setAttendee(attendee);
        newCheckin.setCreatedAt(LocalDateTime.now());
        this.checkinRepository.save(newCheckin);
    }

    private void verifyCheckinExists(String attendeeId) {
        Optional<Checkin> isCheckedin = this.getCheckin(attendeeId);
        if(isCheckedin.isPresent()) throw new CheckinAlreadyExistsException("Attendee already checked in");
    }

    public Optional<Checkin> getCheckin(String attendeeId) {
        return this.checkinRepository.findByAttendeeId(attendeeId);
    }
}

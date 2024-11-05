package com.salasync.projectfinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salasync.projectfinal.repositorys.BookingRepository;
import com.salasync.projectfinal.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalTime;
import java.time.DayOfWeek;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public boolean isRoomAvailable(Long roomId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Booking> conflictingBookings = bookingRepository.findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(roomId, endTime, startTime);
        return conflictingBookings.isEmpty();
    }

    public Booking createBooking(Booking booking) {
        if (!isWeekday(booking.getStartTime()) || !isWithinAllowedHours(booking.getStartTime(), booking.getEndTime())) {
            throw new RuntimeException("Reservas de sala só podem ser feitas em dias de semana e em horas específicas do dias");
        }
        
        if (isRoomAvailable(booking.getRoom().getId(), booking.getStartTime(), booking.getEndTime())) {
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Esta sala não está disponível nas horas selecionadas");
        }
    }

    private boolean isWeekday(LocalDateTime dateTime) {
        DayOfWeek day = dateTime.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    private boolean isWithinAllowedHours(LocalDateTime startTime, LocalDateTime endTime) {
        LocalTime start = startTime.toLocalTime();
        LocalTime end = endTime.toLocalTime();

        // Define time slots: 08:00-12:00, 13:00-17:00, 18:00-22:00
        return (isWithinTimeSlot(start, end, LocalTime.of(8, 0), LocalTime.of(12, 0)) ||
                isWithinTimeSlot(start, end, LocalTime.of(13, 0), LocalTime.of(17, 0)) ||
                isWithinTimeSlot(start, end, LocalTime.of(18, 0), LocalTime.of(22, 0)));
    }

    private boolean isWithinTimeSlot(LocalTime start, LocalTime end, LocalTime slotStart, LocalTime slotEnd) {
        return !start.isBefore(slotStart) && !end.isAfter(slotEnd);
    }
}
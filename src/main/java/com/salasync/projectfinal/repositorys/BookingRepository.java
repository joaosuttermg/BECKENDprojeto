package com.salasync.projectfinal.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

import com.salasync.projectfinal.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long roomId, LocalDateTime endTime, LocalDateTime startTime);
}
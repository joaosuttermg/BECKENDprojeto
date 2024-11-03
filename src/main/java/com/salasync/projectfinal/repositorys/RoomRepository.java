package com.salasync.projectfinal.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salasync.projectfinal.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
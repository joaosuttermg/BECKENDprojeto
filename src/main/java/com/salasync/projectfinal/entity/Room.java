package com.salasync.projectfinal.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Room {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;

private String name;

private int capacity;

private String description;

private String equipment;

private boolean available;
}
package com.salasync.projectfinal.entity;

import jakarta.validation.constraints.Email;

public record AuthenticationDTO(Email email, String password) {

}

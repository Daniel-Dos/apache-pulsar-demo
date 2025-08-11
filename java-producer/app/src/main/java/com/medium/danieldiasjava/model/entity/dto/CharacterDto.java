package com.medium.danieldiasjava.model.entity.dto;

import java.io.Serializable;

public record CharacterDto(
        String name,
        String eikon,
        boolean dominant ) implements Serializable {}
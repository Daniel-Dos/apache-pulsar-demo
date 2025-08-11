package com.medium.danieldiasjava.service;

import com.medium.danieldiasjava.model.entity.dto.CharacterDto;

import java.util.List;

public interface CharacterService {
    void saveCharacterEvent(CharacterDto character);
}

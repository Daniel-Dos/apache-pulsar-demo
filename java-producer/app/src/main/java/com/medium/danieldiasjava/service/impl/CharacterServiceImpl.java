package com.medium.danieldiasjava.service.impl;

import com.medium.danieldiasjava.model.entity.dto.CharacterDto;
import com.medium.danieldiasjava.pulsar.producer.CharacterProducerEvent;
import com.medium.danieldiasjava.service.CharacterService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;

import java.util.Objects;

@RequestScoped
public class CharacterServiceImpl implements CharacterService {

    private Logger logger;

    private CharacterProducerEvent characterProducerEvent;
    public CharacterServiceImpl(){}

    @Inject
    public CharacterServiceImpl(CharacterProducerEvent characterProducerEvent, Logger logger) {
        this.characterProducerEvent = characterProducerEvent;
        this.logger = logger;
    }

    @Override
    public void saveCharacterEvent(CharacterDto character) {
        if (Objects.nonNull(character)) {
            logger.info("Saving character event: {}", character);
            characterProducerEvent.sendCharacterEvent(character);
            logger.info("Character event saved successfully: {}", character);
        } else {
            throw new IllegalArgumentException("Character cannot be null");
        }
    }
}
package com.medium.danieldiasjava.pulsar.producer;

import com.medium.danieldiasjava.model.entity.dto.CharacterDto;
import jakarta.inject.Inject;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

public class CharacterProducerEvent {

    private Logger logger;

    private PulsarClient pulsarClient;

    @Inject
    @ConfigProperty(name = "pulsar.topic")
    private String characterTopic;

    public CharacterProducerEvent() {}

    @Inject
    public CharacterProducerEvent(PulsarClient pulsarClient,  Logger logger) {
        this.pulsarClient = pulsarClient;
        this.logger = logger;
    }

    public void sendCharacterEvent(CharacterDto characterDto) {
        try {
            logger.info("Sending character event: {}", characterDto);
            if (pulsarClient == null) {
                logger.error("PulsarClient is not initialized.");
                return;
            }
            Producer<CharacterDto> producer = pulsarClient.newProducer(Schema.JSON(CharacterDto.class))
                    .topic(characterTopic)
                    .create();
					
            producer.send(characterDto);

            logger.info("Character event sent successfully: {}", characterDto);
            producer.close();

        } catch (Exception e) {
            logger.error("Error sending character event", e);
        }
    }
}

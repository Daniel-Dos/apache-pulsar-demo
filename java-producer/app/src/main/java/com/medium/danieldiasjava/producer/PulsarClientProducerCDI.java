package com.medium.danieldiasjava.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class PulsarClientProducerCDI {

    @Inject
    private Logger logger;

    @Inject
    @ConfigProperty(name = "pulsar.service-url")
    private String pulsarServiceUrl;

    @ApplicationScoped
    @Produces
    public PulsarClient createPulsarClient() throws PulsarClientException {
        logger.info("Creating PulsarClient with service URL: {}", pulsarServiceUrl);
        return PulsarClient.builder()
                .serviceUrl(pulsarServiceUrl)
                .build();
    }

    public void shutdown(@Disposes @Default PulsarClient pulsarClient) throws PulsarClientException {
        logger.info("Shutting down PulsarClient");
        if (pulsarClient != null) {
            pulsarClient.close();
        }
    }

}

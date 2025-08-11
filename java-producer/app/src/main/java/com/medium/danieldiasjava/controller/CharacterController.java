package com.medium.danieldiasjava.controller;

import com.medium.danieldiasjava.model.entity.dto.CharacterDto;
import com.medium.danieldiasjava.service.CharacterService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;

import java.util.Objects;

@Path("characters")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CharacterController {

    private Logger logger;

    private CharacterService characterService;

    public CharacterController() {}

    @Inject
    public CharacterController(CharacterService characterService, Logger logger) {
        this.characterService = characterService;
        this.logger = logger;
    }

    @POST
    public Response createCharacter(CharacterDto character) {

        if(Objects.nonNull(character)) {
            logger.info("Creating character: {}", character);
            characterService.saveCharacterEvent(character);
            return Response.status(Response.Status.CREATED).entity(character).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Character cannot be null").build();
        }
    }
}

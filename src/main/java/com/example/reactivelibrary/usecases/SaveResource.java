package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.DTOs.ResourceDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveResource {
    Mono<ResourceDTO> apply(@Valid ResourceDTO resourceDTO);
}

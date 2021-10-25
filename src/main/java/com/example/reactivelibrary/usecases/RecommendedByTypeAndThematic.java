package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.DTOs.ResourceDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@FunctionalInterface
public interface RecommendedByTypeAndThematic {
    Flux<ResourceDTO> get(@Valid String kind, @Valid String thematic);
}


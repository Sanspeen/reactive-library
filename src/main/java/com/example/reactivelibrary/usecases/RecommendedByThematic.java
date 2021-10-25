package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.DTOs.ResourceDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@FunctionalInterface
public interface RecommendedByThematic {
    Flux<ResourceDTO> get(@Valid String thematic);
}
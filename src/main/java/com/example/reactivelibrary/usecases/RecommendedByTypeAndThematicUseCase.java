package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.DTOs.ResourceDTO;
import com.example.reactivelibrary.mappers.ResourceMapper;
import com.example.reactivelibrary.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecommendedByTypeAndThematicUseCase implements RecommendedByTypeAndThematic{

    private final ResourceRepository repository;
    private final ResourceMapper mapper;

    public RecommendedByTypeAndThematicUseCase(ResourceMapper mapper, ResourceRepository repository) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<ResourceDTO> get(String kind, String thematic) {
        return repository.findAllByKindAndThematic(kind,thematic).map(resource -> mapper.mapEntityToResourceDTO().apply(resource));
    }
}

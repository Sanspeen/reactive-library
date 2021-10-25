package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.DTOs.ResourceDTO;
import com.example.reactivelibrary.mappers.ResourceMapper;
import com.example.reactivelibrary.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetResourceUseCase implements Function<String, Mono<ResourceDTO>> {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;

    public GetResourceUseCase(ResourceMapper mapper, ResourceRepository repository) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<ResourceDTO> apply(String id) {
        Objects.requireNonNull(id, "ID is required");
        return repository.findById(id).map(resource -> mapper.mapEntityToResourceDTO().apply(resource));
    }
}

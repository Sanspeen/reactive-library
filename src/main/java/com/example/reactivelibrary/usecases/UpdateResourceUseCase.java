package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.DTOs.ResourceDTO;
import com.example.reactivelibrary.mappers.ResourceMapper;
import com.example.reactivelibrary.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateResourceUseCase implements SaveResource{

    private final ResourceRepository repository;
    private final ResourceMapper mapper;

    public UpdateResourceUseCase(ResourceMapper mapper, ResourceRepository repository) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<ResourceDTO> apply(ResourceDTO resourceDTO) {
        Objects.requireNonNull(resourceDTO.getId(), "ID is required.");
        return repository.save(mapper.mapperToResourceEntity().apply(resourceDTO))
                .map(resource -> mapper.mapEntityToResourceDTO().apply(resource));
    }

}

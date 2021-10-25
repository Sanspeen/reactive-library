package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.mappers.ResourceMapper;
import com.example.reactivelibrary.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteResourceUseCase implements Function<String, Mono<Void>> {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;

    public DeleteResourceUseCase(ResourceRepository repository, ResourceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "The ID is completelly necesary to delete a resource.");
        return repository.deleteById(id);
    }
}
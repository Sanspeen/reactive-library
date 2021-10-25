package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.repositories.ResourceRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class CheckAvailabilityUseCase implements Function<String, Mono<String>> {

    private final ResourceRepository repository;

    public CheckAvailabilityUseCase(ResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id no puede ser nulo");
        return repository.findById(id)
                .map(resource ->
                        resource.getAvaibleResources() > resource.getLendedResources()
                                ? "This resource is able to lend at this time, we have " + (resource.getAvaibleResources() - resource.getLendedResources()) + " avaible"
                                : "The resource is not able, it has been lended " + resource.getLocalDate()
                );
    }
}

package com.example.reactivelibrary.usecases;

import com.example.reactivelibrary.mappers.ResourceMapper;
import com.example.reactivelibrary.repositories.ResourceRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Validated
public class LendResourceUseCase implements Function<String, Mono<String>> {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;
    private final UpdateResourceUseCase updateResourceUseCase;

    public LendResourceUseCase(ResourceRepository repository, ResourceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.updateResourceUseCase = new UpdateResourceUseCase(mapper,repository);
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id,"El id es requerido");
        return repository.findById(id).flatMap(resource -> {
            if (resource.getAvaibleResources()>resource.getLendedResources()){
                resource.setLendedResources(resource.getLendedResources()+1);
                resource.setLocalDate(LocalDate.now());
                return updateResourceUseCase.apply(mapper.mapEntityToResourceDTO().apply(resource)).thenReturn("This resource has been lended successfully!");
            }
            return Mono.just("This resource is not able to lend at this moment.");
        });
    }
}

package com.example.reactivelibrary.mappers;

import com.example.reactivelibrary.DTOs.ResourceDTO;
import com.example.reactivelibrary.collections.Resource;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ResourceMapper {
    public Function<ResourceDTO, Resource> mapperToResourceEntity() {
        return updateResource -> {
            var resource = new Resource();
            resource.setId(updateResource.getId());
            resource.setName(updateResource.getName());
            resource.setKind(updateResource.getKind());
            resource.setThematic(updateResource.getThematic());
            resource.setAvaibleResources(updateResource.getAvaibleResources());
            resource.setLendedResources(updateResource.getLendedResources());
            resource.setLocalDate(updateResource.getLocalDate());
            return resource;
        };
    }

    public Function<Resource, ResourceDTO> mapEntityToResourceDTO() {
        return entity -> new ResourceDTO(
                entity.getId(),
                entity.getName(),
                entity.getAvaibleResources(),
                entity.getLendedResources(),
                entity.getKind(),
                entity.getThematic(),
                entity.getLocalDate()
        );
    }
}

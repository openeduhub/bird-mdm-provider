package org.edusharing.wlo.bird.mdm.provider.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiException;
import org.edusharing.wlo.bird.mdm.provider.models.BirdDTO;
import org.edusharing.wlo.bird.mdm.provider.models.Paged;
import org.edusharing.wlo.bird.mdm.provider.services.EduSharingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class Controller {

    private final EduSharingService eduSharingService;
    @GetMapping
    @Parameters({
            @Parameter(name = "page", description = "page number",
                    in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0")),
            @Parameter(name = "size", description = "page size",
                    in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "20"))
    })

    public Paged<BirdDTO> get(@RequestParam(required = false, defaultValue = "0") int page,
                              @RequestParam(required = false, defaultValue = "20") int size) throws ApiException {
        return eduSharingService.getCourses(page, size);
    }
}

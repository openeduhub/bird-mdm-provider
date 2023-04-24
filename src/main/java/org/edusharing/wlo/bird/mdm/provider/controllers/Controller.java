package org.edusharing.wlo.bird.mdm.provider.controllers;

import lombok.RequiredArgsConstructor;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiException;
import org.edusharing.wlo.bird.mdm.provider.services.EduSharingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class Controller {

    private final EduSharingService eduSharingService;
    @GetMapping
    public String get() throws ApiException {
        eduSharingService.Test();
        return "Hello World";
    }
}

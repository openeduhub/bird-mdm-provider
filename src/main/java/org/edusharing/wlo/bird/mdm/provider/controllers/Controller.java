package org.edusharing.wlo.bird.mdm.provider.controllers;

import lombok.RequiredArgsConstructor;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiException;
import org.edusharing.wlo.bird.mdm.provider.models.BirdDTO;
import org.edusharing.wlo.bird.mdm.provider.services.EduSharingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class Controller {

    private final EduSharingService eduSharingService;
    @GetMapping(produces = "application/json; charset=UTF-8")
    public List<BirdDTO> get() throws ApiException {
        return eduSharingService.getCourses();
    }

    @DeleteMapping
    public void evictCaches(){
        eduSharingService.evictCache();
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<String> handleApiException(){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ups something went wrong.");
    }

    @ExceptionHandler({java.net.SocketTimeoutException.class})
    public ResponseEntity<String> handleSocketTimeoutException(){
        return ResponseEntity
                .status(HttpStatus.REQUEST_TIMEOUT)
                .body("Repository wasn't answering");
    }
}

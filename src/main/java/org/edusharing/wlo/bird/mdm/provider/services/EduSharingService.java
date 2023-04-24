package org.edusharing.wlo.bird.mdm.provider.services;

import lombok.RequiredArgsConstructor;
import org.edu_sharing.generated.repository.backend.services.rest.client.api.NodeV1Api;
import org.edu_sharing.generated.repository.backend.services.rest.client.model.NodeEntry;
import org.edu_sharing.generated.repository.backend.services.rest.client.model.SearchResult;
import org.springframework.stereotype.Service;

import org.edu_sharing.generated.repository.backend.services.rest.client.*;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class EduSharingService {

    private final NodeV1Api nodeV1Api;

    public void Test() throws ApiException {
        NodeEntry metadata = nodeV1Api.getMetadata("-home-", "26105802-9039-4add-bf21-07a0f89f6e70", null);
        System.out.println(metadata);
    }
}

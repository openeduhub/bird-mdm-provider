package org.edusharing.wlo.bird.mdm.provider.fixes;

import okhttp3.Call;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiCallback;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiClient;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiException;
import org.edu_sharing.generated.repository.backend.services.rest.client.Pair;
import org.edu_sharing.generated.repository.backend.services.rest.client.api.NodeV1Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClientFixes extends ApiClient {
    @Override
    public Call buildCall(String baseUrl, String path, String method, List<Pair> queryParams, List<Pair> collectionQueryParams, Object body, Map<String, String> headerParams, Map<String, String> cookieParams, Map<String, Object> formParams, String[] authNames, ApiCallback callback) throws ApiException {
        headerParams.putIfAbsent("Content-Type", "application/json");
        return super.buildCall(baseUrl, path, method, queryParams, collectionQueryParams, body, headerParams, cookieParams, formParams, authNames, callback);
    }
}

package org.edusharing.wlo.bird.mdm.provider.config;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiClient;
import org.edu_sharing.generated.repository.backend.services.rest.client.api.NodeV1Api;
import org.edusharing.wlo.bird.mdm.provider.fixes.ApiClientFixes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import org.threeten.bp.temporal.TemporalAccessor;
@Configuration
public class eduSharingConfig {
    @Value("${edu.sharing.basePath}")
    public String basePath;

    @Value("${edu.sharing.user}")
    public String username;

    @Value("${edu.sharing.password}")
    public String password;

    @Bean
    ApiClient apiClient(){
        ApiClient apiClient = new ApiClientFixes();
        apiClient.setBasePath(basePath);
        apiClient.addDefaultHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", username, password).getBytes()));
        apiClient.addDefaultHeader("Content-Type", "application/json");

        return apiClient;
    }

    @Bean
    NodeV1Api nodeV1Api(ApiClient apiClient){
        return new NodeV1Api(apiClient);
    }
}

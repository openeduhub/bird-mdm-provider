package org.edusharing.wlo.bird.mdm.provider.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiException;
import org.edusharing.wlo.bird.mdm.provider.services.EduSharingService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheCleaner {

    private final EduSharingService eduSharingService;

    @Scheduled(fixedDelayString = "${application.cache.ttl}", initialDelayString = "${application.cache.ttl}", timeUnit = TimeUnit.SECONDS)
    public void evictCacheSchedule() {
        eduSharingService.evictCache();
        try {
            eduSharingService.getCourses();
        } catch (ApiException e) {
            log.error("evictCacheSchedule: {}",e.getMessage(), e);
        }
    }

}
